/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.administrativo.managedbean;

import br.com.sgpo.administrativo.controller.ColaboradorController;
import br.com.sgpo.administrativo.modelo.Colaborador;
import br.com.sgpo.engenharia.obra.Controller.ObraController;
import br.com.sgpo.engenharia.obra.modelo.Obra;
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
    @Inject
    private ObraController obraController;

    private List<Projeto> listaDeProjetos;
    private List<NotificacaoProjeto> listaDeNotificacaoProjetos;
    private List<Obra> listaDeObras;

    private DualListModel<Colaborador> listDualColaboradores;
    private List<Colaborador> listaColaboradorSource;
    private List<Colaborador> listaColaboradorTarget;

    private NotificacaoProjeto notificacaoProjeto;
    private Projeto projeto;


    @PostConstruct
    @Override
    public void init() {
        try {
            listaDeProjetos = projetoController.consultarTodosAtivos();
            notificacaoProjeto = new NotificacaoProjeto();
            projeto = new Projeto();
            
            listaDeObras = obraController.consultarObrasEmAndamento();
            
          
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

   
    public List<Projeto> getListaDeProjetos() {
        return listaDeProjetos;
    }

 
    public List<Obra> getListaDeObras() {
        return listaDeObras;
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
