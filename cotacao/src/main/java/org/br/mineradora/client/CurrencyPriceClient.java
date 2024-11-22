package org.br.mineradora.client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.br.mineradora.dto.CurrencyPriceDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@ApplicationScoped
@RegisterRestClient(baseUri = "https://economia.awesomeapi.com.br")
public interface CurrencyPriceClient {

    @GET
    @Path("/last/{pair}")
    CurrencyPriceDTO getPriceByPair(@PathParam("pair") String pair);
}
