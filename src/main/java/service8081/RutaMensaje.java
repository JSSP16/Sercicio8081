package service8081;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

@ApplicationScoped
@RegisterForReflection
public class RutaMensaje extends RouteBuilder {

    @Override
    public void configure() {
        restConfiguration()
                .bindingMode(RestBindingMode.auto);

        rest("/api")
                .post("/mensaje")
                .consumes("application/json")
                .produces("application/json")
                .to("direct:mensaje");

        from("direct:mensaje")
                .log("Mensaje recibido en el servicio 8081: ${body}")
                .setBody(simple("Mensaje recibido: ${body}"));
    }
}
