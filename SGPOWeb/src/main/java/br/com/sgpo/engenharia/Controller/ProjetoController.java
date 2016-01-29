/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.Controller;

import br.com.sgpo.engenharia.DAO.ProjetoDAO;
import br.com.sgpo.engenharia.enumeration.FaseProjeto;
import br.com.sgpo.engenharia.modelo.Projeto;
import br.com.sgpo.seguranca.modelo.Usuario;
import br.com.sgpo.utilitario.ControllerGenerico;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ari
 */
@Stateless
public class ProjetoController extends ControllerGenerico<Projeto, Long> implements Serializable {

    @Inject
    private ProjetoDAO dao;

    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    public Projeto salvarGerenciar(Projeto projeto) {
        return dao.atualizarGerenciar(projeto);
    }

    public Projeto alterarStatusProjeto(Projeto projeto, Usuario usuarioLogado) {
//        projeto.setFase(FaseProjeto.CONCLUIDO);
//        projeto.setDataFim(new Date());
//        projeto = projetoController.salvarGerenciar(projeto);
        return null;
    }

}
