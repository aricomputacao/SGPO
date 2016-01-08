/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.utilitario;

import br.com.sgpo.seguranca.controller.UsuarioController;
import br.com.sgpo.seguranca.managedbean.UsuarioMB;
import br.com.sgpo.seguranca.modelo.Usuario;
import br.com.sgpo.utilitario.mensagens.MensagensUtil;
import br.com.sgpo.utilitarios.CriptografiaSenha;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author ari
 */
@Named
@SessionScoped
public class UtilitarioNavegacaoMB implements Serializable {

    @Inject
    private UsuarioController usuarioController;
    private Usuario usuarioLogado;
    private String senhaAtual;
    private String novaSenha;
    private String confirmaSenha;

    @PostConstruct
    public void init() {
        usuarioLogado = usuarioController.usuarioLogin(getContexto().getRemoteUser());
        if (usuarioLogado.getId() == null) {
            logout();
        }
    }

    public void alterarSenha() {
        try {
            usuarioController.alterarSenha(usuarioLogado, senhaAtual, novaSenha, confirmaSenha);
            novaSenha = "";
            confirmaSenha = "";
            senhaAtual = "";
            MensagensUtil.enviarMessageParamentroInfo(MensagensUtil.REGISTRO_ATUALIZADO, usuarioLogado.getNomeDoColaborador());
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(UsuarioMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void validateSenhaAtual(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        senhaAtual = (String) value;
        if (!CriptografiaSenha.criptografarSenha(senhaAtual).equals(usuarioLogado.getSenha())) {
            senhaAtual = "";
            RequestContext.getCurrentInstance().update("@(.ui-password)");
            RequestContext.getCurrentInstance().execute("PF('sen').focus();");
            MensagensUtil.enviarMessageWarn(MensagensUtil.SENHA_INVALIDA);
        }

    }

    public String logout() {
        return getContexto().getRequestContextPath() + "/j_spring_security_logout";
    }

    public static ExternalContext getContexto() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext external = context.getExternalContext();
        return external;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

}
