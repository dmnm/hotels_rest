package org.example.rest.dao;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.example.rest.entity.Person;

@Named
public class PersonDao extends GenericDao<Person> {
    public Person findByName(final String name) {
        return repo.values().stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
    }

    @PostConstruct
    void init() {
        Person p;

        save(p = new Person());
        p.setName("admin");
        p.setPhone("322223");

        save(p = new Person());
        p.setName("qa");
        p.setPhone("+1234567890");
    }
}
