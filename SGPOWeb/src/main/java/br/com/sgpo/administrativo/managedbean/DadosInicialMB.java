/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.administrativo.managedbean;

import br.com.sgpo.administrativo.controller.ColaboradorController;
import br.com.sgpo.administrativo.modelo.Colaborador;
import br.com.sgpo.engenharia.projeto.Controller.NotificacaoProjetoController;
import br.com.sgpo.engenharia.projeto.Controller.ProjetoController;
import br.com.sgpo.engenharia.projeto.modelo.NotificacaoProjeto;
import br.com.sgpo.engenharia.projeto.modelo.Projeto;
import br.com.sgpo.utilitario.BeanGenerico;
import br.com.sgpo.utilitario.UtilitarioNavegacaoMB;
import br.com.sgpo.utilitarios.enumeration.Mes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.DualListModel;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

/**
 *
 * @author ari
 */
@Named
@ViewScoped
public class DadosInicialMB extends BeanGenerico implements Serializable {

    @Inject
    private ProjetoController projetoController;
    @Inject
    private NotificacaoProjetoController notificacaoProjetoController;
    @Inject
    private UtilitarioNavegacaoMB navegacaoMB;
    @Inject
    private ColaboradorController colaboradorController;

    private List<Projeto> listaDeProjetos;
    private List<NotificacaoProjeto> listaDeNotificacaoProjetos;

    private DualListModel<Colaborador> listDualColaboradores;
    private List<Colaborador> listaColaboradorSource;
    private List<Colaborador> listaColaboradorTarget;

    private NotificacaoProjeto notificacaoProjeto;
    private Projeto projeto;

    private List<String> listaString2;
    private LineChartModel lineModel;
    private Integer ano;

    @PostConstruct
    public void init() {
        try {
            listaDeProjetos = projetoController.consultarTodosAtivos();
            notificacaoProjeto = new NotificacaoProjeto();
            projeto = new Projeto();
            
            ano = 2015;
            createLineModels();
            
            listaString2 = new ArrayList<>();
            String a;
            for (int i = 0; i < 10; i++) {
                a = "Projeto: ".concat(String.valueOf(i + 1));
                a = "Obra: ".concat(String.valueOf(i + 1));
                listaString2.add(a);
            }
            listaColaboradorSource = colaboradorController.consultarTodosOrdenadorPor("nome");
            listaColaboradorTarget = new ArrayList<>();
            listDualColaboradores = new DualListModel<>(listaColaboradorSource, listaColaboradorTarget);
        } catch (Exception ex) {
            Logger.getLogger(DadosInicialMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addNotificacaoProjeto() {
        try {
            notificacaoProjeto.setColaborador(navegacaoMB.getUsuarioLogado().getColaborador());
            notificacaoProjeto.setData(new Date());
            notificacaoProjeto.setProjeto(projeto);
            notificacaoProjetoController.salvar(notificacaoProjeto,listDualColaboradores.getTarget());
            notificacaoProjeto = new NotificacaoProjeto();
            listaDeNotificacaoProjetos = notificacaoProjetoController.consultarTodos(projeto);
        } catch (Exception ex) {
            Logger.getLogger(DadosInicialMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarNotificacoes(Projeto p) {
        listaDeNotificacaoProjetos = notificacaoProjetoController.consultarTodos(p);
        projeto = p;
    }

    private void createLineModels() {

        lineModel = initCategoryModel();
        lineModel.setTitle("(Projetos | Obras)");
        lineModel.setLegendPosition("e");
        lineModel.setShowPointLabels(true);
        lineModel.getAxes().put(AxisType.X, new CategoryAxis("(Mes)"));
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setLabel("(Quantidades)");
        yAxis.setMin(0);
        yAxis.setMax(28);
    }

    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Projeto");
        boys.set(Mes.JANEIRO.getAbreviacao(), 10);
        boys.set(Mes.FEVEREIRO.getAbreviacao(), 15);
        boys.set(Mes.MARÇO.getAbreviacao(), 5);
        boys.set(Mes.ABRIL.getAbreviacao(), 8);
        boys.set(Mes.MAIO.getAbreviacao(), 3);
        boys.set(Mes.JUNHO.getAbreviacao(), 2);
        boys.set(Mes.JULHO.getAbreviacao(), 19);
        boys.set(Mes.AGOSTO.getAbreviacao(), 25);
        boys.set(Mes.SETEMBRO.getAbreviacao(), 13);
        boys.set(Mes.OUTUBRO.getAbreviacao(), 10);
        boys.set(Mes.NOVEMBRO.getAbreviacao(), 9);
        boys.set(Mes.DEZEMBRO.getAbreviacao(), 12);

        ChartSeries girls = new ChartSeries();
        girls.setLabel("Obras");
        girls.set(Mes.JANEIRO.getAbreviacao(), 8);
        girls.set(Mes.FEVEREIRO.getAbreviacao(), 5);
        girls.set(Mes.MARÇO.getAbreviacao(), 1);
        girls.set(Mes.ABRIL.getAbreviacao(), 3);
        girls.set(Mes.MAIO.getAbreviacao(), 3);
        girls.set(Mes.JUNHO.getAbreviacao(), 5);
        girls.set(Mes.JULHO.getAbreviacao(), 8);
        girls.set(Mes.AGOSTO.getAbreviacao(), 10);
        girls.set(Mes.SETEMBRO.getAbreviacao(), 5);
        girls.set(Mes.OUTUBRO.getAbreviacao(), 2);
        girls.set(Mes.NOVEMBRO.getAbreviacao(), 5);
        girls.set(Mes.DEZEMBRO.getAbreviacao(), 3);

        model.addSeries(boys);
        model.addSeries(girls);

        return model;
    }

    public List<Projeto> getListaDeProjetos() {
        return listaDeProjetos;
    }

    public LineChartModel getLineModel() {
        return lineModel;
    }

    public Integer getAno() {
        return ano;
    }

    public List<Integer> getListaDeAnos() {
        return Mes.ABRIL.getAnos();
    }

    public List<String> getListaString2() {
        return listaString2;
    }

    public List<NotificacaoProjeto> getListaDeNotificacaoProjetos() {
        return listaDeNotificacaoProjetos;
    }

    public NotificacaoProjeto getNotificacaoProjeto() {
        return notificacaoProjeto;
    }

    public void setNotificacaoProjeto(NotificacaoProjeto notificacaoProjeto) {
        this.notificacaoProjeto = notificacaoProjeto;
    }

    @Override
    protected Map<String, Object> getCampo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DualListModel<Colaborador> getListDualColaboradores() {
        return listDualColaboradores;
    }

    public void setListDualColaboradores(DualListModel<Colaborador> listDualColaboradores) {
        this.listDualColaboradores = listDualColaboradores;
    }

    
}
