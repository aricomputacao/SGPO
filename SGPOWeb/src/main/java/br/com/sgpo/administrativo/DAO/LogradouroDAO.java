/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.administrativo.DAO;


import br.com.sgpo.administrativo.modelo.Endereco;
import br.com.sgpo.utilitario.DAOGenerico;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author ari
 */
@Stateless 
public class LogradouroDAO extends DAOGenerico<Endereco, Long> implements Serializable{

    public LogradouroDAO() {
        super(Endereco.class);
    }
     public Endereco buscarPor(String cep) {
        TypedQuery<Endereco> tq;
        tq = getEm().createQuery("SELECT l from Logradouro l WHERE l.cep = :cep ", Endereco.class)
                .setParameter("cep", cep);
        return tq.getResultList().isEmpty() ? new Endereco() : tq.getSingleResult();
    }

    public Endereco buscarPor(String uf, String municipio, String cep, String logradouro, String tipoLogradouro, String bairro) {
        TypedQuery<Endereco> tq;
        tq = getEm().createQuery("SELECT l FROM Logradouro l WHERE l.cep = :cep "
                + "AND l.nome = :nome AND l.tipoLogradouro.nome = :tpLog AND l.municipio.nome = :mun "
                + "AND l.municipio.unidadeFederativa.sigla = :uf AND l.bairro = :bai", Endereco.class)
                .setParameter("cep", cep.trim())
                .setParameter("nome", logradouro.toUpperCase())
                .setParameter("tpLog", tipoLogradouro.toUpperCase())
                .setParameter("mun", municipio)
                .setParameter("uf", uf)
                .setParameter("bai", bairro.toUpperCase());
        return tq.getResultList().isEmpty() ? new Endereco() : tq.getSingleResult();
    }
    
    public Endereco buscarPor(String municipio, String cep, String logradouro, String bairro) {
        TypedQuery<Endereco> tq;
        tq = getEm().createQuery("SELECT l FROM Logradouro l WHERE l.cep = :cep "
                + "AND l.nome = :nome AND l.municipio.nome = :mun "
                + "AND l.bairro = :bai", Endereco.class)
                .setParameter("cep", cep.trim())
                .setParameter("nome", logradouro.toUpperCase())
                .setParameter("mun", municipio)
                .setParameter("bai", bairro.toUpperCase());
        return tq.getResultList().isEmpty() ? new Endereco() : tq.getSingleResult();
    }
}
