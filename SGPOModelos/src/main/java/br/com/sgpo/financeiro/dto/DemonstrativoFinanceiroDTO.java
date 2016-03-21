/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.financeiro.dto;

import br.com.sgpo.financeiro.modelo.FaturaOperacao;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ari
 */
public class DemonstrativoFinanceiroDTO implements Serializable {

    public DemonstrativoFinanceiroDTO(List<FaturaOperacao> listaDeFaturas) {
        this.listaDeFaturas = listaDeFaturas;
        listaDeDespesas = new ArrayList<>();
        listaDeReceitas = new ArrayList<>();
        dataReferencia = new Date();
        receitaDia = BigDecimal.ZERO;
        despesaDia = BigDecimal.ZERO;
        receitaMes = BigDecimal.ZERO;
        despesaMes = BigDecimal.ZERO;
        processarFaturas();
    }

    public DemonstrativoFinanceiroDTO() {
        receitaDia = BigDecimal.ZERO;
        despesaDia = BigDecimal.ZERO;
        receitaMes = BigDecimal.ZERO;
        despesaMes = BigDecimal.ZERO;
         listaDeDespesas = new ArrayList<>();
        listaDeReceitas = new ArrayList<>();
        dataReferencia = new Date();
    }

    private List<FaturaOperacao> listaDeFaturas;
    private List<FaturaOperacao> listaDeReceitas;
    private List<FaturaOperacao> listaDeDespesas;

    private BigDecimal receitaDia;
    private BigDecimal despesaDia;
    private BigDecimal receitaMes;
    private BigDecimal despesaMes;
    private Date dataReferencia;

    public final void processarFaturas() {
        Calendar referencia = Calendar.getInstance();
        referencia.setTime(dataReferencia);

        for (FaturaOperacao fat : listaDeFaturas) {
            calcularValoresDia(fat, referencia);

        }
    }

    private void calcularValoresDia(FaturaOperacao fa, Calendar dataReferencia) {
        switch (fa.getOperacao().getCategoriaOperacao().getTipoDeOperacao()) {
            case DESPESA: {
                if (fa.getDataVencimento().compareTo(dataReferencia.getTime()) == 0) {
                    despesaDia = despesaDia.add(fa.getValor());
                    listaDeDespesas.add(fa);
                } else if (fa.getDataVencimento().compareTo(dataReferencia.getTime()) > 0) {
                    listaDeDespesas.add(fa);
                }
                despesaMes = despesaMes.add(fa.getValor());

                break;
            }

            case RECEITA: {

                if (fa.getDataVencimento().compareTo(dataReferencia.getTime()) == 0) {
                    receitaDia = receitaDia.add(fa.getValor());
                    listaDeReceitas.add(fa);
                } else if (fa.getDataVencimento().compareTo(dataReferencia.getTime()) > 0) {
                    listaDeReceitas.add(fa);

                }
                receitaMes = receitaMes.add(fa.getValor());

                break;
            }
        }
    }

    public Date getDataReferencia() {
        return dataReferencia;
    }

    public void setDataReferencia(Date dataReferencia) {
        this.dataReferencia = dataReferencia;
    }

    public BigDecimal getReceitaDia() {
        return receitaDia;
    }

    public void setReceitaDia(BigDecimal receitaDia) {
        this.receitaDia = receitaDia;
    }

    public BigDecimal getDespesaDia() {
        return despesaDia;
    }

    public void setDespesaDia(BigDecimal despesaDia) {
        this.despesaDia = despesaDia;
    }

    public BigDecimal getReceitaMes() {
        return receitaMes;
    }

    public void setReceitaMes(BigDecimal receitaMes) {
        this.receitaMes = receitaMes;
    }

    public BigDecimal getDespesaMes() {
        return despesaMes;
    }

    public void setDespesaMes(BigDecimal despesaMes) {
        this.despesaMes = despesaMes;
    }

    public List<FaturaOperacao> getListaDeReceitas() {
        return listaDeReceitas;
    }

    public List<FaturaOperacao> getListaDeDespesas() {
        return listaDeDespesas;
    }

}
