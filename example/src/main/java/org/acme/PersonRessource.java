package org.acme;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;

@Path("/api/persons")
public class PersonRessource {
    @Inject
    PersonRepository personRepository;

    @GET 
    public long count(){
        
        return personRepository.count();
    }

    @GET
    @Path("hello")
    public String hello() {
        return "Hello, World!";
    }

    @POST
    @Transactional
    public Response create(PersonCreateDTO personDTO) {
        {
            
            Person person = new Person();
            person.setName(personDTO.getName());
            person.setBirth((personDTO.getBirth()));
            
            personRepository.persist(person);
            return Response.status(201).entity(person).build();

        }
    }
}
