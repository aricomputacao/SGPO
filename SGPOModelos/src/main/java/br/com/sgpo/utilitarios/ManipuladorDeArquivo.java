/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sgpo.utilitarios;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author ari
 */
public class ManipuladorDeArquivo {

    public static final String PATH_NOTIFICACOES_WINDOWS = "C:" + File.separator;
    public static final String PATH_NOTIFICACOES_LINUX = "/" + File.separator;
//    public static final String PASTA_PECAS = "pecas" + SEPARADOR;
//    public static final String PASTA_NOTIFICACOES = "notificacoes" + SEPARADOR;
//    public static final String PATH_FILES = "pecas" + SEPARADOR + "documentos";

    private static boolean ehLinux() {
        String os = System.getProperties().getProperty("os.name").toLowerCase();
        if (os.contains("windows")) {
            return false;
        }
        return true;
    }

    public static String retornaDiretorioDoSistemaOperacional() {
        if (ehLinux()) {
            return PATH_NOTIFICACOES_LINUX;
        } else {
            return PATH_NOTIFICACOES_WINDOWS;
        }
    }

    public static void excluirAquivo(String caminhoCompletoDoArquivo) {
        File file = new File(caminhoCompletoDoArquivo);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Metódo para gravar arquivo localmente
     *
     * @param rotuloDaPasta nome do tipo de arquivos que seão gravados dentro da
     * pasta
     * @param identificadorPasta identificador único dauqla sub pasta
     * @param nomeArquivo
     * @param conteudo
     * @throws IOException
     * @throws Exception
     */
    private static void gravaArquivo(String rotuloDaPasta, String identificadorPasta, String nomeArquivo, byte[] conteudo) throws IOException, Exception {
        File pastaGeral = new File(PATH_NOTIFICACOES_WINDOWS + rotuloDaPasta + identificadorPasta);
        if (ehLinux()) {
            pastaGeral = new File(PATH_NOTIFICACOES_LINUX + rotuloDaPasta + identificadorPasta);
        }

        if (!pastaGeral.exists()) {
            if (!pastaGeral.mkdirs()) {
                throw new Exception("Erro ao cria pasta relativa");
            }
        } else {
            pastaGeral.delete();
        }

        try (FileOutputStream writer = new FileOutputStream(pastaGeral + File.separator + nomeArquivo)) {
            writer.write(conteudo);
            writer.flush();
        }
    }

    private static void gravaArquivo(String caminhoCompletoDoArquivo, byte[] conteudo) throws IOException {
        File pastaGeral = new File(caminhoCompletoDoArquivo);
        if (ehLinux()) {
            pastaGeral = new File(caminhoCompletoDoArquivo);
        }

        if (!pastaGeral.exists()) {
            if (!pastaGeral.mkdirs()) {
                throw new IOException("Erro ao cria pasta relativa");
            }
        } else {
            pastaGeral.delete();
        }

        FileOutputStream writer = new FileOutputStream(caminhoCompletoDoArquivo);
        writer.write(conteudo);
        writer.flush();
    }

// Listar os arquivos de uma pasta
    /**
     *
     * @param rotuloDaPasta nome da pasta que identifica o tipo de arquivo
     * @param pasta pasta com identificador único
     * @return
     */
    public static List<File> aquivos(String rotuloDaPasta, String pasta) {
        String relativo = retornaDiretorioDoSistemaOperacional() + rotuloDaPasta + pasta;
        File f = new File(relativo);
        if (f.exists()) {
            return Arrays.asList(f.listFiles());
        } else {
            return new ArrayList<>();
        }
    }

    //Grava Arquivo localmente
    public static void gravarArquivoLocalmente(FileUploadEvent event, String rotuloDaPasta, String pasta) throws IOException, Exception {
        UploadedFile f = event.getFile();
        ManipuladorDeArquivo.gravaArquivo(rotuloDaPasta, pasta, f.getFileName(), f.getContents());
    }

    //Grava Arquivo localmente
    public static void gravarArquivoLocalmente(String caminhoCompletoDoArquivo, byte[] conteudoArquivo) throws IOException {
        ManipuladorDeArquivo.gravaArquivo(caminhoCompletoDoArquivo, conteudoArquivo);
    }

    /**
     * Criar o arquivo com os dados(bytes) do banco
     *
     * @param file
     * @param pasta
     * @param nomeArquivo
     * @param conteudoArquivo
     * @throws Exception
     */
//    private static void criarArquivoCasoNaoExista(File file,String pecaOuNotificacao,String pasta, String nomeArquivo, byte[] conteudoArquivo) throws Exception {
//        if (!file.exists()) {
//            ManipuladorDeArquivo.gravaArquivo(pecaOuNotificacao,pasta, nomeArquivo, conteudoArquivo);
//        }
//    }
    /**
     * O arquivo deve ser passado com extenção
     *
     * @param rotuloDaPasta
     * @param conteudoArquivo
     * @return
     */
//    public static String checarExistenciaDoArquivoNaPasta(String pecaOuNotificacao,String pasta, String nomeArquivo, byte[] conteudoArquivo) throws Exception {
//        File file;
//
//        String caminho = pecaOuNotificacao.concat(pasta.concat(SEPARADOR.concat(nomeArquivo)));
//
//        if (ehLinux()) {
//            file = new File(PATH_NOTIFICACOES_LINUX + SEPARADOR + caminho);
//        } else {
//            file = new File(PATH_NOTIFICACOES_WINDOWS + SEPARADOR + caminho);
//
//        }
//        if (!file.exists()) {
//            ManipuladorDeArquivo.gravaArquivo(pecaOuNotificacao,pasta, nomeArquivo, conteudoArquivo);
//        }
//        return file.getAbsolutePath();
//
//    }
//
//    public static String caminhoDoArquivo(String pecaOuNotificacao,String pasta, String nome) {
//        File pastaGeral = new File(PATH_NOTIFICACOES_WINDOWS + pecaOuNotificacao + pasta);
//        if (ehLinux()) {
//            pastaGeral = new File(PATH_NOTIFICACOES_LINUX + pecaOuNotificacao + pasta);
//        }
//        return pastaGeral + SEPARADOR + nome;
//    }
//    public static String diretorioRelativoNotificacao(String diretorioRealDaAplicacao) {
//        String localAbs = diretorioRealDaAplicacao;
//        String relativo = localAbs.substring(0, localAbs.lastIndexOf(SEPARADOR));
//        relativo = relativo.substring(0, relativo.lastIndexOf(SEPARADOR));
//        relativo = relativo.substring(0, relativo.lastIndexOf(SEPARADOR));
//
//        if (ehLinux()) {
//            return relativo + SEPARADOR + PATH_NOTIFICACOES_LINUX;
//        } else {
//            return relativo + SEPARADOR + PATH_NOTIFICACOES_WINDOWS;
//        }
//
//    }
//
//    public static String diretorioRelativo() {
//        String localAbs = new AssistentedeRelatorio().getDiretorioReal(File.separator);
//        String relativo = localAbs.substring(0, localAbs.lastIndexOf(File.separator));
//        relativo = relativo.substring(0, relativo.lastIndexOf(File.separator));
//        relativo = relativo.substring(0, relativo.lastIndexOf(File.separator));
//        return relativo + File.separator + PATH_FILES;
//
//    }
}
