package org.br.mineradora.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.br.mineradora.dto.ProposalDetailsDTO;
import org.br.mineradora.service.ProposalService;

@ApplicationScoped
@Path("/api/trade")
@RequiredArgsConstructor
public class ProposalController {

    private final ProposalService proposalService;

    @GET
    @Path("/{id}")
    @RolesAllowed({"user", "manager"})
    public Response getProposalDetailsById(@PathParam("id") long id) {
        return Response.ok(proposalService.getProposalDetailsById(id), MediaType.APPLICATION_JSON)
                .build();
    }

    @POST
    @RolesAllowed("proposal-customer")
    public Response createProposal(ProposalDetailsDTO proposalDetails) {
        return Response.ok(proposalService.createProposal(proposalDetails))
                .build();
    }

    @DELETE
    @Path("/remove/{id}")
    @RolesAllowed("manager")
    public Response removeProposal(@PathParam("id") long id) {
        return Response.ok(proposalService.removeProposal(id))
                .build();
    }
}
