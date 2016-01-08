/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.seguranca.controller;

import br.com.sgpo.seguranca.DAO.UsuarioDAO;
import br.com.sgpo.seguranca.modelo.Usuario;
import br.com.sgpo.utilitario.ControllerGenerico;
import br.com.sgpo.utilitarios.CriptografiaSenha;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ari
 */
@Stateless
public class UsuarioController extends ControllerGenerico<Usuario, Long> implements Serializable{

    @Inject
    private UsuarioDAO dao;
    
    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }
    
    
    @Override
    public void salvar(Usuario u) throws Exception{
        u.setSenha("sgpoweb");
        dao.atualizar(u);
    }

    public void ativarOuDesativar(Usuario usuario) throws Exception {
        if (usuario.isAtivo()) {
            usuario.setAtivo(false);
        }else{
            usuario.setAtivo(true);
        }
        dao.atualizar(usuario);
    }

    public void alterarSenha(Usuario usuario,String senhaAtual, String novaSenha, String confirmaSenha) throws Exception {
        senhaAtual = CriptografiaSenha.criptografarSenha(senhaAtual);
        if (usuario.getSenha().equals(senhaAtual)) {
            if (novaSenha.equals(confirmaSenha)) {
                usuario.setSenha(novaSenha);
                dao.atualizar(usuario);
            }
        }
    }

    public Usuario usuarioLogin(String usr) {
        return dao.usuarioLogin(usr);
    }
}
