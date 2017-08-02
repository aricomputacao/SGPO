/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.obra.managedbean;

import br.com.sgpo.engenharia.enumeration.TipoEquipamento;
import br.com.sgpo.engenharia.obra.Controller.EquipamentoController;
import br.com.sgpo.engenharia.obra.modelo.Equipamento;
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
public class EquipamentoMB extends BeanGenerico implements Serializable {

    @Inject
    private EquipamentoController equipamentoController;

    private List<Equipamento> listaDeEquipamentos;
    private Equipamento equipamento;

    @PostConstruct
    @Override
    public void init() {
        try {

            equipamento = new Equipamento();
            listaDeEquipamentos = equipamentoController.consultarTodosOrdenadorPor("nome");

        } catch (Exception ex) {
            Logger.getLogger(EquipamentoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salvar() {
        try {
            equipamentoController.salvar(equipamento);
            init();
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_SUCESSO);
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(EquipamentoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

     public void consultarEquipamentos(){
        try {
            listaDeEquipamentos = equipamentoController.consultarLike("nome", getValorCampoConsuta());
        } catch (Exception ex) {
            Logger.getLogger(ItemMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public TipoEquipamento[] getListaDeTipoDeEquipamentos() {
        return TipoEquipamento.values();
    }

    @Override
    protected Map<String, Object> getCampo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public List<Equipamento> getListaDeEquipamentos() {
        return listaDeEquipamentos;
    }

}
