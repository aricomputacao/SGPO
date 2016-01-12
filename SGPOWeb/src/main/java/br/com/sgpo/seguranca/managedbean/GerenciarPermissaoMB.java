/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.seguranca.managedbean;

import br.com.sgpo.administrativo.controller.EmpresaController;
import br.com.sgpo.administrativo.modelo.Empresa;
import br.com.sgpo.seguranca.controller.PermissaoController;
import br.com.sgpo.seguranca.controller.TarefaController;
import br.com.sgpo.seguranca.controller.UsuarioController;
import br.com.sgpo.seguranca.modelo.Permissao;
import br.com.sgpo.seguranca.modelo.Tarefa;
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
public class GerenciarPermissaoMB extends BeanGenerico implements Serializable {

    @Inject
    private PermissaoController permissaoController;
    @Inject
    private UsuarioController usuarioController;
    @Inject
    private TarefaController tarefaController;
    @Inject
    private EmpresaController empresaController;
    @Inject
    private UtilitarioNavegacaoMB navegacaoMB;

    private List<Permissao> listaDePermissao;
    private List<Tarefa> listaDeTarefas;
    private List<Empresa> listaDeEmpresas;

    private Permissao permissao;
    private Usuario usuario;
    private Usuario usuarioClonado;

    @PostConstruct
    @Override
    public void init() {
        try {
            permissao = new Permissao();
            usuario = new Usuario();
            usuario.setEmpresas(new ArrayList<>());
            usuarioClonado = new Usuario();
            listaDeTarefas = tarefaController.consultarTodosOrdenadorPor("modulo.nome");
        } catch (Exception ex) {
            Logger.getLogger(GerenciarPermissaoMB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void salvar() {
        try {
            permissaoController.salvarouAtualizar(permissao);
            listaDePermissao = permissaoController.buscarPermissao(usuario);
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_SUCESSO);
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(GerenciarPermissaoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void clonarPermissoes(Usuario u) {
        try {
            permissaoController.clonarAcessos(usuario, u);
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_SUCESSO);
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(GerenciarPermissaoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Map<String, Object> getCampo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Permissao> getListaDePermissao() {
        return listaDePermissao;
    }

    public void setarUsuario(Usuario u) {
        try {
            usuario = u;
            listaDePermissao = permissaoController.buscarPermissao(usuario);
            listaDeEmpresas = empresaController.consultarTodosOrdenadorPor("nome");
        } catch (Exception ex) {
            Logger.getLogger(GerenciarPermissaoMB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addEmpresa(Empresa e) {
        try {
            usuario.addEmpresa(e);
            usuarioController.atualizar(usuario);
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_SUCESSO);
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(GerenciarPermissaoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delEmpresa(Empresa e) {
        try {
            usuario.removerEmpresa(e);
            usuarioController.atualizar(usuario);
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_SUCESSO);
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(GerenciarPermissaoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean renderBtnAdd(Empresa e) {
        return usuario.getEmpresas().contains(e);
    }

    public void setarPermissao(Permissao p) {
        permissao = p;
    }

    public List<Tarefa> getListaDeTarefas() {
        return listaDeTarefas;
    }

    public List<Empresa> getListaDeEmpresas() {
        return listaDeEmpresas;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioClonado() {
        return usuarioClonado;
    }

    public void setUsuarioClonado(Usuario usuarioClonado) {
        this.usuarioClonado = usuarioClonado;
    }

}
