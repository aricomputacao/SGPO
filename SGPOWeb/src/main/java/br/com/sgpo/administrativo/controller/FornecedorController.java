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
        fr.setEndereco(enderecoController.buscarOuCriarLogradouroPor(fr.getEndereco().getAbreviacaoUnidadeFederativa(), fr.getEndereco().getNomeDaCidade(),
               fr.getEndereco().getCep(), fr.getEndereco().getNome(), fr.getEndereco().getBairro(),fr.getEndereco().getNumero(),
               fr.getEndereco().getComplemento()));
        dao.atualizar(fr); 
    }
    
    
}
