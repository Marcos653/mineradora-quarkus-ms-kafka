package org.br.mineradora.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.br.mineradora.dto.ProposalDTO;
import org.br.mineradora.dto.ProposalDetailsDTO;
import org.br.mineradora.entity.ProposalEntity;
import org.br.mineradora.message.KafkaEvents;
import org.br.mineradora.repository.ProposalRepository;
import org.eclipse.microprofile.opentracing.Traced;

@Traced
@ApplicationScoped
@RequiredArgsConstructor
public class ProposalServiceImpl implements ProposalService {

    private final KafkaEvents kafkaEvents;
    private final ProposalRepository proposalRepository;

    @Override
    public ProposalDetailsDTO findFullProposal(long id) {
        ProposalEntity proposal = proposalRepository.findById(id);
        return ProposalDetailsDTO.from(proposal);
    }

    @Override
    @Transactional
    public void createNewProposal(ProposalDetailsDTO proposalDetailsDTO) {
        ProposalEntity proposal = ProposalEntity.from(proposalDetailsDTO);

        proposalRepository.persist(proposal);
        kafkaEvents.sendProposal(ProposalDTO.from(proposal));
    }

    @Override
    @Transactional
    public void removeProposal(long id) {
        proposalRepository.deleteById(id);
    }
}
