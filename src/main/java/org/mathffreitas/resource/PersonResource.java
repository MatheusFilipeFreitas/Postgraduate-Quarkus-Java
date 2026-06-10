package org.mathffreitas.resource;

import io.micrometer.core.annotation.Counted;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.mathffreitas.model.Person;

import java.util.List;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {
    @GET
    @Counted(value = "counted.getPerson")
    public List<Person> getPerson() {
        return Person.listAll();
    }

    @POST
    @Transactional
    public Person createPerson(Person person) {
        person.id = null;
        person.persist();

        return person;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Person updatePerson(@PathParam("id") int id, Person person) throws Exception {
        Person existingPerson = Person.findById(id);
        if (existingPerson == null) {
            throw new Exception("Not Found");
        }
        existingPerson.name = person.name;
        existingPerson.birthYear = person.birthYear;

        existingPerson.persist();
        return existingPerson;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deletePerson(@PathParam("id") int id) throws Exception {
        Person person = Person.findById(id);
        if (person == null) {
            throw new Exception("Not Found");
        }
        Person.deleteById(id);
    }

    @GET
    @Path("/birthYear/{year}")
    public List<Person> getYear(@PathParam("year")int year) {
        return Person.findPeerBirthYear(year);
    }
}
