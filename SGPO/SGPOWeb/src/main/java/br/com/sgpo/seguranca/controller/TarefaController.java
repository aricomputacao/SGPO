/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.seguranca.controller;

import br.com.sgpo.seguranca.DAO.TarefaDAO;
import br.com.sgpo.seguranca.enumaration.TarefaPermissaoDTO;
import br.com.sgpo.seguranca.modelo.Modulo;
import br.com.sgpo.seguranca.modelo.Permissao;
import br.com.sgpo.seguranca.modelo.Tarefa;
import br.com.sgpo.utilitario.ControllerGenerico;
import br.com.sgpo.utilitarios.ResourceUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
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
        if ("".equals(nomeModulo) || nomeModulo == null) {
            return dao.consultarTodosOrdenadosPor("modulo.nome");
        } else {
            return dao.consultarLike("modulo.nome", nomeModulo);
        }
    }

    public void criarTarefas() throws Exception {
        criarTarefaModSeguranca();
        criarTarefaModAdministrativo();
        criarTarefaModEngenharia();
        criarTarefaModFinanceiro();
    }

    public List<TarefaPermissaoDTO> retornarTarefasPermissao(List<Tarefa> tarefas, List<Permissao> permissoes) {
        boolean contem = false;
        List<TarefaPermissaoDTO> listDTO = new ArrayList<>();
        //roda todas as taredas 
        for (Tarefa taf : tarefas) {
            contem = false;
            TarefaPermissaoDTO dto = new TarefaPermissaoDTO();
            //roda para saber se tem as taredas nas permissões
            for (Permissao per : permissoes) {
                if (per.getTarefa().equals(taf)) {
                    dto.setTarefa(taf);
                    dto.setConsultar(per.isConsultar());
                    dto.setEditar(per.isEditar());
                    dto.setExcluir(per.isExcluir());
                    dto.setIncluir(per.isIncluir());

                    listDTO.add(dto);
                    contem = true;
                    break;
                }
            }

            if (!contem) {
                dto.setTarefa(taf);
                listDTO.add(dto);
            }

        }
        return listDTO;
    }

    private void criarTarefaModSeguranca() throws Exception {
        System.out.println("--------------------------------------Criando Tarefas Mod Segurança------------------------------------------");
        ResourceBundle bundle = ResourceBundle.getBundle("br.com.sgpo.arquivos.seguranca");
        Enumeration<String> tarefa = bundle.getKeys();
        while (tarefa.hasMoreElements()) {
            Modulo md = moduloController.pegarModuloPor(ResourceUtil.lerBundle("seguranca", ResourceUtil.MODULO));
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
    private void criarTarefaModEngenharia() throws Exception {
        System.out.println("--------------------------------------Criando Tarefas Mod Engenharia------------------------------------------");
        ResourceBundle bundle = ResourceBundle.getBundle("br.com.sgpo.arquivos.engenharia");
        Enumeration<String> tarefa = bundle.getKeys();
        while (tarefa.hasMoreElements()) {
            Modulo md = moduloController.pegarModuloPor(ResourceUtil.lerBundle("engenharia", ResourceUtil.MODULO));
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
    private void criarTarefaModFinanceiro() throws Exception {
        System.out.println("--------------------------------------Criando Tarefas Mod Financeiro------------------------------------------");
        ResourceBundle bundle = ResourceBundle.getBundle("br.com.sgpo.arquivos.financeiro");
        Enumeration<String> tarefa = bundle.getKeys();
        while (tarefa.hasMoreElements()) {
            Modulo md = moduloController.pegarModuloPor(ResourceUtil.lerBundle("financeiro", ResourceUtil.MODULO));
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
            Modulo md = moduloController.pegarModuloPor(ResourceUtil.lerBundle("administrativo", ResourceUtil.MODULO));

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
