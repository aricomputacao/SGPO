/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.administrativo.managedbean;

import br.com.sgpo.administrativo.controller.CargoController;
import br.com.sgpo.administrativo.modelo.Cargo;
import br.com.sgpo.utilitario.BeanGenerico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ari
 */
@Named
@ViewScoped
public class CargoMB extends BeanGenerico implements Serializable{

    @Inject
    private CargoController cargoController;
    private Cargo cargo;
    private List<Cargo> listaDeCargos;
    
    
    @PostConstruct
    @Override
    public void init() {
        cargo = new Cargo();
        listaDeCargos = new ArrayList<>();
        criarListaDeCamposDaConsulta();
    }

    
    public void consultar(){
        try {
            listaDeCargos = cargoController.consultarLike(getCampoConsuta(), getValorCampoConsuta());
        } catch (Exception ex) {
            Logger.getLogger(CargoMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected Map<String, Object> getCampo() {
       Map<String,Object> map = new HashMap<>();
       map.put("Nome", "nome");
       return map;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public List<Cargo> getListaDeCargos() {
        return listaDeCargos;
    }
    
    
    
}
