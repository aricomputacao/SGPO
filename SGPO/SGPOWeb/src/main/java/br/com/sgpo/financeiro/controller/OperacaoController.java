/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.financeiro.controller;

import br.com.sgpo.financeiro.DAO.FaturaOperacaoDAO;
import br.com.sgpo.financeiro.DAO.OperacaoDAO;
import br.com.sgpo.financeiro.modelo.FaturaOperacao;
import br.com.sgpo.financeiro.modelo.Operacao;
import br.com.sgpo.utilitario.ControllerGenerico;
import br.com.sgpo.utilitarios.ParcelamentoUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ari
 */
@Stateless
public class OperacaoController extends ControllerGenerico<Operacao, Long> implements Serializable {
    
    @Inject
    private OperacaoDAO dao;
    @Inject
    private FaturaController faturaController;
    
    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }
    
    private void addOperacaoComParcelamento(Operacao operacao, BigDecimal valor) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTime(operacao.getDateOperacao());
        List<Date> datasVencimento = ParcelamentoUtil.geraVencimentos(operacao.getDateOperacao(), operacao.getNumeroParcelas());
        List<BigDecimal> valores = ParcelamentoUtil.totalParcelas(valor, operacao.getNumeroParcelas());
        for (int i = 0; i < operacao.getNumeroParcelas(); i++) {
            FaturaOperacao fatura = new FaturaOperacao();
            fatura.setOperacao(operacao);
            fatura.setDataVencimento(datasVencimento.get(i));
            fatura.setValor(valores.get(i));
            
            faturaController.salvar(fatura);
            
        }
    }
    
    private void addOperacaoSemParcelamento(Operacao operacao, BigDecimal valor) throws Exception {
        FaturaOperacao fatura = new FaturaOperacao();
        fatura.setOperacao(operacao);
        fatura.setDataVencimento(operacao.getDateOperacao());
        fatura.setValor(valor);
        
        faturaController.salvar(fatura);
        
    }
    
    private List<Operacao> processarOperacao(Operacao operacao, BigDecimal valor) throws Exception {
        List<Operacao> list = new ArrayList<>();
        Date ultimaData = operacao.getDateOperacao();
        
        if (operacao.isRecorrencia()) {
            for (int i = 0; i < operacao.getTempoRecorrencia(); i++) {
                
                Operacao op = new Operacao();
                op.setCategoriaOperacao(operacao.getCategoriaOperacao());
                op.setDateOperacao(ultimaData);
                op.setDescricao(operacao.getDescricao());
                op.setNumeroParcelas(operacao.getNumeroParcelas());
                op.setParcelamento(operacao.isParcelamento());
                op.setPeriodoRecorrencia(operacao.getPeriodoRecorrencia());
                op.setRecorrencia(operacao.isRecorrencia());
                op.setTempoRecorrencia(operacao.getTempoRecorrencia());
                
                dao.salvar(op);
                list.add(op);
                
                ultimaData = operacao.getPeriodoRecorrencia().processaData(ultimaData);
                
            }
        } else {
            dao.salvar(operacao);
            list.add(operacao);
        }
        return list;
        
    }
    
    public void addOperacao(Operacao operacao, BigDecimal valor) throws Exception {
        List<Operacao> list = processarOperacao(operacao, valor);
        switch (operacao.getCategoriaOperacao().getTipoDeOperacao()) {
            case DESPESA: {
                
                for (Operacao op : list) {
                    if (op.isParcelamento()) {
                        addOperacaoComParcelamento(op, valor);
                    } else {
                        addOperacaoSemParcelamento(op, valor);
                    }
                }
                break;
            }
            
            case RECEITA: {
                for (Operacao op : list) {
                    faturaController.addFaturaReceita(op, valor);
                }
                break;
                
            }
        }
        
    }
    
    public List<Operacao> consultarOperacoesDoDia(Date date) {
        return dao.consultarOperacoesDoDia(date);
    }
    
    @Override
    public void excluir(Operacao op) throws Exception {
        List<FaturaOperacao> fos = faturaController.consultarFaturaDa(op);
        op = gerenciar(op.getId());
        for (FaturaOperacao fo : fos) {
            faturaController.excluir(fo);
        }
        dao.excluir(op);
        
    }
}
