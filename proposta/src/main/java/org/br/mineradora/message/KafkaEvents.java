package org.br.mineradora.message;

import jakarta.enterprise.context.ApplicationScoped;
import org.br.mineradora.dto.ProposalDTO;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class KafkaEvents {

    private final Logger LOG = LoggerFactory.getLogger(KafkaEvents.class);

    @Channel("proposal-channel")
    private Emitter<ProposalDTO> proposalChannel;

    public void sendProposal(ProposalDTO dto) {
        LOG.info("-- Enviando Proposta para Tópico Kafka --");
        proposalChannel.send(dto).toCompletableFuture().join();
    }
}
