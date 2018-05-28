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
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ari
 */
public class DemonstrativoFinanceiroDTO implements Serializable {

    public DemonstrativoFinanceiroDTO(List<FaturaOperacao> listaDeFaturas, Date dataReferencia) {
        this.listaDeFaturas = listaDeFaturas;
        this.dataReferencia = dataReferencia;
        listaDeDespesas = new ArrayList<>();
        listaDeReceitas = new ArrayList<>();
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
    private final List<FaturaOperacao> listaDeReceitas;
    private final List<FaturaOperacao> listaDeDespesas;

    private BigDecimal receitaDia;
    private BigDecimal despesaDia;
    private BigDecimal receitaMes;
    private BigDecimal despesaMes;
    private final Date dataReferencia;

    public final void processarFaturas() {
        listaDeDespesas.clear();
        listaDeReceitas.clear();
        for (FaturaOperacao fat : listaDeFaturas) {
            calcularValoresDia(fat);

        }
    }

    private void calcularValoresDia(FaturaOperacao fa) {
        switch (fa.getOperacao().getCategoriaOperacao().getTipoDeOperacao()) {
            case DESPESA: {
                if (fa.getDataVencimento().compareTo(dataReferencia) == 0) {
                    despesaDia = despesaDia.add(fa.getValor());
                    listaDeDespesas.add(fa);
                } else if (fa.getDataVencimento().compareTo(dataReferencia) > 0) {
                    listaDeDespesas.add(fa);
                }
                despesaMes = despesaMes.add(fa.getValor());

                break;
            }

            case RECEITA: {

                if (fa.getDataVencimento().compareTo(dataReferencia) == 0) {
                    receitaDia = receitaDia.add(fa.getValor());
                    listaDeReceitas.add(fa);
                } else if (fa.getDataVencimento().compareTo(dataReferencia) > 0) {
                    listaDeReceitas.add(fa);

                }
                receitaMes = receitaMes.add(fa.getValor());

                break;
            }
        }
    }

    public void removerFatura(FaturaOperacao fo) {
        if (listaDeDespesas.contains(fo)) {
            listaDeDespesas.remove(fo);
        }
        if (listaDeReceitas.contains(fo)) {
            listaDeReceitas.remove(fo);
        }
    }

    public Date getDataReferencia() {
        return dataReferencia;
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
        return Collections.unmodifiableList(listaDeReceitas);
    }

    public List<FaturaOperacao> getListaDeDespesas() {
        return Collections.unmodifiableList(listaDeDespesas);
    }

}
