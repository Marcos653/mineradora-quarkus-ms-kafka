package org.br.mineradora.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.br.mineradora.client.ProposalRestClient;
import org.br.mineradora.dto.ProposalDetailsDTO;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Traced
@ApplicationScoped
public class ProposalServiceImpl implements ProposalService {

    @Inject
    @RestClient
    private ProposalRestClient proposalRestClient;

    @Override
    public ProposalDetailsDTO getProposalDetailsById(long proposalId) {
        return proposalRestClient.getProposalById(proposalId);
    }

    @Override
    public Response createProposal(ProposalDetailsDTO proposalDetails) {
        return proposalRestClient.createProposal(proposalDetails);
    }

    @Override
    public Response removeProposal(long id) {
        return proposalRestClient.removeProposal(id);
    }
}
