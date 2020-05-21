package br.com.estudo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DOCUMENTS")
public class Document implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DOCUMENT")
    private Long id;

    @Column(name = "CPF", nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(name = "RG", unique = true)
    private Integer rg;

    public Document() {
    }

    public Document(String cpf, Integer rg) {
        this.cpf = cpf;
        this.rg = rg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        return id != null ? id.equals(document.id) : document.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", cpf='" + cpf + '\'' +
                ", rg=" + rg +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCPF(String cpf) {
        this.cpf = cpf;
    }

    public Integer getRG() {
        return rg;
    }

    public void setRG(Integer rg) {
        this.rg = rg;
    }
}
