package org.br.mineradora.message;

import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.br.mineradora.dto.ProposalDTO;
import org.br.mineradora.dto.QuotationDTO;
import org.br.mineradora.service.OpportunityService;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@RequiredArgsConstructor
public class KafkaEvents {

    private final Logger LOG = LoggerFactory.getLogger(KafkaEvents.class);

    private final OpportunityService opportunityService;

    @Transactional
    @Incoming("proposal")
    public void receiveProposal(ProposalDTO proposalDTO) {
        LOG.info("--- Recebendo Nova Proposta do Tópico Kafka ---");
        opportunityService.buildOpportunity(proposalDTO);
    }

    @Blocking
    @Incoming("quotation")
    public void receiveQuotation(QuotationDTO quotationDTO) {
        LOG.info("--- Recebendo Nova Cotação de Moeda do Tópico Kafka ---");
        opportunityService.saveQuotation(quotationDTO);
    }
}
