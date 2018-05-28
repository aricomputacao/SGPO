/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.administrativo.managedbean;

import br.com.sgpo.administrativo.controller.CargoController;
import br.com.sgpo.administrativo.controller.ColaboradorController;
import br.com.sgpo.administrativo.controller.FaltaColaboradorController;
import br.com.sgpo.administrativo.modelo.Cargo;
import br.com.sgpo.administrativo.modelo.Colaborador;
import br.com.sgpo.administrativo.modelo.FaltaColaborador;
import br.com.sgpo.utilitario.BeanGenerico;
import br.com.sgpo.utilitario.mensagens.MensagensUtil;
import br.com.sgpo.utilitario.relatorio.RelatorioSession;
import br.com.sgpo.utilitarios.relatorios.AssistentedeRelatorio;
import br.com.sgpo.utilitarios.relatorios.PastasRelatorio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Giancarlo
 */
@Named
@ViewScoped
public class ColaboradorMB extends BeanGenerico implements Serializable {

    @Inject
    private ColaboradorController colaboradorcontroller;
    @Inject
    private CargoController cargoController;
    @Inject
    private FaltaColaboradorController faltaColaboradorController;

    private Colaborador colaborador;
    private FaltaColaborador faltaColaborador;

    private List<Colaborador> listaDeColaborador;
    private List<Cargo> listaDeCargo;
    private List<FaltaColaborador> listaDeFaltaColaboradors;

    @PostConstruct
    @Override
    public void init() {
        try {
            criarListaDeCamposDaConsulta();
            colaborador = (Colaborador) lerRegistroDaSessao("colaborador");
            if (colaborador == null) {
                faltaColaborador = new FaltaColaborador();
                colaborador = new Colaborador();
                colaborador.setAtivo(true);
                colaborador.setCargo(new Cargo());
                listaDeFaltaColaboradors = new ArrayList<>();
            } else {
                faltaColaborador = new FaltaColaborador(colaborador);
                listaDeFaltaColaboradors = faltaColaboradorController.consultarPor(colaborador);
            }
            listaDeColaborador = new ArrayList<>();
            listaDeCargo = cargoController.consultarTodosOrdenadorPor("nome");
        } catch (Exception ex) {
            Logger.getLogger(ColaboradorMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salvar() {
        try {
            colaboradorcontroller.salvar(colaborador);
            MensagensUtil.enviarMessageParamentroInfo(MensagensUtil.REGISTRO_SUCESSO, colaborador.getNome());
            init();
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(ColaboradorMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addFalta() {
        try {
            faltaColaboradorController.salvar(faltaColaborador);
            listaDeFaltaColaboradors = faltaColaboradorController.consultarPor(faltaColaborador.getColaborador());
            faltaColaborador = new FaltaColaborador(faltaColaborador.getColaborador());
            MensagensUtil.enviarMessageInfo(MensagensUtil.REGISTRO_SUCESSO);

        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(ColaboradorMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setarColaboradoConsultarFalta(Colaborador c) {
        listaDeFaltaColaboradors = faltaColaboradorController.consultarPor(c);
        faltaColaborador.setColaborador(c);

    }

    public void geraImpressaoColaborador() {
        try {
            Map<String, Object> m = new HashMap<>();
            byte[] rel = new AssistentedeRelatorio().relatorioemByte(listaDeColaborador, m, PastasRelatorio.RESOURCE_ADMINISTRATIVO, PastasRelatorio.REL_ADMINISTRATIVO_COLABORADOR, "");
            RelatorioSession.setBytesRelatorioInSession(rel);
        } catch (Exception e) {
//            erroCliente.adicionaErro(e);
        }
    }
    public void geraImpressaoFaltas() {
        try {
            listaDeFaltaColaboradors = faltaColaboradorController.consultarTodosOrdenadoColaboraroData();
            Map<String, Object> m = new HashMap<>();
            byte[] rel = new AssistentedeRelatorio().relatorioemByte(listaDeFaltaColaboradors, m, PastasRelatorio.RESOURCE_ADMINISTRATIVO, PastasRelatorio.REL_ADMINISTRATIVO_FALTA_COLABORADOR, "");
            RelatorioSession.setBytesRelatorioInSession(rel);
        } catch (Exception e) {
        }
    }
    public void geraImpressaoFaltasDoColaborador() {
        try {
            Map<String, Object> m = new HashMap<>();
            byte[] rel = new AssistentedeRelatorio().relatorioemByte(listaDeFaltaColaboradors, m, PastasRelatorio.RESOURCE_ADMINISTRATIVO, PastasRelatorio.REL_ADMINISTRATIVO_FALTA_COLABORADOR, "");
            RelatorioSession.setBytesRelatorioInSession(rel);
        } catch (Exception e) {
        }
    }

    public void desativarColaborador() {
        try {
            colaborador.setAtivo(false);
            colaboradorcontroller.atualizar(colaborador);
            MensagensUtil.enviarMessageParamentroInfo(MensagensUtil.REGISTRO_ATUALIZADO, "teress", "rewe");
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(ColaboradorMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarColaborador() {
        try {
            listaDeColaborador = colaboradorcontroller.consultarAtivo(getCampoConsuta(), getValorCampoConsuta());
        } catch (Exception ex) {
            Logger.getLogger(ColaboradorMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarColaboradorDisponivel() {
        try {
            listaDeColaborador = colaboradorcontroller.consultarColaboradorDisponivelParaObra(getValorCampoConsuta());
        } catch (Exception ex) {
            Logger.getLogger(ColaboradorMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Map<String, Object> getCampo() {
        Map<String, Object> map = new HashMap<>();
        map.put("Nome", "nome");
        map.put("CPF", "cpf");
        return map;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public List<Colaborador> getListaDeColaborador() {
        return listaDeColaborador;
    }

    public void setListaDeColaborador(List<Colaborador> listaDeColaborador) {
        this.listaDeColaborador = listaDeColaborador;
    }

    public List<Cargo> getListaDeCargo() {
        return listaDeCargo;
    }

    public void setListaDeCargo(List<Cargo> listaDeCargo) {
        this.listaDeCargo = listaDeCargo;
    }

    public FaltaColaborador getFaltaColaborador() {
        return faltaColaborador;
    }

    public void setFaltaColaborador(FaltaColaborador faltaColaborador) {
        this.faltaColaborador = faltaColaborador;
    }

    public List<FaltaColaborador> getListaDeFaltaColaboradors() {
        return listaDeFaltaColaboradors;
    }

}
