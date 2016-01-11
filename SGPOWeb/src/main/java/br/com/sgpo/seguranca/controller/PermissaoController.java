/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.seguranca.controller;

import br.com.sgpo.seguranca.DAO.PermissaoDAO;
import br.com.sgpo.seguranca.modelo.Permissao;
import br.com.sgpo.seguranca.modelo.Tarefa;
import br.com.sgpo.seguranca.modelo.Usuario;
import br.com.sgpo.utilitario.ControllerGenerico;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ari
 */
@Stateless
public class PermissaoController extends ControllerGenerico<Permissao, Long> implements Serializable {

    @Inject
    private PermissaoDAO dao;
    @Inject
    private TarefaController tarefaController;
    @Inject
    private UsuarioController usuarioController;

    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    public void criarPermissaoUsuarioAdmim() throws Exception {
        System.out.println("--------------------------------------Criando Permissões ADM------------------------------------------");
        List<Tarefa> listaTarefas = tarefaController.consultarTodosOrdenadorPor("id");
        Usuario usuario = usuarioController.usuarioLogin("adm");
        
        for (Tarefa taf : listaTarefas) {
            Permissao per = new Permissao();
            per.setTarefa(taf);
            per.setUsuario(usuario);
            per.setConsultar(true);
            per.setEditar(true);
            per.setExcluir(true);
            per.setIncluir(true);
            
            dao.atualizar(per);
        }

    }

}
