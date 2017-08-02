/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.projeto.DAO;

import br.com.sgpo.engenharia.projeto.modelo.NotificacaoProjeto;
import br.com.sgpo.engenharia.projeto.modelo.Projeto;
import br.com.sgpo.utilitario.DAOGenerico;
import br.com.sgpo.utilitarios.MetodosUtilitariosData;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author ari
 */
@Stateless
public class NotificacaoProjetoDAO extends DAOGenerico<NotificacaoProjeto, Long> implements Serializable {

    public NotificacaoProjetoDAO() {
        super(NotificacaoProjeto.class);
    }

    public List<NotificacaoProjeto> consultarTodos(Projeto projeto) {
        TypedQuery<NotificacaoProjeto> tq;
        tq = getEm().createQuery("SELECT n FROM NotificacaoProjeto n where n.projeto = :p ORDER BY n.data DESC", NotificacaoProjeto.class)
                .setParameter("p", projeto);
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();

    }

    public List<NotificacaoProjeto> consultarTodosDoDia() {
        Calendar cal = Calendar.getInstance();
        TypedQuery<NotificacaoProjeto> tq;
        tq = getEm().createQuery("SELECT n FROM NotificacaoProjeto n where  month(n.data) = :mes and day(n.data) = :dia  and year(n.data) = :ano   ", NotificacaoProjeto.class)
                .setParameter("mes", cal.get(Calendar.MONTH)+1)
                .setParameter("dia", cal.get(Calendar.DAY_OF_MONTH))
                .setParameter("ano", cal.get(Calendar.YEAR));
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }

}
