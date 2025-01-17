package org.br.mineradora.client;

import io.quarkus.oidc.token.propagation.reactive.AccessTokenRequestReactiveFilter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.br.mineradora.dto.ProposalDetailsDTO;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@ApplicationScoped
@Path("/api/proposals")
@RegisterRestClient(baseUri = "http://localhost:8091")
@RegisterProvider(AccessTokenRequestReactiveFilter.class)
public interface ProposalRestClient {

    @GET
    @Path("/{id}")
    ProposalDetailsDTO getProposalById(@PathParam("id") long id);

    @POST
    Response createProposal(ProposalDetailsDTO proposalDetailsDTO);

    @DELETE
    @Path("/{id}")
    Response removeProposal(@PathParam("id") long id);
}

