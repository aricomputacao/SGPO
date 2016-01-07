/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.seguranca.managedbean;

import br.com.sgpo.administrativo.controller.ColaboradorController;
import br.com.sgpo.administrativo.modelo.Colaborador;
import br.com.sgpo.seguranca.controller.UsuarioController;
import br.com.sgpo.seguranca.modelo.Usuario;
import br.com.sgpo.utilitario.BeanGenerico;
import br.com.sgpo.utilitario.UtilitarioNavegacaoMB;
import br.com.sgpo.utilitario.mensagens.MensagensUtil;
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
public class UsuarioMB extends BeanGenerico implements Serializable{

    @Inject
    private UtilitarioNavegacaoMB navegacaoMB;
    @Inject
    private UsuarioController usuarioController;
    @Inject
    private ColaboradorController colaboradorController;
    
    private List<Usuario> listaUsuarios;
    private List<Colaborador> listaColaborador;
    
    private Usuario usuario;
    
    @PostConstruct
    @Override
    public void init() {
        usuario = (Usuario) lerRegistroDaSessao("usuario");
        if (usuario == null) {
            usuario = new Usuario();
            usuario.setAtivo(true);
        }
        listaUsuarios = new ArrayList<>();
        listaColaborador = new ArrayList<>();
    }
    
    
    public void salvar(){
        try {
            usuarioController.salvarouAtualizar(usuario);
            init();
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_SUCESSO);
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setarColaborador(Colaborador c){
        usuario.setColaborador(c);
    }
    
    public void consultarFornecedor(){
        try {
            listaColaborador = colaboradorController.consultarLike(getCampoConsuta(), getValorCampoConsuta());
        } catch (Exception ex) {
            Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void consultarUsuarios(){
        try {
            listaUsuarios = usuarioController.consultarLike(getCampoConsuta(), getValorCampoConsuta());
        } catch (Exception ex) {
            Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Map<String, Object> getCampo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public List<Colaborador> getListaColaborador() {
        return listaColaborador;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
