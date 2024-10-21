import jakarta.enterprise.context.ApplicationScoped;
import org.acme.PersonCamel;
import org.acme.PersonCreateDTO;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

@ApplicationScoped
public class ExampleRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        rest("api")
            .get("/hello")
            .to("direct:hello")
                
            .get("/person")
            .to("direct:GetAllperson")
                
            .post("/person")
            .type(PersonCreateDTO.class)
            .consumes("application/json")
            .produces("application/json")
            .to("direct:CreatePerson");
        
        
        from("direct:hello")
            .transform().constant("Hello World");

        from("direct:GetAllperson")
                .to("jpa:org.acme.PersonCamel?query=select o from PersonCamel o")
                .marshal().json(PersonCamel.class);  // Konvertiert die Liste in JSON;
        
        from("direct:CreatePerson")
                .unmarshal().json(JsonLibrary.Jackson, PersonCamel.class) // Unmarshalling JSON into PersonCamel
                .to("jpa:org.acme.PersonCamel")
                .marshal().json(JsonLibrary.Jackson, PersonCamel.class); // Marshalling PersonCamel into JSON
        
    }
}
