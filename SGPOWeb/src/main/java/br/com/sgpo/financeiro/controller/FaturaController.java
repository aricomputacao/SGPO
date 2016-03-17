/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.financeiro.controller;

import br.com.sgpo.financeiro.DAO.FaturaOperacaoDAO;
import br.com.sgpo.financeiro.modelo.FaturaOperacao;
import br.com.sgpo.financeiro.modelo.Operacao;
import br.com.sgpo.utilitario.ControllerGenerico;
import java.io.Serializable;
import java.math.BigDecimal;
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
public class FaturaController extends ControllerGenerico<FaturaOperacao, Long> implements Serializable {

    @Inject
    private FaturaOperacaoDAO dao;

    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    public void addFaturaReceita(Operacao operacao, BigDecimal valor) throws Exception {
        Date ultimaData = operacao.getDateOperacao();
        if (operacao.isRecorrencia()) {
            for (int i = 0; i < operacao.getTempoRecorrencia(); i++) {
                FaturaOperacao fatura = new FaturaOperacao();
                fatura.setDataVencimento(ultimaData);
                fatura.setOperacao(operacao);
                fatura.setValor(valor);
                dao.salvar(fatura);
                ultimaData = operacao.getPeriodoRecorrencia().processaData(ultimaData);
            }
        } else {
            FaturaOperacao fatura = new FaturaOperacao();
            fatura.setDataVencimento(ultimaData);
            fatura.setOperacao(operacao);
            fatura.setValor(valor);
            dao.salvar(fatura);
        }
    }

    public List<FaturaOperacao> consultarFaturaDa(Operacao o) {
        return dao.consultarFaturaDa(o);

    }

}
