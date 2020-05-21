package br.com.estudo.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
/*
Foi criado um index composto com as colunas FIRST_NAME e LAST_NAME
e definimos ele como UNIQUE, sendo assim, só será possivel inserir uma
MARIA APARECIDA, JOSE SILVA ou qualquer outro nome e sobrenome
 */
@Table(
        name = "PERSONS",
        indexes = {@Index(name="IDX_PERSON_NAME", columnList = "FIRST_NAME, LAST_NAME", unique = true)}
)

/* Se não utilizarmos o @Table o Hibernate irá criar ou tentar localizar
uma tabela com o nome da classe no caso Person */

public class Person implements Serializable {

    /*
    Ao invés de utilizar variáveis do tipo primitiva
    devemos sempre utilizar as do tipo Object (reference)
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PERSON")
    private Long id;
    // Se não utilizarmos o @Column o Hibernate irá utilizar o nome id

    @Column(name = "FIRST_NAME", nullable = false, length = 30)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, length = 60)
    private String lastName;

    @Column(name = "AGE", nullable = false)
    private Integer age;

    /* Relacionamentos:

     Unidirecional ->

     @ Apenas um lado do relacionamento trabalhando pelos dois
     @ Sendo assim caso eu utilizar o relacionamento do lado da tabela PERSONS
     irá aparecer uma coluna (DOCUMENT_ID) referente a outra entidade

     Bidirecional ->

     @ Os dois lados trabalhando pelo relacionamento
     @ Sendo assim em PERSONS iria aparecer uma coluna (DOCUMENT_ID)
     e em DOCUMENT (PERSON_ID)

     Quando eu consultar um objeto PERSONS eu terei um DOCUMENTS
     Quando eu consultar um objeto DOCUMENTS eu terei um PERSONS

     */

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DOCUMENT_ID") // Nome da coluna que irá aparecer na tabela
    private Document document;

    // Bidirecional com Phone: É uma lista, pois uma pessoa pode ter mais de um telefone
    // mappedBy: Serve para informar qual variável ou objeto que esse atributo irá se relacionar na entidade PHONE
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Phone> phones;

    //@Fetch utilizado para resolver o erro: cannot simultaneously fetch multiple bags:
    @Fetch(value = FetchMode.SUBSELECT)
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "PERSONS_ADDRESSES",
            joinColumns = @JoinColumn(name = "ID_PERSON"),
            inverseJoinColumns = @JoinColumn(name = "ID_ADDRESS")
    )
    private List<Address> addresses;

    /* Método para associar os telefones a pessoa ao inserir uma lista utilizando o método insertPhoneByPerson
       Caso eu não utilize esse método ao inserir uma pessoa com a lista de telefones o hibernate não consegue setar o ID na tabela de pessoas
       Tem outra forma de fazer, que está comentada lá no método
     */
    public void addPhone(Phone phone){
        if(phones == null){
            phones = new ArrayList<Phone>();
        }
        phone.setPerson(this);
        phones.add(phone);
    }

    public void dellPhone(Phone phone){
        if(phones != null){
            phones.remove(phone);
        }
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != null ? !id.equals(person.id) : person.id != null) return false;
        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) return false;
        if (lastName != null ? !lastName.equals(person.lastName) : person.lastName != null) return false;
        return age != null ? age.equals(person.age) : person.age == null;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", document=" + document +
                '}';
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
