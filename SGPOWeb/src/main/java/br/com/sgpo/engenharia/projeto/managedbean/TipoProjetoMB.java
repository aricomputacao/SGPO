/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.projeto.managedbean;

import br.com.sgpo.engenharia.projeto.Controller.TipoProjetoController;
import br.com.sgpo.engenharia.projeto.modelo.TipoProjeto;
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
public class TipoProjetoMB extends BeanGenerico implements Serializable{

    @Inject
    private TipoProjetoController tipoProjetoController;
    private TipoProjeto tipoProjeto;
    private List<TipoProjeto> listaDeTiposProjetos;
    
    @PostConstruct
    @Override
    public void init() {
        try {
            tipoProjeto = new TipoProjeto();
            listaDeTiposProjetos = tipoProjetoController.consultarTodosOrdenadorPor("nome");
        } catch (Exception ex) {
            Logger.getLogger(TipoProjetoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void salvar(){
        try {
            tipoProjetoController.salvar(tipoProjeto);
            init();
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_SUCESSO);
        } catch (Exception ex) {
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(TipoProjetoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Map<String, Object> getCampo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<TipoProjeto> getListaDeTiposProjetos() {
        return listaDeTiposProjetos;
    }

    public TipoProjeto getTipoProjeto() {
        return tipoProjeto;
    }

    public void setTipoProjeto(TipoProjeto tipoProjeto) {
        this.tipoProjeto = tipoProjeto;
    }
    
    
}
