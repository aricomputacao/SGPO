/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.financeiro.controller;

import br.com.sgpo.financeiro.DAO.FaturaOperacaoDAO;
import br.com.sgpo.financeiro.dto.DemonatrativoFinanceiroAnualDTO;
import br.com.sgpo.financeiro.dto.DemonstrativoFinanceiroMensalDTO;
import br.com.sgpo.financeiro.dto.ResultadoFinanceiroMensalDTO;
import br.com.sgpo.financeiro.enumeration.TipoDeOperacao;
import br.com.sgpo.financeiro.modelo.FaturaOperacao;
import br.com.sgpo.financeiro.modelo.Operacao;
import br.com.sgpo.utilitario.ControllerGenerico;
import br.com.sgpo.utilitarios.enumeration.Mes;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author ari
 */
@Stateless
public class FaturaController extends ControllerGenerico<FaturaOperacao, Long> implements Serializable {

    @Inject
    private FaturaOperacaoDAO dao;

    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    public void addFaturaReceita(Operacao operacao, BigDecimal valor) throws Exception {
        Date ultimaData = operacao.getDateOperacao();
        if (operacao.isParcelamento()) {
            for (int i = 0; i < operacao.getNumeroParcelas(); i++) {
                FaturaOperacao fatura = new FaturaOperacao();
                fatura.setDataVencimento(ultimaData);
                fatura.setOperacao(operacao);
                fatura.setValor(valor);
                dao.salvar(fatura);
                ultimaData = operacao.getPeriodoRecorrencia().processaData(ultimaData);
            }
        } else {
            FaturaOperacao fatura = new FaturaOperacao();
            fatura.setDataVencimento(ultimaData);
            fatura.setOperacao(operacao);
            fatura.setValor(valor);
            dao.salvar(fatura);
        }
    }

    public List<FaturaOperacao> consultarFaturaDa(Operacao o) {
        return dao.consultarFaturaDa(o);

    }

    public List<Integer> consultarAnosRegistrados() {
        return dao.consultarAnosRegistrados();

    }

    @Override
    public void excluir(FaturaOperacao fo) throws Exception {
        fo = dao.gerenciar(fo.getId());
        dao.excluir(fo);
    }

    public List<FaturaOperacao> consultarPor(Date dataReferencia) {
        return dao.consultarFaturaPor(dataReferencia);
    }

    public List<DemonatrativoFinanceiroAnualDTO> consultarDemonstrativoAnual(int ano) {
        return dao.consultarDemonstrativoAnual(ano);
    }

    public List<DemonatrativoFinanceiroAnualDTO> consultarDemonstrativoTipo(TipoDeOperacao operacao) {
        return dao.consultarDemonstrativoAnualTipo(operacao);
    }

    public BarChartModel graficoReceitaDespesaAnual() {
        BigDecimal maior = BigDecimal.ZERO;
        BigDecimal menor = BigDecimal.valueOf(500);
        BarChartModel barModel = new BarChartModel();

        List<DemonatrativoFinanceiroAnualDTO> listaDemonatrativoFinanceiroAnual = consultarDemonstrativoTipo(TipoDeOperacao.RECEITA);

        ChartSeries receita = new ChartSeries();
        receita.setLabel("Receita");
        for (DemonatrativoFinanceiroAnualDTO dem : listaDemonatrativoFinanceiroAnual) {
            receita.set(Integer.toString(dem.getAno()), dem.getValor());
            if (dem.getValor().compareTo(menor) < 0) {
                menor = dem.getValor();
            }
            if (dem.getValor().compareTo(maior) > 0) {
                maior = dem.getValor();
            }

        }

        listaDemonatrativoFinanceiroAnual = consultarDemonstrativoTipo(TipoDeOperacao.DESPESA);
        ChartSeries despesa = new ChartSeries();
        despesa.setLabel("Despesa");
        for (DemonatrativoFinanceiroAnualDTO dem : listaDemonatrativoFinanceiroAnual) {
            despesa.set(Integer.toString(dem.getAno()), dem.getValor());
            if (dem.getValor().compareTo(menor) < 0) {
                menor = dem.getValor();
            }
            if (dem.getValor().compareTo(maior) > 0) {
                maior = dem.getValor();
            }
        }

        barModel.addSeries(receita);
        barModel.addSeries(despesa);

        barModel.setTitle("Operações Anuais");
        barModel.setLegendPosition("ne");
        barModel.setAnimate(true);

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Ano");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Tipo de Operaçao");
        yAxis.setMin(menor.subtract(new BigDecimal(10)));
        yAxis.setMax(maior.add(new BigDecimal(100)));

        return barModel;

    }

    public BarChartModel graficoReceitaDespesaMensal(Integer ano) {
        BigDecimal maior = BigDecimal.ZERO;
        BigDecimal menor = BigDecimal.valueOf(500);
        BarChartModel barModel = new BarChartModel();

        List<DemonstrativoFinanceiroMensalDTO> listaDemonatrativoFinanceiroMensal = dao.consultarDemonstrativoMensaTipo(TipoDeOperacao.RECEITA, ano);

        ChartSeries receita = new ChartSeries();
        receita.setLabel("Receita");
        for (DemonstrativoFinanceiroMensalDTO dem : listaDemonatrativoFinanceiroMensal) {
            receita.set(Mes.retornarMes(dem.getMes()), dem.getValor());
            if (dem.getValor().compareTo(menor) < 0) {
                menor = dem.getValor();
            }
            if (dem.getValor().compareTo(maior) > 0) {
                maior = dem.getValor();
            }

        }

        listaDemonatrativoFinanceiroMensal = dao.consultarDemonstrativoMensaTipo(TipoDeOperacao.DESPESA, ano);
        ChartSeries despesa = new ChartSeries();
        despesa.setLabel("Despesa");
        for (DemonstrativoFinanceiroMensalDTO dem : listaDemonatrativoFinanceiroMensal) {
            despesa.set(Mes.retornarMes(dem.getMes()), dem.getValor());
            if (dem.getValor().compareTo(menor) < 0) {
                menor = dem.getValor();
            }
            if (dem.getValor().compareTo(maior) > 0) {
                maior = dem.getValor();
            }
        }

        barModel.addSeries(receita);
        barModel.addSeries(despesa);

        barModel.setTitle("Operações Mensais");
        barModel.setLegendPosition("ne");
        barModel.setAnimate(true);

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel(ano.toString());

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Tipo de Operaçao");
        yAxis.setMin(menor.subtract(new BigDecimal(10)));
        yAxis.setMax(maior.add(new BigDecimal(100)));

        return barModel;

    }

    public ScheduleModel construirCalendarioFinanceiro() throws Exception {
        List<FaturaOperacao> listaDeFaturaOperacaos = consultarTodosOrdenadorPor("id");
        ScheduleModel eventModel = new DefaultScheduleModel();

        //percorre a lista de eventos e popula o calendario
        for (FaturaOperacao fa : listaDeFaturaOperacaos) {

            DefaultScheduleEvent agendaFinanceira = new DefaultScheduleEvent();
            agendaFinanceira.setAllDay(true);
            agendaFinanceira.setEndDate(fa.getDataVencimento());
            agendaFinanceira.setStartDate(fa.getDataVencimento());
            agendaFinanceira.setTitle(fa.getOperacao().getCategoriaOperacao().getNome());
            agendaFinanceira.setDescription(fa.getOperacao().getCategoriaOperacao().getTipoDeOperacao().toString() + "( R$ "
                    + fa.getValor().toPlainString() + " ) : " + fa.getOperacao().getDescricao());
            agendaFinanceira.setData(fa.getId());
            agendaFinanceira.setEditable(false); //pertir que o usuario edite

            agendaFinanceira.setStyleClass(fa.getOperacao().getCategoriaOperacao().getCor().getDescricao());

            eventModel.addEvent(agendaFinanceira); //o evento e adicionado na lista
        }
        return eventModel;
    }

    public List<FaturaOperacao> consultarPor(Mes mesReferencia, int ano, TipoDeOperacao tipoDeOperacao) {
        return dao.consultarFaturaDa(mesReferencia, ano, tipoDeOperacao);
    }

    public List<FaturaOperacao> consultarPor(int ano, TipoDeOperacao tipoDeOperacao) {
        return dao.consultarPor(ano, tipoDeOperacao);
    }

    public List<ResultadoFinanceiroMensalDTO> consultarResultadoFinanceiro(int ano) {
        List<ResultadoFinanceiroMensalDTO> listaResultadoFinanceiro = new ArrayList<>();
        List<FaturaOperacao> lista = dao.consultarResultadoFinanceiro(ano);
        for (FaturaOperacao fa : lista) {
            if (fa.getOperacao().getCategoriaOperacao().getTipoDeOperacao().equals(TipoDeOperacao.RECEITA)) {
                listaResultadoFinanceiro.add(new ResultadoFinanceiroMensalDTO(fa.getDataVencimento(), fa.getValor(), BigDecimal.ZERO));

            } else {
                listaResultadoFinanceiro.add(new ResultadoFinanceiroMensalDTO(fa.getDataVencimento(), BigDecimal.ZERO, fa.getValor()));

            }
        }
        return listaResultadoFinanceiro;
    }

}
