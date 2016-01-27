/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.managedbean;

import br.com.sgpo.administrativo.controller.ClienteController;
import br.com.sgpo.administrativo.controller.ColaboradorController;
import br.com.sgpo.administrativo.modelo.Cliente;
import br.com.sgpo.administrativo.modelo.Colaborador;
import br.com.sgpo.engenharia.Controller.ProjetoController;
import br.com.sgpo.engenharia.Controller.TipoProjetoController;
import br.com.sgpo.engenharia.enumeration.FaseProjeto;
import br.com.sgpo.engenharia.modelo.Projeto;
import br.com.sgpo.engenharia.modelo.TipoProjeto;
import br.com.sgpo.utilitario.BeanGenerico;
import java.io.Serializable;
import java.util.ArrayList;
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
public class ProjetoMB extends BeanGenerico implements Serializable {

    @Inject
    private ProjetoController projetoController;
   
    @Inject
    private TipoProjetoController tipoProjetoController;
    
    private Projeto projeto;
    private List<TipoProjeto> listaTipoProjetos;

    @PostConstruct
    @Override
    public void init() {
        try {
            projeto = (Projeto) lerRegistroDaSessao("projeto");
            if (projeto == null) {
                projeto = new Projeto();
                
            }
           
            listaTipoProjetos = tipoProjetoController.consultarTodosOrdenadorPor("nome");
//            criarListaDeCamposDaConsulta();
        } catch (Exception ex) {
            Logger.getLogger(ProjetoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void setarCliente(Cliente c){
        projeto.setCliente(c);
    }
    public void setarResponsavel(Colaborador c){
        projeto.setResponsavel(c);
    }
    
    
    public List<TipoProjeto> getListaTipoProjetos() {
        return listaTipoProjetos;
    }


    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }
    
    
    public FaseProjeto[] getListaDeFasesProjeto(){
        return FaseProjeto.values();
    }

    @Override
    protected Map<String, Object> getCampo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
