/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.seguranca.controller;

import br.com.sgpo.administrativo.DAO.CargoDAO;
import br.com.sgpo.administrativo.DAO.ColaboradorDAO;
import br.com.sgpo.administrativo.controller.CargoController;
import br.com.sgpo.administrativo.controller.ColaboradorController;
import br.com.sgpo.administrativo.enumeration.TipoCargo;
import br.com.sgpo.administrativo.modelo.Cargo;
import br.com.sgpo.administrativo.modelo.Colaborador;
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
public class UsuarioController extends ControllerGenerico<Usuario, Long> implements Serializable {

    @Inject
    private UsuarioDAO dao;
    @Inject
    private ColaboradorDAO colaboradorDAO;
    @Inject
    private CargoDAO cargoDAO;

    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    @Override
    public void salvar(Usuario u) throws Exception {
        u.setSenha("sgpoweb");
        dao.atualizar(u);
    }

    public void ativarOuDesativar(Usuario usuario) throws Exception {
        if (usuario.isAtivo()) {
            usuario.setAtivo(false);
        } else {
            usuario.setAtivo(true);
        }
        dao.atualizar(usuario);
    }

    public void alterarSenha(Usuario usuario, String senhaAtual, String novaSenha, String confirmaSenha) throws Exception {
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

    public void criarUsuarioAdministrado() throws Exception {
        Cargo cargo = new Cargo();
        cargo.setDescricao("Administrador do Sistema");
        cargo.setNome("Administrador do Sistema");
        cargo.setTipoCargo(TipoCargo.OUTRO);
        cargo = cargoDAO.atualizarGerenciar(cargo);
        System.out.println("-------------------------------------------Cargo Admin incluido---------------------------------------");

        Colaborador col = new Colaborador();
        col.setAtivo(false);
        col.setCargo(cargo);
        col.setCpf("11111111111111");
        col.setEmail("adm@ss.com");
        col.setNome("Administrador do Sistema");
        col = colaboradorDAO.atualizarGerenciar(col);
        System.out.println("-------------------------------------------Colaborador Admin incluido---------------------------------------");

        Usuario usuario = new Usuario();
        usuario.setColaborador(col);
        usuario.setAtivo(true);
        usuario.setLogin("adm");
        usuario.setSenha("1234");
        System.out.println("-------------------------------------------Usuário  Admin incluido---------------------------------------");

        dao.atualizar(usuario);
    }
}
