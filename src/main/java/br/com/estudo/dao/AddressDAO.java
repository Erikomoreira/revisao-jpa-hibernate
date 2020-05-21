package br.com.estudo.dao;

import br.com.estudo.entity.Address;

import java.util.List;

public class AddressDAO extends GenericDAO<Address> {

    public AddressDAO() {
        super(Address.class);
    }

    public List<Address> findByCity(String city){

        String jpql = "from Address a where a.city like ?1 ";
        return find(jpql, city);

    }

}
