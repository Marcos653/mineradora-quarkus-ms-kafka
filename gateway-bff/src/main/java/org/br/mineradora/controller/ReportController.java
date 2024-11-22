package org.br.mineradora.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.br.mineradora.service.ReportService;

import java.io.ByteArrayInputStream;
import java.util.Date;

import static jakarta.ws.rs.core.MediaType.APPLICATION_OCTET_STREAM;

@ApplicationScoped
@Path("/api/opportunities")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GET
    @Path("/report")
    @RolesAllowed({"user", "manager"})
    @Produces(APPLICATION_OCTET_STREAM)
    public Response generateReport() {
        ByteArrayInputStream csvReport = reportService.generateCSVOpportunityReport();
        String fileName = new Date() + "--oportunidades-venda.csv";

        return buildDownloadResponse(csvReport, fileName);
    }

    @GET
    @Path("/data")
    @RolesAllowed({"user", "manager"})
    public Response generateOpportunitiesData() {
        return Response.ok(reportService.getOpportunitiesData())
                .build();
    }

    private Response buildDownloadResponse(ByteArrayInputStream fileContent, String fileName) {
        return Response
                .ok(fileContent, APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
                .build();
    }
}
