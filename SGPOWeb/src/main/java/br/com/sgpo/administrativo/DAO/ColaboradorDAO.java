/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.administrativo.DAO;

import br.com.sgpo.administrativo.modelo.Colaborador;
import br.com.sgpo.utilitario.DAOGenerico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Giancarlo
 */
@Stateless
public class ColaboradorDAO extends DAOGenerico<Colaborador, Long> implements Serializable {

    public ColaboradorDAO() {
        super(Colaborador.class);
    }

    public Colaborador buscarPorNome(String nome) {
        TypedQuery<Colaborador> tq;
        tq = getEm().createQuery("SELECT c FROM Colaborador c WHERE c.nome = :nome", Colaborador.class)
                .setParameter("nome", nome);

        return tq.getResultList().isEmpty() ? new Colaborador() : tq.getSingleResult();
    }

    public List<Colaborador> consultarColaboradorDisponivelParaObra(String nome) {
        TypedQuery<Colaborador> tq;
        tq = getEm().createQuery("SELECT c FROM Colaborador c WHERE c.ativo = true and UPPER(c.nome) LIKE  :nome and c.id  NOT IN(SELECT f.colaborador.id from FuncionarioObra f WHERE  f.dataSaida  IS NULL ) ", Colaborador.class)
                .setParameter("nome", "%"+nome.toUpperCase()+"%");

        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }

}
