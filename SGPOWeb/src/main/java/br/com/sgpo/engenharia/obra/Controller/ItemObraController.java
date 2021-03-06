/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.obra.Controller;

import br.com.sgpo.administrativo.modelo.Fornecedor;
import br.com.sgpo.engenharia.obra.DAO.ItemObraDAO;
import br.com.sgpo.engenharia.obra.modelo.ItemObra;
import br.com.sgpo.engenharia.obra.modelo.Obra;
import br.com.sgpo.utilitario.ControllerGenerico;
import br.com.sgpo.utilitarios.enumeration.Mes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ari
 */
@Stateless
public class ItemObraController extends ControllerGenerico<ItemObra, Long> implements Serializable {

    @Inject
    private ItemObraDAO dao;

    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    public List<ItemObra> consultarPor(Obra obra) {
        return dao.consultarPor(obra);
    }

    public List<ItemObra> consultarPor(Obra obra, Mes mes) {
        List<ItemObra> lista = new ArrayList<>();
        lista = dao.consultarPor(obra, mes.getReferencia());
        Collections.sort(lista);

        return lista;
    }

    public List<ItemObra> consultarPor(Obra obra, Fornecedor f) {
        return dao.consultarPor(obra,f);
    }

    public List<ItemObra> consultarOrdenadoPorEtapaTipo(Obra obra) {
        return dao.consultarOrdenadoPorEtapaTipo(obra);
    }

}
