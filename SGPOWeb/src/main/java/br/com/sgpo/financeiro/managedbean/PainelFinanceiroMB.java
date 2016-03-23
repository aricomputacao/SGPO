/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.financeiro.managedbean;

import br.com.sgpo.financeiro.controller.FaturaController;
import br.com.sgpo.financeiro.dto.AnosRegistradosDTO;
import br.com.sgpo.financeiro.dto.DemonatrativoFinanceiroAnualDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
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

    private List<DemonatrativoFinanceiroAnualDTO> listaDemonatrativoFinanceiroAnual;
    private List<AnosRegistradosDTO> listaAnosRegistados;

    private BarChartModel barModel;

    @PostConstruct
    public void init() {
        createBarModels();
    }

   

    private BarChartModel initBarModel() {
        listaAnosRegistados = new ArrayList<>();
        listaAnosRegistados.add(new AnosRegistradosDTO(2015));
        listaAnosRegistados.add(new AnosRegistradosDTO(2016));

        return faturaController.graficoReceitaDespesaAnual(listaAnosRegistados);
    }

    private void createBarModels() {
        createBarModel();
    }

    private void createBarModel() {
        barModel = initBarModel();

        barModel.setTitle("Visão das Operações Anuais");
        barModel.setLegendPosition("ne");
        
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Ano");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Tipo de Operaçao");
        yAxis.setMin(150);
        yAxis.setMax(15000);
    }
    
     public BarChartModel getBarModel() {
        return barModel;
    }

}
