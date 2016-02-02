/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.administrativo.managedbean;

import br.com.sgpo.administrativo.controller.ContaEmailController;
import br.com.sgpo.administrativo.controller.EmpresaController;
import br.com.sgpo.administrativo.controller.MunicipioController;
import br.com.sgpo.administrativo.controller.UnidadeFederativaController;
import br.com.sgpo.administrativo.modelo.Cliente;
import br.com.sgpo.administrativo.modelo.ContaEmail;
import br.com.sgpo.administrativo.modelo.Empresa;
import br.com.sgpo.administrativo.modelo.Endereco;
import br.com.sgpo.administrativo.modelo.Municipio;
import br.com.sgpo.administrativo.modelo.UnidadeFederativa;
import br.com.sgpo.utilitario.BeanGenerico;
import br.com.sgpo.utilitario.mensagens.MensagensUtil;
import br.com.sgpo.utilitarios.ManipuladorDeArquivo;
import static java.io.File.separator;
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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author ari
 */
@Named
@ViewScoped
public class EmpresaMB extends BeanGenerico implements Serializable {

    @Inject
    private EmpresaController empresaController;
    @Inject
    private UnidadeFederativaController unidadeFederativaController;
    @Inject
    private MunicipioController municipioController;
    @Inject
    private ContaEmailController contaEmailController;
    private UnidadeFederativa uf;
    private Empresa empresa;
    private ContaEmail contaEmail;
    private List<UnidadeFederativa> listaDeUnidadeFederativas;
    private List<Cliente> listaDeClientes;
    private List<Empresa> listaDeEmpresas;
    private List<Municipio> listaDeMunicipios;
    private List<ContaEmail> listaDeEmails;
    private UploadedFile arquivoUpload;
    private byte logo[];

    @PostConstruct
    @Override
    public void init() {
        try {
            criarListaDeCamposDaConsulta();
            empresa = (Empresa) lerRegistroDaSessao("empresa");
            contaEmail = new ContaEmail();
            uf = new UnidadeFederativa();
            if (empresa == null) {
                empresa = new Empresa();
                empresa.setAtivo(true);
                empresa.setEndereco(new Endereco());
                listaDeEmails = new ArrayList<>();
            } else {
                uf = empresa.getEndereco().getUnidadeFederativa();
                logo = ManipuladorDeArquivo.lerArquivoEmByte(getDiretorioReal("resources" + separator + "images"+separator+empresa.getNome()+".png"));
                contaEmail.setEmpresa(empresa);
                listaDeEmails = contaEmailController.cosultar(empresa);
                consultarMuncipioPorUf();

            }
            listaDeClientes = new ArrayList<>();
            listaDeUnidadeFederativas = unidadeFederativaController.consultarTodosOrdenadorPor("sigla");
        } catch (Exception ex) {
            Logger.getLogger(EmpresaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salvar() {
        try {
            empresaController.salvar(empresa);
            empresaController.addLogo(empresa.getNome(), logo, getDiretorioReal("resources" + separator + "images"));
            contaEmail.setEmpresa(empresa);
            MensagensUtil.enviarMessageParamentroInfo(MensagensUtil.REGISTRO_SUCESSO, empresa.getNome());
            init();
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(EmpresaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addContaEmail(){
        try {
            contaEmailController.salvar(contaEmail);
            contaEmail =  new ContaEmail();
            contaEmail.setEmpresa(empresa);
            listaDeEmails = contaEmailController.cosultar(empresa);
        } catch (Exception ex) {
            Logger.getLogger(EmpresaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void desativarEmpresa(){
        try {
            empresa.setAtivo(false);
            empresaController.atualizar(empresa);
            MensagensUtil.enviarMessageParamentroInfo(MensagensUtil.REGISTRO_ATUALIZADO, "teress","rewe");
        } catch (Exception ex) {
            MensagensUtil.enviarMessageErro(MensagensUtil.REGISTRO_FALHA);
            Logger.getLogger(EmpresaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarEmpresa() {
        try {
            listaDeEmpresas = empresaController.consultarTodos("nome", getCampoConsuta(), getValorCampoConsuta());
        } catch (Exception ex) {
            Logger.getLogger(EmpresaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consultarMuncipioPorUf() {
        listaDeMunicipios = municipioController.consultarMunicipioPor(uf);
    }

    public void fileUploud(FileUploadEvent event) {
        try {
            logo = event.getFile().getContents();
        } catch (Exception ex) {
            Logger.getLogger(EmpresaMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected Map<String, Object> getCampo() {
        Map<String, Object> map = new HashMap<>();
        map.put("Nome", "nome");

        return map;
    }


    public boolean renderAddEmail(){
        return empresa.getId() != null;
    }

    public UnidadeFederativa getUf() {
        return uf;
    }

    public void setUf(UnidadeFederativa uf) {
        this.uf = uf;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public List<UnidadeFederativa> getListaDeUnidadeFederativas() {
        return listaDeUnidadeFederativas;
    }

    public List<Cliente> getListaDeClientes() {
        return listaDeClientes;
    }

    public List<Empresa> getListaDeEmpresas() {
        return listaDeEmpresas;
    }

    public List<Municipio> getListaDeMunicipios() {
        return listaDeMunicipios;
    }

    public UploadedFile getArquivoUpload() {
        return arquivoUpload;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setArquivoUpload(UploadedFile arquivoUpload) {
        this.arquivoUpload = arquivoUpload;
    }

    public ContaEmail getContaEmail() {
        return contaEmail;
    }

    public void setContaEmail(ContaEmail contaEmail) {
        this.contaEmail = contaEmail;
    }

    public List<ContaEmail> getListaDeEmails() {
        return listaDeEmails;
    }

    
}
