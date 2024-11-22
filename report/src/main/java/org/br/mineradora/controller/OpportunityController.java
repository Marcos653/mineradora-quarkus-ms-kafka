package org.br.mineradora.controller;

import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.br.mineradora.dto.OpportunityDTO;
import org.br.mineradora.service.OpportunityService;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.List;

@Authenticated
@RequiredArgsConstructor
@Path("/api/opportunities")
public class OpportunityController {

    private final JsonWebToken jwtToken;
    private final OpportunityService opportunityService;

    @GET
    @Path("/data")
    @RolesAllowed({"user", "manager"})
    public List<OpportunityDTO> generateReport() {
        return opportunityService.generateOpportunityData();
    }
}
