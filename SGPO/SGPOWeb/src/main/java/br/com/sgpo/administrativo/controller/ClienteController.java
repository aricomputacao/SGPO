/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.administrativo.controller;

import br.com.sgpo.administrativo.DAO.ClienteDAO;
import br.com.sgpo.administrativo.modelo.Cliente;
import br.com.sgpo.utilitario.ControllerGenerico;
import br.com.sgpo.utilitarios.StringUtil;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ari
 */
@Stateless
public class ClienteController extends ControllerGenerico<Cliente, Long> implements Serializable{

    @Inject
    private ClienteDAO dao;
    @Inject
    private EnderecoController enderecoController;
    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }
    
    
    @Override
    public void salvar(Cliente cl) throws Exception{
       cl.setEndereco(enderecoController.buscarOuCriarLogradouroPor(cl.getEndereco().getAbreviacaoUnidadeFederativa(), cl.getEndereco().getNomeDaCidade(),
               cl.getEndereco().getCep(), cl.getEndereco().getNome(), cl.getEndereco().getBairro(),cl.getEndereco().getNumero(),
               cl.getEndereco().getComplemento()));
       cl.setDocumento(StringUtil.removerCaracteresEspeciais(cl.getDocumento()));
       dao.atualizar(cl);    
    }
}
