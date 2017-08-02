/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.administrativo.managedbean;

import br.com.sgpo.administrativo.controller.ClienteController;
import br.com.sgpo.administrativo.controller.MunicipioController;
import br.com.sgpo.administrativo.controller.UnidadeFederativaController;
import br.com.sgpo.administrativo.enumeration.TipoCliente;
import br.com.sgpo.administrativo.modelo.Cliente;
import br.com.sgpo.administrativo.modelo.Endereco;
import br.com.sgpo.administrativo.modelo.Municipio;
import br.com.sgpo.administrativo.modelo.UnidadeFederativa;
import br.com.sgpo.utilitario.BeanGenerico;
import br.com.sgpo.utilitario.mensagens.MensagensUtil;
import br.com.sgpo.utilitarios.relatorios.AssistentedeRelatorio;
import br.com.sgpo.utilitarios.relatorios.PastasRelatorio;
import br.com.sgpo.utilitario.relatorio.RelatorioSession;
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
 * @author ari
 */
@Named
@ViewScoped
public class ClienteMB extends BeanGenerico implements Serializable {

    @Inject
    private ClienteController clienteController;
    @Inject
    private MunicipioController municipioController;
    @Inject
    private UnidadeFederativaController unidadeFederativaController;
    private Cliente cliente;
    private UnidadeFederativa unidadeFederativa;
    private List<Cliente> listaDeClientes;
    private List<UnidadeFederativa> listaDeUnidadeFederativas;
    private List<Municipio> listaDeMunicpios;
    private boolean renderizarRepresentante;

    @PostConstruct
    @Override
    public void init() {
        try {
            criarListaDeCamposDaConsulta();
            cliente = (Cliente) lerRegistroDaSessao("cliente");
            if (cliente == null) {
                cliente = new Cliente();
                unidadeFederativa = new UnidadeFederativa();
                cliente.setEndereco(new Endereco());
            } else {
                unidadeFederativa = cliente.getEndereco().getUnidadeFederativa();
                consultarMuncipioPorUf();

            }
            listaDeClientes = new ArrayList<>();
            listaDeUnidadeFederativas = unidadeFederativaController.consultarTodosOrdenadorPor("sigla");
        } catch (Exception ex) {
            Logger.getLogger(ClienteMB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void salvar() {
        try {
            clienteController.salvar(cliente);
            MensagensUtil.enviarMessageParamentroInfo(MensagensUtil.REGISTRO_SUCESSO, cliente.getNome());
            init();
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(ClienteMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarCliente() {
        try {
            listaDeClientes = clienteController.consultarLike(getCampoConsuta(), getValorCampoConsuta().toUpperCase());
        } catch (Exception ex) {
            Logger.getLogger(ClienteMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void geraImpressaoClientes() {
        try {
            Map<String, Object> m = new HashMap<>();
            byte[] rel = new AssistentedeRelatorio().relatorioemByte(listaDeClientes, m, PastasRelatorio.RESOURCE_ADMINISTRATIVO, PastasRelatorio.REL_ADMINISTRATIVO_CLIENTE, "");
            RelatorioSession.setBytesRelatorioInSession(rel);
        } catch (Exception e) {
//            erroCliente.adicionaErro(e);
        }
    }

    @Override
    protected Map<String, Object> getCampo() {
        Map<String, Object> map = new HashMap<>();
        map.put("Nome", "nome");
        map.put("Documento", "documento");
        return map;
    }

    public void processarRenderRepresentante() {
        renderizarRepresentante = cliente.getTipoCliente().equals(TipoCliente.PESSOA_JURIDICA)
                || cliente.getTipoCliente().equals(TipoCliente.INSTITUICAO_PUBLICA);
    }

    public void consultarMuncipioPorUf() {
        listaDeMunicpios = municipioController.consultarMunicipioPor(unidadeFederativa);
    }

    public TipoCliente[] getListaDeTiposDeCliente() {
        return TipoCliente.values();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<UnidadeFederativa> getListaDeUnidadeFederativas() {
        return listaDeUnidadeFederativas;
    }

    public List<Municipio> getListaDeMunicpios() {
        return listaDeMunicpios;
    }

    public boolean isRenderizarRepresentante() {
        return renderizarRepresentante;
    }

    public UnidadeFederativa getUnidadeFederativa() {
        return unidadeFederativa;
    }

    public void setUnidadeFederativa(UnidadeFederativa unidadeFederativa) {
        this.unidadeFederativa = unidadeFederativa;
    }

    public List<Cliente> getListaDeClientes() {
        return listaDeClientes;
    }

}
