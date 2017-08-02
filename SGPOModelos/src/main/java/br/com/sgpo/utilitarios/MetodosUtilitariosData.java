/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.utilitarios;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ari
 */
public class MetodosUtilitariosData {

    public static Date formataData(String data) throws Exception {
        if (data == null || data.equals("")) {
            return null;
        }

        Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = (java.util.Date) formatter.parse(data);
        } catch (ParseException e) {
            throw e;
        }
        return date;
    }

    /**
     * Metodo utilizado para setar o timestamp da hora final do dia
     *
     * @param dtFim
     * @return
     */
    public static Date processarDataFinal(Date dtFim) {
        Calendar c = Calendar.getInstance();
        c.setTime(dtFim);

        c.set(Calendar.HOUR, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);

        dtFim.setTime(c.getTimeInMillis());
        return dtFim;
    }

    /**
     * Metodo utilizado para setar o timestamp da hora inicial do dia
     *
     * @param dtIni
     * @return
     */
    public static Date processarDataInicial(Date dtIni) {
        Calendar c = Calendar.getInstance();
        c.setTime(dtIni);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        dtIni.setTime(c.getTimeInMillis());
        return dtIni;
    }

    public static Date pegarDataFormatoTimeStamp(Date date) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        //Data do parametro (data do mapa)
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        //Data do processada (data do mapa)
        Date dt2 = new Date();
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(dt2);

        if (cal.get(Calendar.DAY_OF_YEAR) == cal.get(Calendar.DAY_OF_YEAR)) {
            return dateFormat.parse(dateFormat.format(cal2.getTime()));
        } else {
            cal2.set(Calendar.MONTH, cal.MONTH);
            cal2.set(Calendar.DAY_OF_MONTH, cal.DAY_OF_MONTH);
            return dateFormat.parse(dateFormat.format(cal2.getTime()));

        }

    }

    public static String formatarDataString(Date date) {
        SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return spf.format(cal.getTime());

    }

    public static String formatarDataTimeString(Date date) {
        SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return spf.format(cal.getTime());

    }

    public static Date formatarData(Date date) {
        try {
            SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            return spf.parse(spf.format(cal.getTime()));
        } catch (ParseException ex) {

            Logger.getLogger(MetodosUtilitariosData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public static Date adicionarMes(Date date, int qtd) {
        try {
            SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MONTH, qtd);
            return spf.parse(spf.format(cal.getTime()));
        } catch (ParseException ex) {

            Logger.getLogger(MetodosUtilitariosData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    /* Calcula a diferença de duas datas em dias
 * <br>
 * <b>Importante:</b> Quando realiza a diferença em dias entre duas datas, este método considera as horas restantes e as converte em fração de dias.
 * @param dataInicial
 * @param dataFinal
 * @return quantidade de dias existentes entre a dataInicial e dataFinal.
     */
    public static double diferencaEmDias(Date dataInicial, Date dataFinal) {
        double result = 0;
        long diferenca = dataFinal.getTime() - dataInicial.getTime();
        double diferencaEmDias = (diferenca / 1000) / 60 / 60 / 24; //resultado é diferença entre as datas em dias
        long horasRestantes = (diferenca / 1000) / 60 / 60 % 24; //calcula as horas restantes
        result = diferencaEmDias + (horasRestantes / 24d); //transforma as horas restantes em fração de dias

        return result;
    }

    /**
     * Calcula a diferença de duas datas em horas
     * <br>
     * <b>Importante:</b> Quando realiza a diferença em horas entre duas datas,
     * este método considera os minutos restantes e os converte em fração de
     * horas.
     *
     * @param dataInicial
     * @param dataFinal
     * @return quantidade de horas existentes entre a dataInicial e dataFinal.
     */
    public static double diferencaEmHoras(Date dataInicial, Date dataFinal) {
        double result = 0;
        long diferenca = dataFinal.getTime() - dataInicial.getTime();
        long diferencaEmHoras = (diferenca / 1000) / 60 / 60;
        long minutosRestantes = (diferenca / 1000) / 60 % 60;
        double horasRestantes = minutosRestantes / 60d;
        result = diferencaEmHoras + (horasRestantes);

        return result;
    }

    /**
     * Calcula a diferença de duas datas em minutos
     * <br>
     * <b>Importante:</b> Quando realiza a diferença em minutos entre duas
     * datas, este método considera os segundos restantes e os converte em
     * fração de minutos.
     *
     * @param dataInicial
     * @param dataFinal
     * @return quantidade de minutos existentes entre a dataInicial e dataFinal.
     */
    public static double diferencaEmMinutos(Date dataInicial, Date dataFinal) {
        double result = 0;
        long diferenca = dataFinal.getTime() - dataInicial.getTime();
        double diferencaEmMinutos = (diferenca / 1000) / 60; //resultado é diferença entre as datas em minutos
        long segundosRestantes = (diferenca / 1000) % 60; //calcula os segundos restantes
        result = diferencaEmMinutos + (segundosRestantes / 60d); //transforma os segundos restantes em minutos

        return result;
    }

    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        
         c.add(Calendar.DAY_OF_MONTH, 2);
        
        System.out.println(MetodosUtilitariosData.diferencaEmDias(Calendar.getInstance().getTime(), c.getTime()));
    }
}
