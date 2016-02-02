/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.utilitarios;

import br.com.sgpo.engenharia.enumeration.TipoExtencaoArquivo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author ari
 */
public class ManipuladorDeArquivo {

    public static final String PATH_WINDOWS = "C:" + File.separator;
    public static final String PATH_LINUX = "/" + File.separator;
    public static final String PASTA_LOGOS = "logos" + File.separator;
    public static final String PASTA_DOCUMENTOS_PROJETO = "documentos" + File.separator+"projetos"+File.separator;

    public static String getDiretorioLogos() {
        if (ehLinux()) {
            return PATH_LINUX + PASTA_LOGOS;
        } else {
            return PATH_WINDOWS + PASTA_LOGOS;
        }
    }

    public static String getDiretorioDocumentos() {
        if (ehLinux()) {
            return PATH_LINUX + PASTA_DOCUMENTOS_PROJETO;
        } else {
            return PATH_WINDOWS + PASTA_DOCUMENTOS_PROJETO;
        }
    }

    private static boolean ehLinux() {
        String os = System.getProperties().getProperty("os.name").toLowerCase();
        if (os.contains("windows")) {
            return false;
        }
        return true;
    }

    public static String retornaDiretorioDoSistemaOperacional() {
        if (ehLinux()) {
            return PATH_LINUX;
        } else {
            return PATH_WINDOWS;
        }
    }

    public static void excluirAquivo(String caminhoCompletoDoArquivo) {
        File file = new File(caminhoCompletoDoArquivo);
        if (file.exists()) {
            file.delete();
        }
    }


    private static void criaroDiretorio(String caminhoCompletoDoArquivo) throws IOException {
        File pastaGeral = new File(caminhoCompletoDoArquivo);
        if (!pastaGeral.exists()) {
            if (!pastaGeral.mkdirs()) {
                throw new IOException("Erro ao cria pasta relativa");
            }
        }

    }

    private static void gravaArquivo(String pasta, String arquivoComExtensao, byte[] conteudo) throws IOException {

        File pastaGeral = new File(pasta);
        File arquivo = new File(pasta + File.separator + arquivoComExtensao);
        if (ehLinux()) {
            pastaGeral = new File(pasta);
        }

        if (!pastaGeral.exists()) {
            if (!pastaGeral.mkdirs()) {
                throw new IOException("Erro ao cria pasta relativa");
            }
        }
        if (arquivo.exists()) {
            arquivo.delete();
        }

        FileOutputStream writer = new FileOutputStream(arquivo);
        writer.write(conteudo);
        writer.flush();
        writer.close();
    }

    public static List<File> listaDeArquivosDaPasta(String pasta) {
        String relativo = pasta;
        File f = new File(relativo);
        if (f.exists()) {
            return Arrays.asList(f.listFiles());
        } else {
            return new ArrayList<>();
        }
    }

    //Criar pasta para o arquivo
    public static void criarDiretorioLocal(String nomeCompletoComExtencao) throws IOException {
        ManipuladorDeArquivo.criaroDiretorio(nomeCompletoComExtencao);
    }

    //Grava Arquivo localmente
    public static void gravarArquivoLocalmente(String pasta, String arquivoComExtensao, byte[] conteudo) throws IOException {
        ManipuladorDeArquivo.gravaArquivo(pasta, arquivoComExtensao, conteudo);
    }

    public static byte[] lerArquivoEmByte(String caminhoCompletoComExtensao) throws IOException {
        if (caminhoCompletoComExtensao != null) {
            File file = new File(caminhoCompletoComExtensao);

            InputStream is = null;
            byte[] buffer = null;

            is = new FileInputStream(file);
            buffer = new byte[is.available()];
            is.close();

            return buffer;
        }
        return null;

    }

    public static boolean checarSeExisteExcluir(String caminhoCompletoComExtensao) {
        if (caminhoCompletoComExtensao != null) {
            File file = new File(caminhoCompletoComExtensao);
            if (file.exists()) {
                file.delete();
                return true;
            }
            return false;

        }
        return false;

    }

    public static StreamedContent download(String caminhoCompleto, String nomeArquivoComExtencao, TipoExtencaoArquivo extencaoArquivo) throws FileNotFoundException {
        StreamedContent file;
        InputStream stream = new FileInputStream(caminhoCompleto);
        file = new DefaultStreamedContent(stream, extencaoArquivo.getMime(), nomeArquivoComExtencao);

        return file;
    }

}
