package org.br.mineradora.controller;

import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.br.mineradora.dto.ProposalDetailsDTO;
import org.br.mineradora.service.ProposalService;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Authenticated
@Path("/api/proposals")
@RequiredArgsConstructor
public class ProposalController {

    private final Logger LOG = LoggerFactory.getLogger(ProposalController.class);

    private final JsonWebToken jwtToken;
    private final ProposalService proposalService;

    @GET
    @Path("/{id}")
    @RolesAllowed({"user", "manager"})
    public ProposalDetailsDTO findDetailsProposal(@PathParam("id") long id) {
        return proposalService.findFullProposal(id);
    }

    @POST
    @RolesAllowed("proposal-customer")
    public Response createProposal(ProposalDetailsDTO proposalDetailsDTO) {
        LOG.info("--- Recebendo Proposta de Compra ---");

        proposalService.createNewProposal(proposalDetailsDTO);

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("manager")
    public Response removeProposal(@PathParam("id") long id) {
        LOG.info("--- Removendo Proposta de Compra com ID: {} ---", id);

        proposalService.removeProposal(id);

        return Response.ok().build();
    }
}
