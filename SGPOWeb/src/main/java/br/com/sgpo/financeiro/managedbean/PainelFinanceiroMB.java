/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.financeiro.managedbean;

import br.com.sgpo.financeiro.controller.FaturaController;
import br.com.sgpo.financeiro.dto.AnosRegistradosDTO;
import br.com.sgpo.utilitario.mensagens.MensagensUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.extensions.component.gchart.model.GChartModel;
import org.primefaces.extensions.component.gchart.model.GChartModelBuilder;
import org.primefaces.extensions.component.gchart.model.GChartModelRow;
import org.primefaces.extensions.component.gchart.model.GChartType;
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
    
      private GChartModel chartModel; 
    
    private BarChartModel visaoOperacaoAnual;
    private BarChartModel visaoOperacaoMes;
    
    private int ano;
    
    @PostConstruct
    public void init() {
        listaAnosRegistados = faturaController.consultarAnosRegistrados();
        ano = 2016;
        criarVisaoOperacoesAnual();
        criarVisaoOperacoesMensal();
         
        
      
          
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

    public GChartModel getChartModel() {
        return chartModel;
    }
    
    
    
}
