/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.utilitarios;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author gilmario
 */
public class ParcelamentoUtil {

    public static List<Date> geraVencimentos(Date dataInicial, Integer quantidadeParcelas) throws Exception {
        List<Date> listaVencimento = new ArrayList<>();
        listaVencimento.add(dataInicial);
        Calendar c = Calendar.getInstance();
        c.setTime(dataInicial);
        for (int i = 1; i < quantidadeParcelas; i++) {
            c.add(Calendar.MONTH, 1);
            listaVencimento.add(c.getTime());
        }
        return listaVencimento;

    }

    public static List<BigDecimal> totalParcelas(BigDecimal total, Integer quantidadeParcelas) throws Exception {
        List<BigDecimal> parcelas = new ArrayList<>();
        for (int i = 1; i <= quantidadeParcelas; i++) {
            BigDecimal div = new BigDecimal(quantidadeParcelas);
            BigDecimal valorParcela = total.divide(div, 2, RoundingMode.CEILING);
            BigDecimal valorParcial = valorParcela.multiply(div.subtract(new BigDecimal(1)));
            BigDecimal ultimaParcela = total.subtract(valorParcial);
            if (i == quantidadeParcelas) {
                parcelas.add(ultimaParcela);
            } else {
                parcelas.add(valorParcela);
            }
        }
        return parcelas;
    }

  
}
