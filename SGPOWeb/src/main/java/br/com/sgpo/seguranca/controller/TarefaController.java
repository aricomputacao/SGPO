/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.seguranca.controller;

import br.com.sgpo.seguranca.DAO.TarefaDAO;
import br.com.sgpo.seguranca.modelo.Modulo;
import br.com.sgpo.seguranca.modelo.Tarefa;
import br.com.sgpo.utilitario.ConfiguracaoSistemaMB;
import br.com.sgpo.utilitario.ControllerGenerico;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ari
 */
@Stateless
public class TarefaController extends ControllerGenerico<Tarefa, Long> implements Serializable {

    @Inject
    private TarefaDAO dao;
    @Inject
    private ModuloController moduloController;

    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    public List<Tarefa> consultarPorModulo(String nomeModulo) throws Exception {
        if ("".equals(nomeModulo) || nomeModulo == null ) {
            return dao.consultarTodosOrdenadosPor("modulo.nome");
        } else {
            return dao.consultarLike("modulo.nome", nomeModulo);
        }
    }

    public void criarTarefas() throws Exception {
        criarTarefaModSeguranca();
        criarTarefaModAdministrativo();
    }

    private void criarTarefaModSeguranca() throws Exception {
        System.out.println("--------------------------------------Criando Tarefas Mod Segurança------------------------------------------");
        ResourceBundle bundle = ResourceBundle.getBundle("br.com.sisdelta.arquivos.seguranca");
        Enumeration<String> tarefa = bundle.getKeys();
        while (tarefa.hasMoreElements()) {
            Modulo md = moduloController.pegarModuloPor("SEGURANÇA");
            String nome = tarefa.nextElement();
            String descricao = bundle.getString(nome);
                if (!dao.existeTarefa(nome)) {
                    Tarefa taf = new Tarefa();
                    taf.setModulo(md);
                    taf.setNome(nome);
                    taf.setDescricao(descricao);
                    salvar(taf);
                }
            

        }
    }

    private void criarTarefaModAdministrativo() throws Exception {
        System.out.println("--------------------------------------Criando Tarefas Mod Administrativo------------------------------------------");
        ResourceBundle bundle = ResourceBundle.getBundle("br.com.sgpo.arquivos.administrativo");
        Enumeration<String> tarefa = bundle.getKeys();
        while (tarefa.hasMoreElements()) {
            Modulo md = moduloController.pegarModuloPor("ADMINISTRATIVO");
            String nome = tarefa.nextElement();
            String descricao = bundle.getString(nome);
                if (!dao.existeTarefa(nome)) {
                    Tarefa taf = new Tarefa();
                    taf.setModulo(md);
                    taf.setNome(nome);
                    taf.setDescricao(descricao);
                    salvar(taf);
                }

        }
    }

}
