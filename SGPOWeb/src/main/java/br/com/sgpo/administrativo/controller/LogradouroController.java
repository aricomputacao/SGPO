/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.administrativo.controller;

import br.com.sgpo.administrativo.DAO.LogradouroDAO;
import br.com.sgpo.administrativo.DAO.MunicipioDAO;
import br.com.sgpo.administrativo.modelo.Endereco;
import br.com.sgpo.administrativo.modelo.Municipio;
import br.com.sgpo.utilitario.ControllerGenerico;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ari
 */
@Stateless
public class LogradouroController extends ControllerGenerico<Endereco, Long> implements Serializable {

    @Inject
    private LogradouroDAO dao;
    @Inject
    private MunicipioDAO municipioDAO;

    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    
      public Endereco buscarOuCriarLogradouroPor(String unidadeFederativa, String nomeCidade, String cep, String nomeLogradouro, String tipoLogradouro, String bairro) throws Exception {
//        Logradouro logradouro = daoConsulta.buscarPor(unidadeFederativa, nomeCidade, cep, nomeLogradouro, tipoLogradouro, bairro);
        Endereco logradouro = dao.buscarPor(nomeCidade, cep, nomeLogradouro, bairro);

        if (logradouro.getId() != null) {
            return logradouro;
        } else {
            Municipio cidade = municipioDAO.buscarPor(nomeCidade, unidadeFederativa);

            if (cidade.getId() == null) {
                throw new Exception("Não existe cidade cadastrada!");
            }
            logradouro.setMunicipio(cidade);
            logradouro.setCep(cep);
            logradouro.setBairro(bairro);
            logradouro.setNome(nomeLogradouro);

            return salvarComRetorno(logradouro);
        }
    }

    private Endereco salvarComRetorno(Endereco logradouro) {
       return dao.atualizarGerenciar(logradouro);
    }
}
