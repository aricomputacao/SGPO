/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.administrativo.controller;

import br.com.sgpo.administrativo.DAO.ContaFornecedorDAO;
import br.com.sgpo.administrativo.DAO.FornecedorDAO;
import br.com.sgpo.administrativo.modelo.ContaFornecedor;
import br.com.sgpo.administrativo.modelo.Fornecedor;
import br.com.sgpo.utilitario.ControllerGenerico;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Giancarlo
 */
@Stateless
public class FornecedorController extends ControllerGenerico<Fornecedor, Long> implements Serializable {

    @Inject
    private FornecedorDAO dao;
    @Inject
    private ContaFornecedorDAO contaDAO;

    @Inject
    private EnderecoController enderecoController;

    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    public Fornecedor salvarGenreciar(Fornecedor fr) throws Exception {
        fr.setEndereco(enderecoController.buscarOuCriarLogradouroPor(fr.getEndereco().getAbreviacaoUnidadeFederativa(), fr.getEndereco().getNomeDaCidade(),
                fr.getEndereco().getCep(), fr.getEndereco().getNome(), fr.getEndereco().getBairro(), fr.getEndereco().getNumero(),
                fr.getEndereco().getComplemento()));
        return dao.atualizarGerenciar(fr);
    }

    public void addConta(ContaFornecedor cf) throws Exception {
        contaDAO.atualizar(cf);
    }

    public List<ContaFornecedor> consultarContasPor(Fornecedor f) throws Exception {
        return contaDAO.consultarIgualTodos("id", "fornecedor.nome", f.getNome());
    }

    public void remverContaFornecedor(ContaFornecedor cf) throws Exception{
        cf = contaDAO.gerenciar(cf.getId());
        contaDAO.excluir(cf);
    }
    
}
