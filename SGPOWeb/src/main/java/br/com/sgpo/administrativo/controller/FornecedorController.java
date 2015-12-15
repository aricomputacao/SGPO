/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.administrativo.controller;

import br.com.sgpo.administrativo.DAO.FornecedorDAO;
import br.com.sgpo.administrativo.modelo.Fornecedor;
import br.com.sgpo.utilitario.ControllerGenerico;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Giancarlo
 */
@Stateless
public class FornecedorController extends ControllerGenerico<Fornecedor, Long> implements Serializable{

    @Inject
    private FornecedorDAO dao;
    
    @Inject
    private EnderecoController enderecoController;
    
    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }
    
    public void salvar(Fornecedor fr) throws Exception{
        fr.setEndereço(enderecoController.buscarOuCriarLogradouroPor(fr.getEndereço().getAbreviacaoUnidadeFederativa(), fr.getEndereço().getNomeDaCidade(),
               fr.getEndereço().getCep(), fr.getEndereço().getNome(), fr.getEndereço().getBairro(),fr.getEndereço().getNumero(),
               fr.getEndereço().getComplemento()));
        dao.atualizar(fr); 
    }
    
    
}
