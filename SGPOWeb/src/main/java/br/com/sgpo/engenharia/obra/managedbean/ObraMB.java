package br.com.sgpo.engenharia.obra.managedbean;

import br.com.sgpo.administrativo.controller.ColaboradorController;
import br.com.sgpo.administrativo.controller.MunicipioController;
import br.com.sgpo.administrativo.controller.UnidadeFederativaController;
import br.com.sgpo.administrativo.modelo.Endereco;
import br.com.sgpo.administrativo.modelo.Municipio;
import br.com.sgpo.administrativo.modelo.UnidadeFederativa;
import br.com.sgpo.engenharia.obra.Controller.ObraController;
import br.com.sgpo.engenharia.obra.modelo.Obra;
import br.com.sgpo.utilitario.BeanGenerico;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ari
 */
@Named
@ViewScoped
public class ObraMB extends BeanGenerico implements Serializable {

    @Inject
    private ObraController obraController;
    @Inject
    private MunicipioController municipioController;
    @Inject
    private UnidadeFederativaController unidadeFederativaController;

    private List<Obra> listaDeObras;
    private List<UnidadeFederativa> listaDeUnidadeFederativas;
    private List<Municipio> listaDeMunicpios;

    private UnidadeFederativa unidadeFederativa;
    private Obra obra;

    @PostConstruct
    @Override
    public void init() {
        try {
            obra = (Obra) lerRegistroDaSessao("obra");
            if (obra == null) {
                obra = new Obra();
                obra.setEndereco(new Endereco());
            } else {
                unidadeFederativa = obra.getEndereco().getUnidadeFederativa();
                listaDeMunicpios = municipioController.consultarMunicipioPor(unidadeFederativa);

            }
            listaDeUnidadeFederativas = unidadeFederativaController.consultarTodosOrdenadorPor("sigla");
        } catch (Exception ex) {
            Logger.getLogger(ObraMB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected Map<String, Object> getCampo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Obra> getListaDeObras() {
        return listaDeObras;
    }

    public List<UnidadeFederativa> getListaDeUnidadeFederativas() {
        return listaDeUnidadeFederativas;
    }

    public List<Municipio> getListaDeMunicpios() {
        return listaDeMunicpios;
    }

    public UnidadeFederativa getUnidadeFederativa() {
        return unidadeFederativa;
    }

    public void setUnidadeFederativa(UnidadeFederativa unidadeFederativa) {
        this.unidadeFederativa = unidadeFederativa;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    
    
}
