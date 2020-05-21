package br.com.estudo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PHONES")
public class Phone implements Serializable {

    public enum TypePhone{
        RESIDENCIAL, CELULAR, COMERCIAL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PHONE")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE_PHONE", nullable = false)
    private TypePhone type;

    @Column(name = "NUMBER", nullable = false)
    private String number;

    // Ao utilizar o CascadeType.ALL se eu deletar um telefone irá ser deletado todas insformações da pessoa, documento e telefones associados
    // @ManyToOne(cascade = CascadeType.ALL)

    // Ao utilizar o CascadeType.PERSIST e CascadeType.MERGE se eu deletar um telefone irá ser deletado apenas o número informado
    // Consigo inserir(PERSIST) uma nova pessoa apartir de Phone e também atualizar informações (MERGE)
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    public Phone() {
    }

    public Phone(TypePhone type, String number) {
        this.type = type;
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Phone phone = (Phone) o;

        return id != null ? id.equals(phone.id) : phone.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", type=" + type +
                ", number='" + number + '\'' +
                ", person=" + person +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypePhone getType() {
        return type;
    }

    public void setType(TypePhone type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
