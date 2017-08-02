/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.obra.Controller;

import br.com.sgpo.engenharia.obra.DAO.NotaObraDAO;
import br.com.sgpo.engenharia.obra.modelo.NotaObra;
import br.com.sgpo.engenharia.obra.modelo.Obra;
import br.com.sgpo.utilitario.ControllerGenerico;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ari
 */
@Stateless
public class NotaObraController extends ControllerGenerico<NotaObra, Long> implements Serializable {

    @Inject
    private NotaObraDAO dao;

    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }

    public void addDocumento(NotaObra notaObra,byte[] conteudo) throws IOException, Exception {
         notaObra = dao.atualizarGerenciar(notaObra);
         notaObra.addDocumento(conteudo);
    }

    public List<NotaObra> consultarPor(Obra obra) {
        return dao.consultarPor(obra);
     }
}
