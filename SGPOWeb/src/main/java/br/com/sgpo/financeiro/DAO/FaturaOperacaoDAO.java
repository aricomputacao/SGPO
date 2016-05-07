/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.financeiro.DAO;

import br.com.sgpo.financeiro.dto.AnosRegistradosDTO;
import br.com.sgpo.financeiro.dto.DemonatrativoFinanceiroAnualDTO;
import br.com.sgpo.financeiro.dto.DemonstrativoFinanceiroMensalDTO;
import br.com.sgpo.financeiro.dto.ResultadoFinanceiroMensalDTO;
import br.com.sgpo.financeiro.enumeration.TipoDeOperacao;
import br.com.sgpo.financeiro.modelo.FaturaOperacao;
import br.com.sgpo.financeiro.modelo.Operacao;
import br.com.sgpo.utilitario.DAOGenerico;
import br.com.sgpo.utilitarios.enumeration.Mes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author ari
 */
@Stateless
public class FaturaOperacaoDAO extends DAOGenerico<FaturaOperacao, Long> implements Serializable {

    public FaturaOperacaoDAO() {
        super(FaturaOperacao.class);
    }

    public List<FaturaOperacao> consultarFaturaDa(Operacao o) {
        TypedQuery tq;

        tq = getEm().createQuery("SELECT f FROM FaturaOperacao f WHERE f.operacao = :o ORDER BY f.dataVencimento", FaturaOperacao.class)
                .setParameter("o", o);
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }

    public List<FaturaOperacao> consultarFaturaPor(Date dataReferencia) {
        Calendar c = Calendar.getInstance();
        c.setTime(dataReferencia);
        TypedQuery tq;

        tq = getEm().createQuery("SELECT f FROM FaturaOperacao f WHERE (month(f.dataVencimento) = :mes and year(f.dataVencimento) = :ano) ORDER BY f.dataVencimento", FaturaOperacao.class)
                .setParameter("mes", c.get(Calendar.MONTH) + 1)
                .setParameter("ano", c.get(Calendar.YEAR));
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }

    public List<DemonatrativoFinanceiroAnualDTO> consultarDemonstrativoAnual(int ano) {
        TypedQuery tq;

        tq = getEm().createQuery("SELECT new br.com.sgpo.financeiro.dto.DemonatrativoFinanceiroAnualDTO(f.operacao.categoriaOperacao.tipoDeOperacao,year(f.dataVencimento),SUM(f.valor))  "
                + "FROM FaturaOperacao f where year(f.dataVencimento) = :ano  GROUP BY f.operacao.categoriaOperacao.tipoDeOperacao,year(f.dataVencimento) order by year(f.dataVencimento)", DemonatrativoFinanceiroAnualDTO.class)
                .setParameter("ano", ano);
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }

    public List<DemonatrativoFinanceiroAnualDTO> consultarDemonstrativoAnualTipo(TipoDeOperacao operacao) {
        TypedQuery tq;

        tq = getEm().createQuery("SELECT new br.com.sgpo.financeiro.dto.DemonatrativoFinanceiroAnualDTO(f.operacao.categoriaOperacao.tipoDeOperacao,year(f.dataVencimento),SUM(f.valor))  "
                + "FROM FaturaOperacao f where f.operacao.categoriaOperacao.tipoDeOperacao = :operacao  GROUP BY f.operacao.categoriaOperacao.tipoDeOperacao,year(f.dataVencimento) order by year(f.dataVencimento)", DemonatrativoFinanceiroAnualDTO.class)
                .setParameter("operacao", operacao);
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }

    public List<DemonstrativoFinanceiroMensalDTO> consultarDemonstrativoMensaTipo(TipoDeOperacao operacao, int ano) {
        TypedQuery tq;

        tq = getEm().createQuery("SELECT new br.com.sgpo.financeiro.dto.DemonstrativoFinanceiroMensalDTO(f.operacao.categoriaOperacao.tipoDeOperacao,month(f.dataVencimento),SUM(f.valor))  "
                + "FROM FaturaOperacao f where f.operacao.categoriaOperacao.tipoDeOperacao = :operacao and  year(f.dataVencimento) = :ano GROUP BY f.operacao.categoriaOperacao.tipoDeOperacao,month(f.dataVencimento) order by month(f.dataVencimento)", DemonstrativoFinanceiroMensalDTO.class)
                .setParameter("operacao", operacao)
                .setParameter("ano", ano);
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }

    public List<Integer> consultarAnosRegistrados() {
        TypedQuery tq;

        tq = getEm().createQuery("select distinct(year(f.dataVencimento)) from FaturaOperacao f", Integer.class);
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }

    public List<FaturaOperacao> consultarFaturaDa(Mes mesReferencia, int ano, TipoDeOperacao tp) {
        TypedQuery tq;

        tq = getEm().createQuery("SELECT f FROM FaturaOperacao f where month(f.dataVencimento) = :mes and  year(f.dataVencimento) = :ano and f.operacao.categoriaOperacao.tipoDeOperacao = :tp"
                + "                  order by f.operacao.categoriaOperacao.tipoDeOperacao,f.operacao.categoriaOperacao,month(f.dataVencimento)", FaturaOperacao.class)
                .setParameter("mes", mesReferencia.getReferencia())
                .setParameter("tp", tp)
                .setParameter("ano", ano);
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();

    }

    public List<FaturaOperacao> consultarPor(int ano,TipoDeOperacao tp) {
        TypedQuery tq;

        tq = getEm().createQuery("SELECT f FROM FaturaOperacao f where  year(f.dataVencimento) = :ano and f.operacao.categoriaOperacao.tipoDeOperacao = :tp"
                + "                  order by month(f.dataVencimento),f.operacao.categoriaOperacao", FaturaOperacao.class)
                .setParameter("tp", tp)
                .setParameter("ano", ano);
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }
    
    public List<FaturaOperacao> consultarResultadoFinanceiro(int ano) {
        TypedQuery tq;


        tq = getEm().createQuery("SELECT f FROM FaturaOperacao f where  year(f.dataVencimento) = :ano "
                + "                  order by month(f.dataVencimento)", FaturaOperacao.class)
                .setParameter("ano", ano);
        return tq.getResultList().isEmpty() ? new ArrayList<>() : tq.getResultList();
    }

}
