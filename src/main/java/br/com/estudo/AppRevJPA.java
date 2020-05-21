package br.com.estudo;

import br.com.estudo.dao.AddressDAO;
import br.com.estudo.dao.DocumentDAO;
import br.com.estudo.dao.PersonDAO;
import br.com.estudo.dao.PhoneDAO;
import br.com.estudo.entity.Address;
import br.com.estudo.entity.Document;
import br.com.estudo.entity.Person;
import br.com.estudo.entity.Phone;

import java.util.Arrays;
import java.util.List;

public class AppRevJPA {

    public static void main(String[] args) {

        //insertPerson();
        //findPersonById();
        //findAllPersons();
        //countPersons();
        //findByLastName();
        //findByAge();
        //findByFullName();
        //updatePerson();
        //deletePerson();

        //insertDocument();
        //updateDocument();
        //findPersonByCpf();

        //inserPhone();
        //insertPhoneByPerson();
        //updatePhone();
        //updatePhoneByPerson();
        //deleteOnCascade();

        //insertAddressByPerson();
        //insertPersonByAddress();
        findByCity();
    }

    private static void findByCity() {

        List<Address> addresses = new AddressDAO().findByCity("Rio de Janeiro");

        for(Address address: addresses){
            System.out.println(address.toString());
        }

    }

    private static void insertPersonByAddress() {

        Person person = new PersonDAO().findById(7L);

        Address ad1 = new Address();
        ad1.setCity("Porto Alegre");
        ad1.setStreet("Av. Beira Rio, 1002");
        ad1.setType(Address.TypeAddress.RESIDENCIAL);
        ad1.setPersons(Arrays.asList(person));

        AddressDAO dao = new AddressDAO();
        dao.save(ad1);

        System.out.println(dao.findById(ad1.getId()));
    }

    private static void insertAddressByPerson() {

        Address ad1 = new Address();
        ad1.setCity("Porto Alegre");
        ad1.setStreet("Av. Beira Rio, 1002");
        ad1.setType(Address.TypeAddress.RESIDENCIAL);

        Address ad2 = new Address();
        ad2.setCity("Porto Alegre");
        ad2.setStreet("Av. Floresta, 9455");
        ad2.setType(Address.TypeAddress.COMERCIAL);

        Person person = new Person();
        person.setFirstName("Isabel");
        person.setLastName("Martins");
        person.setAge(50);
        person.setDocument(new Document("655656555356",555556565));
        person.addPhone(new Phone(Phone.TypePhone.RESIDENCIAL, "655565565656"));
        person.addPhone(new Phone(Phone.TypePhone.COMERCIAL, "55556556565656"));
        person.setAddresses(Arrays.asList(ad1, ad2));

        new PersonDAO().save(person);

        System.out.println(new PersonDAO().findById(person.getId()));

    }

    private static void deleteOnCascade() {

        //new PersonDAO().delete(3L);

        //new PhoneDAO().delete(1L);

        PhoneDAO dao = new PhoneDAO();

        Phone phone = dao.findById(6L);

        System.out.println(phone.toString());

        phone.getPerson().dellPhone(phone);

        dao.delete(phone);
    }

    private static void updatePhoneByPerson() {

        Person person = new PersonDAO().findById(3L);

        for(Phone phone : person.getPhones()){

            System.out.println("1- "+ phone.toString());

            if(Phone.TypePhone.COMERCIAL == phone.getType()){

                phone.setType(Phone.TypePhone.RESIDENCIAL);

            }
        }

        new PersonDAO().update(person);

        for(Phone phone : person.getPhones()){

            System.out.println("2- "+ phone.toString());

        }

    }

    private static void updatePhone() {

        Person person = new PersonDAO().findById(3L);

        PhoneDAO dao = new PhoneDAO();

        Phone phone = dao.findById(3L);

        phone.setPerson(person);

        dao.update(phone);

        phone = dao.findById(phone.getId());

        System.out.println(phone.toString());

    }

    private static void insertPhoneByPerson() {

        Phone ph1 = new Phone(Phone.TypePhone.CELULAR, "99999900");
        Phone ph2 = new Phone(Phone.TypePhone.COMERCIAL, "88888811");

        Person person = new Person();
        person.setFirstName("Bob");
        person.setLastName("Silva");
        person.setAge(30);
        person.setDocument( new Document("145.256.365.33", 288236540));

        //ph1.setPerson(person);
        //ph2.setPerson(person);

        //person.setPhones(Arrays.asList(ph1, ph2));
        person.addPhone(ph1);
        person.addPhone(ph2);

        new PersonDAO().save(person);

    }

    private static void inserPhone() {

        Person person = new Person();
        person.setFirstName("Gilson");
        person.setLastName("Figueira");
        person.setAge(25);
        person.setDocument( new Document("145.256.365.98", 100236598));

        Phone phone = new Phone(Phone.TypePhone.CELULAR, "55555555");
        phone.setPerson(person);

        PhoneDAO dao = new PhoneDAO();

        dao.save(phone);

        phone = dao.findById(phone.getId());

        System.out.println(phone.toString());


    }

    private static void findPersonByCpf() {

        Person p = new PersonDAO().findByCpf("431.121.112-00");

        System.out.println(p.toString());

    }

    private static void updateDocument() {

        Document doc = new DocumentDAO().findById(1L);

        System.out.println(doc.toString());

        doc.setCPF("431.121.112-00");

        new DocumentDAO().update(doc);

        System.out.println(new DocumentDAO().findById(1L).toString());

    }

    private static void insertDocument() {

        Person p1 = new Person();
        p1.setFirstName("Aline");
        p1.setLastName("De Souza");
        p1.setAge(24);
        p1.setDocument(new Document("123.146.111-79", 123456789));

        new PersonDAO().save(p1);

        System.out.println(p1.toString());

    }


    // *********** PERSON *********** //

    private static void deletePerson() {
        new PersonDAO().delete(3L);
    }

    private static void updatePerson() {

        Person p1 = new PersonDAO().findById(1L);
        System.out.println(p1.toString());

        p1.setFirstName("Agnaldo");
        new PersonDAO().update(p1);

        Person p2 = new PersonDAO().findById(1L);
        System.out.println(p2.toString());
    }

    private static void findByFullName() {

        Person p = new PersonDAO().findByFullName("Erik", "Moreira");
        System.out.println(p.toString());
    }

    private static void findByAge() {

        List<Person> persons = new PersonDAO().findAgeIsBetween(1,28);
        for(Person p : persons){
            System.out.println(p.toString());
        }

    }

    private static void findByLastName() {

        List<Person> persons = new PersonDAO().findByLastName("Moreira");
        for(Person p : persons){
            System.out.println(p.toString());
        }

    }

    private static void countPersons() {

        Long total = new PersonDAO().count();

        System.out.println("Total of persons " + total);
    }

    private static void findAllPersons() {

        List<Person> persons = new PersonDAO().findAll();

        for(Person p : persons){
            System.out.println(p.toString());
        }

    }

    private static void findPersonById() {

        Person p1 = new PersonDAO().findById(2L);

        Person p2 = new PersonDAO().findById(4L);

        System.out.println(p1.toString());
        System.out.println(p2.toString());

    }

    private static void insertPerson() {
        Person p1 = new Person();
        p1.setFirstName("Erik");
        p1.setLastName("Moreira");
        p1.setAge(24);

        new PersonDAO().save(p1);

        System.out.println(p1.toString());

        Person p2 = new Person();
        p2.setFirstName("Adelmo");
        p2.setLastName("Pacheco");
        p2.setAge(23);

        new PersonDAO().save(p2);

        System.out.println(p2.toString());

        Person p3 = new Person();
        p3.setFirstName("Antonio");
        p3.setLastName("Dias");
        p3.setAge(50);

        new PersonDAO().save(p3);

        System.out.println(p3.toString());

    }
}
