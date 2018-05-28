/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.financeiro.managedbean;

import br.com.sgpo.financeiro.controller.CategoriaOperacaoController;
import br.com.sgpo.financeiro.enumeration.Cor;
import br.com.sgpo.financeiro.enumeration.TipoDeOperacao;
import br.com.sgpo.financeiro.modelo.CategoriaOperacao;
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
public class CategoriaOperacaoMB extends BeanGenerico implements Serializable{

    @Inject
    private CategoriaOperacaoController categoriaOperacaoController;
    
    private CategoriaOperacao categoriaOperacao;
    
    private List<CategoriaOperacao> listaDeCategoriaOperacaos;
    
    @PostConstruct
    @Override
    public void init() {
        try {
            categoriaOperacao = new CategoriaOperacao();
            listaDeCategoriaOperacaos = categoriaOperacaoController.consultarTodosOrdenadorPor("tipoDeOperacao");
        } catch (Exception ex) {
            Logger.getLogger(CategoriaOperacaoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void salvar(){
        try {
            categoriaOperacaoController.salvar(categoriaOperacao);
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_SUCESSO);
            init();
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(CategoriaOperacaoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    protected Map<String, Object> getCampo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public CategoriaOperacao getCategoriaOperacao() {
        return categoriaOperacao;
    }

    public void setCategoriaOperacao(CategoriaOperacao categoriaOperacao) {
        this.categoriaOperacao = categoriaOperacao;
    }

    public List<CategoriaOperacao> getListaDeCategoriaOperacaos() {
        return listaDeCategoriaOperacaos;
    }
    
    
    public TipoDeOperacao[] getListaDeTiposDeOperacao(){
       return TipoDeOperacao.values();
    }
    
    public Cor[] getListaDeCores(){
        return Cor.values();
    }
}
