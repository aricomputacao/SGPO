/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.administrativo.managedbean;

import br.com.sgpo.administrativo.controller.FornecedorController;
import br.com.sgpo.administrativo.controller.MunicipioController;
import br.com.sgpo.administrativo.controller.UnidadeFederativaController;
import br.com.sgpo.administrativo.modelo.ContaFornecedor;
import br.com.sgpo.administrativo.modelo.Endereco;
import br.com.sgpo.administrativo.modelo.Fornecedor;
import br.com.sgpo.administrativo.modelo.Municipio;
import br.com.sgpo.administrativo.modelo.UnidadeFederativa;
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
public class FornecedorMB extends BeanGenerico implements Serializable {

    @Inject
    private FornecedorController fornecedorController;
    private Fornecedor fornecedor;
    private List<Fornecedor> listaDeFornecedor;

    @Inject
    private MunicipioController municipioController;
    @Inject
    private UnidadeFederativaController unidadeFederativaController;
    private UnidadeFederativa unidadeFederativa;
    private ContaFornecedor contaFornecedor;
    private List<UnidadeFederativa> listaDeUnidadeFederativas;
    private List<Municipio> listaDeMunicpios;
    private List<ContaFornecedor> listaDeContaFornecedor;
    private boolean renderizarRepresentante;
    private boolean renderizarAtalhos;

    @PostConstruct
    @Override
    public void init() {
        try {
            criarListaDeCamposDaConsulta();
            fornecedor = (Fornecedor) lerRegistroDaSessao("fornecedor");
            if (fornecedor == null) {
                renderizarAtalhos = false;
                fornecedor = new Fornecedor();
                unidadeFederativa = new UnidadeFederativa();
                fornecedor.setEndereco(new Endereco());
                listaDeContaFornecedor = new ArrayList<>();
                
            } else {
                renderizarAtalhos = true;
                unidadeFederativa = fornecedor.getEndereco().getUnidadeFederativa();
                consultarMuncipioPorUf();
                listaDeContaFornecedor = fornecedorController.consultarContasPor(fornecedor);
            }
            contaFornecedor = new ContaFornecedor();
            listaDeFornecedor = new ArrayList<>();
            listaDeUnidadeFederativas = unidadeFederativaController.consultarTodosOrdenadorPor("sigla");
        } catch (Exception ex) {
            Logger.getLogger(FornecedorMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salvar() {
        try {
            fornecedor = fornecedorController.salvarGenreciar(fornecedor);
            renderizarAtalhos = true;
            MensagensUtil.enviarMessageParamentroInfo(MensagensUtil.REGISTRO_SUCESSO, fornecedor.getNome());
//            init();
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(FornecedorMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addConta(){
        try {
            contaFornecedor.setFornecedor(fornecedor);
            listaDeContaFornecedor.add(contaFornecedor);
            fornecedorController.addConta(contaFornecedor);
            contaFornecedor = new ContaFornecedor();
        } catch (Exception ex) {
            Logger.getLogger(FornecedorMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void excluirConta(ContaFornecedor cf){
        try {
            listaDeContaFornecedor.remove(cf);
            fornecedorController.remverContaFornecedor(cf);
        } catch (Exception ex) {
            Logger.getLogger(FornecedorMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    public void consultarFornecedor() {
        try {
            listaDeFornecedor = fornecedorController.consultarLike(getCampoConsuta(), getValorCampoConsuta());
        } catch (Exception ex) {
            Logger.getLogger(FornecedorMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void consultarMuncipioPorUf() {
        listaDeMunicpios = municipioController.consultarMunicipioPor(unidadeFederativa);
    }
    
    public void geraImpressaoFornecedor() {
        try {
            Map<String, Object> m = new HashMap<>();
            byte[] rel = new AssistentedeRelatorio().relatorioemByte(listaDeFornecedor, m, PastasRelatorio.RESOURCE_ADMINISTRATIVO, PastasRelatorio.REL_ADMINISTRATIVO_FORNECEDOR, "");
            RelatorioSession.setBytesRelatorioInSession(rel);
        } catch (Exception e) {
//            erroCliente.adicionaErro(e);
        }
    }
    public void geraImpressaoDadosFornecedor() {
        try {
            Map<String, Object> m = new HashMap<>();
            byte[] rel = new AssistentedeRelatorio().relatorioemByte(listaDeContaFornecedor, m, PastasRelatorio.RESOURCE_ADMINISTRATIVO, PastasRelatorio.REL_ADMINISTRATIVO_DADOS_FORNECEDOR, "");
            RelatorioSession.setBytesRelatorioInSession(rel);
        } catch (Exception e) {
//            erroCliente.adicionaErro(e);
        }
    }
    
    
 

    @Override
    protected Map<String, Object> getCampo() {
        Map<String, Object> map = new HashMap<>();
        map.put("Nome", "nome");
        return map;
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

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<Fornecedor> getListaDeFornecedor() {
        return listaDeFornecedor;
    }

    public List<ContaFornecedor> getListaDeContaFornecedor() {
        return listaDeContaFornecedor;
    }

    public ContaFornecedor getContaFornecedor() {
        return contaFornecedor;
    }

    public void setContaFornecedor(ContaFornecedor contaFornecedor) {
        this.contaFornecedor = contaFornecedor;
    }

    public boolean isRenderizarAtalhos() {
        return renderizarAtalhos;
    }
    
    

}
