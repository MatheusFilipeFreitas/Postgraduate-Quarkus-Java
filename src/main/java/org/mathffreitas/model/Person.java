package org.mathffreitas.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Person extends PanacheEntity {
    public String name;
    public int birthYear;

    public static List<Person> findPeerBirthYear(int year) {
        return find("birthYear", year).list();
    }
}
