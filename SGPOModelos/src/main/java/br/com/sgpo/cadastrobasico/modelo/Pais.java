package br.com.sgpo.cadastrobasico.modelo;

import br.com.sgpo.utilitarios.RemoveCaracteresUtil;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Tabela de Paises Importada do site da receita Federal
 * http://www.bcb.gov.br/Rex/TabPaises/Ftp/paises.txt
 *
 * @author gilmario
 */
@Entity
@Table(name = "pais", schema = "cadastro_basico")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Pais implements Serializable {

    @Id
    @NotBlank
    @Length(max = 5)
    @Column(name = "pai_id", nullable = false, length = 5)
    private String id;
    @NotBlank
    @Length(max = 50)
    @Column(name = "pai_nome", nullable = false, length = 50, unique = true)
    private String nome;
    @Column(nullable = false, name = "und_ativo", columnDefinition = "boolean default true")
    private boolean ativo;

    public Pais() {
    }

    public Pais(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = RemoveCaracteresUtil.removeAccentos(nome.toUpperCase());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pais other = (Pais) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Pais{" + "id=" + id + ", nome=" + nome + '}';
    }

}
