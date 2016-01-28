/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.engenharia.Controller;

import br.com.sgpo.engenharia.DAO.DocumentoProjetoDAO;
import br.com.sgpo.engenharia.modelo.DocumentoProjeto;
import br.com.sgpo.utilitario.ControllerGenerico;
import br.com.sgpo.utilitarios.ManipuladorDeArquivo;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ari
 */
@Stateless
public class DocumentoProjetoController extends ControllerGenerico<DocumentoProjeto, Long> implements Serializable{

    @Inject
    private DocumentoProjetoDAO dao;
    
    @PostConstruct
    @Override
    protected void inicializaDAO() {
        setDAO(dao);
    }
    
    
    public void addDocumento(DocumentoProjeto documentoProjeto, byte[] conteudo) throws IOException {
         documentoProjeto = dao.atualizarGerenciar(documentoProjeto);
         
         if (conteudo != null) {

            //Remove do local
            ManipuladorDeArquivo.checarSeExisteExcluir(documentoProjeto.getCaminhoArquivo());

       
            //Grava no local
            ManipuladorDeArquivo.gravarArquivoLocalmente(ManipuladorDeArquivo.getDiretorioDocumentos(),documentoProjeto.getNomeDoArquivoComExtencao() , conteudo);

       
        }
     }
}
