/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.financeiro.managedbean;

import br.com.sgpo.financeiro.controller.CategoriaOperacaoController;
import br.com.sgpo.financeiro.controller.FaturaController;
import br.com.sgpo.financeiro.controller.OperacaoController;
import br.com.sgpo.financeiro.enumeration.PeriodoRecorrencia;
import br.com.sgpo.financeiro.enumeration.TipoDeOperacao;
import br.com.sgpo.financeiro.modelo.CategoriaOperacao;
import br.com.sgpo.financeiro.modelo.FaturaOperacao;
import br.com.sgpo.financeiro.modelo.Operacao;
import br.com.sgpo.utilitario.BeanGenerico;
import br.com.sgpo.utilitario.mensagens.MensagensUtil;
import br.com.sgpo.utilitarios.enumeration.Mes;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ari
 */
@Named
@ViewScoped
public class OperacaoMB extends BeanGenerico implements Serializable {

    @Inject
    private OperacaoController operacaoController;
    @Inject
    private FaturaController faturaController;
    @Inject
    private CategoriaOperacaoController categoriaOperacaoController;

    private Operacao operacao;
    private TipoDeOperacao tipoDeOperacao;

    private List<Operacao> listaDeOperacaos;
    private List<FaturaOperacao> listaDeFaturas;
    private List<CategoriaOperacao> listaCategoriaOperacaos;

    private boolean renderReceita;
    private boolean renderRecorrencia;
    private boolean renderParcelamento;
    private BigDecimal valor;

    @PostConstruct
    @Override
    public void init() {
        try {
            criarListaDeCamposDaConsulta();
            operacao = (Operacao) lerRegistroDaSessao("operacao");
            if (operacao == null) {
                operacao = new Operacao();
                valor = BigDecimal.ZERO;
                tipoDeOperacao = TipoDeOperacao.RECEITA;
                renderReceita = false;

            } else {
                listaDeFaturas = faturaController.consultarFaturaDa(operacao);
                valor = operacao.getValorDaOperacao(listaDeFaturas);
                tipoDeOperacao = operacao.getCategoriaOperacao().getTipoDeOperacao();
            }
            processarRecorrencia();
            processarParcelamento();
            processarTipoOperacao();
            listaDeOperacaos = operacaoController.consultarOperacoesDoDia(new Date());
        } catch (Exception ex) {
            Logger.getLogger(OperacaoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salvar() {
        try {
            operacaoController.addOperacao(operacao,valor);
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_SUCESSO);
            init();
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(OperacaoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void excluirFatura(FaturaOperacao ft){
        try {
            Operacao o = ft.getOperacao();
            faturaController.excluir(ft);
            listaDeFaturas = faturaController.consultarFaturaDa(o);
            MensagensUtil.enviarMessageInfo(MensagensUtil.EXCLUIR_SUCESSO);
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.EXCLUIR_FALHA);
            Logger.getLogger(OperacaoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void excluirOperacao(Operacao op){
        try {
            operacaoController.excluir(op);
            init();
            MensagensUtil.enviarMessageInfo(MensagensUtil.EXCLUIR_SUCESSO);
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.EXCLUIR_FALHA);
            Logger.getLogger(OperacaoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public void consulta() {
        try {
            listaDeOperacaos = operacaoController.consultarLike(getCampoConsuta(), getValorCampoConsuta());
        } catch (Exception ex) {
            Logger.getLogger(OperacaoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarFatura(Operacao op){
        listaDeFaturas = faturaController.consultarFaturaDa(op);
    }
    
    public void processarTipoOperacao() {
        listaCategoriaOperacaos = categoriaOperacaoController.consultar(tipoDeOperacao);
        renderReceita = tipoDeOperacao.equals(TipoDeOperacao.RECEITA);
        
    }

    public void processarRecorrencia() {
        renderRecorrencia = operacao.isRecorrencia() == false;
    }

    public void processarParcelamento() {
        renderParcelamento = operacao.isParcelamento() == false;
    }

    @Override
    protected Map<String, Object> getCampo() {
        Map<String, Object> map = new HashMap<>();
        map.put("Tipo", "categoriaOperacao.tipoDeOperacao");
        map.put("Categoria", "categoriaOperacao.nome");
        return map;
    }

    public List<Operacao> getListaDeOperacaos() {
        return listaDeOperacaos;
    }

    public List<FaturaOperacao> getListaDeFaturas() {
        return listaDeFaturas;
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }

    public TipoDeOperacao[] getListaDeTiposDeOperacao() {
        return TipoDeOperacao.values();
    }

    public PeriodoRecorrencia[] getListaPeriodoRecorrencia() {
        return PeriodoRecorrencia.values();
    }

    public List<CategoriaOperacao> getListaCategoriaOperacaos() {
        return listaCategoriaOperacaos;
    }

    public TipoDeOperacao getTipoDeOperacao() {
        return tipoDeOperacao;
    }

    public void setTipoDeOperacao(TipoDeOperacao tipoDeOperacao) {
        this.tipoDeOperacao = tipoDeOperacao;
    }

    public boolean isRenderReceita() {
        return renderReceita;
    }

    public void setRenderReceita(boolean renderReceita) {
        this.renderReceita = renderReceita;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public boolean isRenderRecorrencia() {
        return renderRecorrencia;
    }

    public boolean isRenderParcelamento() {
        return renderParcelamento;
    }
    
    public Mes[] getListaDeMeses(){
        return Mes.values();
    }

}
