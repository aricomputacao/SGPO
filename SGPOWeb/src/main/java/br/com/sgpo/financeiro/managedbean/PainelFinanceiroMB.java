/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.financeiro.managedbean;

import br.com.sgpo.financeiro.controller.FaturaController;
import br.com.sgpo.financeiro.enumeration.TipoDeOperacao;
import br.com.sgpo.financeiro.modelo.FaturaOperacao;
import br.com.sgpo.utilitario.relatorio.RelatorioSession;
import br.com.sgpo.utilitarios.enumeration.Mes;
import br.com.sgpo.utilitarios.relatorios.AssistentedeRelatorio;
import br.com.sgpo.utilitarios.relatorios.PastasRelatorio;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.BarChartModel;

/**
 *
 * @author ari
 */
@Named
@ViewScoped
public class PainelFinanceiroMB implements Serializable {

    @Inject
    private FaturaController faturaController;

    private List<Integer> listaAnosRegistados;
    private List<FaturaOperacao> listaDeFaturas;

    private BarChartModel visaoOperacaoAnual;
    private BarChartModel visaoOperacaoMes;

    private int ano;
    private Mes mesReferencia;
    private TipoDeOperacao tipoDeOperacao;

    @PostConstruct
    public void init() {
        try {
            listaAnosRegistados = faturaController.consultarAnosRegistrados();
            ano = 2016;

            criarVisaoOperacoesAnual();
            criarVisaoOperacoesMensal();
        } catch (Exception ex) {
            Logger.getLogger(PainelFinanceiroMB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void gerarImpressaoDespesaMensa() {
        try {

            listaDeFaturas = faturaController.consultarPor(mesReferencia,ano,TipoDeOperacao.DESPESA);
            Map<String, Object> m = new HashMap<>();
            m.put("mes", " "+mesReferencia.toString()+" / "+String.valueOf(ano));
            byte[] rel = new AssistentedeRelatorio().relatorioemByte(listaDeFaturas, m, PastasRelatorio.RESOURCE_FINANCEIRO, PastasRelatorio.REL_DEMONSTRATIVO_SINTETICO_DESPESA_MENSA, "");
            RelatorioSession.setBytesRelatorioInSession(rel);
        } catch (Exception e) {
//            erroCliente.adicionaErro(e);
        }
    }

    private void criarVisaoOperacoesAnual() {
        visaoOperacaoAnual = faturaController.graficoReceitaDespesaAnual();
    }

    public void criarVisaoOperacoesMensal() {
        visaoOperacaoMes = faturaController.graficoReceitaDespesaMensal(ano);
    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getSource().getClass().getName() + ", Series Index:" + event.getSeriesIndex());
        System.out.println(event.getSource().getClass().getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public BarChartModel getVisaoOperacaoAnual() {
        return visaoOperacaoAnual;
    }

    public BarChartModel getVisaoOperacaoMes() {
        return visaoOperacaoMes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public List<Integer> getListaAnosRegistados() {
        return listaAnosRegistados;
    }
    
    public Mes[] getListaDeMeses(){
        return Mes.values();
    }

    public Mes getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(Mes mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public List<FaturaOperacao> getListaDeFaturas() {
        return listaDeFaturas;
    }

    public TipoDeOperacao getTipoDeOperacao() {
        return tipoDeOperacao;
    }

    public void setTipoDeOperacao(TipoDeOperacao tipoDeOperacao) {
        this.tipoDeOperacao = tipoDeOperacao;
    }

    
}
