/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.obra.managedbean;

import br.com.sgpo.engenharia.obra.Controller.EtapaController;
import br.com.sgpo.engenharia.obra.modelo.Etapa;
import br.com.sgpo.utilitario.BeanGenerico;
import br.com.sgpo.utilitario.mensagens.MensagensUtil;
import java.io.Serializable;
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
public class EtapaMB extends BeanGenerico implements Serializable{

    @Inject
    private EtapaController etapaController;
    
    private Etapa etapa;
    
    private List<Etapa> listaDeEtapas;
    
    @PostConstruct
    @Override
    public void init() {
        try {
            etapa = new Etapa();
            listaDeEtapas = etapaController.consultarTodosOrdenadorPor("nome");
        } catch (Exception ex) {
            Logger.getLogger(EtapaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addEtapa(){
        try {
            etapaController.salvar(etapa);
            init();
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_SUCESSO);
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(EtapaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected Map<String, Object> getCampo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Etapa getEtapa() {
        return etapa;
    }

    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }

    public List<Etapa> getListaDeEtapas() {
        return listaDeEtapas;
    }
    
    
}
