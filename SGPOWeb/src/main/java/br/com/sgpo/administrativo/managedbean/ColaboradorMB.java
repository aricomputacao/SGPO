/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.administrativo.managedbean;

import br.com.sgpo.administrativo.controller.ColaboradorController;
import br.com.sgpo.administrativo.modelo.Cargo;
import br.com.sgpo.administrativo.modelo.Colaborador;
import br.com.sgpo.utilitario.BeanGenerico;
import br.com.sgpo.utilitario.mensagens.MensagensUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Giancarlo
 */
@Named
@ViewScoped
public class ColaboradorMB extends BeanGenerico implements Serializable {

    private ColaboradorController colaboradorcontroller;
    private Colaborador colaborador;
    private List<Colaborador> listaDeColaborador;

    @PostConstruct
    @Override
    public void init() {
        try {
            criarListaDeCamposDaConsulta();
            colaborador = (Colaborador) lerRegistroDaSessao("colaborador");
            if(colaborador == null){
                colaborador = new Colaborador();
                colaborador.setCargo(new Cargo());
            }
            listaDeColaborador = new ArrayList<>();
        } catch (Exception ex) {
            Logger.getLogger(ColaboradorMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void salvar() {
        try {
            colaboradorcontroller.salvar(colaborador);
            MensagensUtil.enviarMessageParamentroInfo(MensagensUtil.REGISTRO_SUCESSO, colaborador.getNome());
            init();
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(ColaboradorMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Map<String, Object> getCampo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
