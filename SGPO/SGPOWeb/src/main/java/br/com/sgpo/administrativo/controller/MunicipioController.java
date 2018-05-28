/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.administrativo.controller;


import br.com.sgpo.administrativo.DAO.MunicipioDAO;
import br.com.sgpo.administrativo.modelo.Municipio;
import br.com.sgpo.administrativo.modelo.UnidadeFederativa;
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
public class MunicipioController extends ControllerGenerico<Municipio, Integer> implements Serializable {

    @Inject
    private MunicipioDAO dao;

    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    public List<Municipio> consultarMunicipioPor(UnidadeFederativa uf) {
       return dao.consultarMunicipioPor(uf);
    }
    
     public Municipio buscarPor(String nomeCidade, String ufAbreviacao) {
         return dao.buscarPor(nomeCidade, ufAbreviacao);
     }
}
