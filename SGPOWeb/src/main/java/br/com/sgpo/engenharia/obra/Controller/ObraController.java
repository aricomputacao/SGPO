/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.obra.Controller;

import br.com.sgpo.administrativo.controller.EnderecoController;
import br.com.sgpo.engenharia.obra.DAO.ObraDAO;
import br.com.sgpo.engenharia.obra.modelo.Obra;
import br.com.sgpo.engenharia.projeto.modelo.Projeto;
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
public class ObraController extends ControllerGenerico<Obra, Long> implements Serializable {

    @Inject
    private ObraDAO dao;
    @Inject
    private EnderecoController enderecoController;

    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    public Obra salvarGerenciar(Obra p) throws Exception {
        p.setEndereco(enderecoController.buscarOuCriarLogradouroPor(p.getEndereco().getAbreviacaoUnidadeFederativa(), p.getEndereco().getNomeDaCidade(),
                p.getEndereco().getCep(), p.getEndereco().getNome(), p.getEndereco().getBairro(), p.getEndereco().getNumero(),
                p.getEndereco().getComplemento()));
        return dao.atualizarGerenciar(p);
    }

    public Obra salvarGerenciar(Obra obra, List<Projeto> target) throws Exception {
        obra.gerenciarProjetos(target);
        return salvarGerenciar(obra);

    }

}
