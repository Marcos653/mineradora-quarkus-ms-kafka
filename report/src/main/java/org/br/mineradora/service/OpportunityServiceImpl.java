package org.br.mineradora.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.br.mineradora.dto.OpportunityDTO;
import org.br.mineradora.dto.ProposalDTO;
import org.br.mineradora.dto.QuotationDTO;
import org.br.mineradora.entity.OpportunityEntity;
import org.br.mineradora.entity.QuotationEntity;
import org.br.mineradora.repository.OpportunityRepository;
import org.br.mineradora.repository.QuotationRepository;
import org.eclipse.microprofile.opentracing.Traced;

import java.util.List;

@Traced
@ApplicationScoped
@RequiredArgsConstructor
public class OpportunityServiceImpl implements OpportunityService {

    private final QuotationRepository quotationRepository;
    private final OpportunityRepository opportunityRepository;

    @Override
    @Transactional
    public void buildOpportunity(ProposalDTO proposalDTO) {
        QuotationEntity quotationEntity = quotationRepository.findLastQuotation();
        OpportunityEntity opportunityEntity = OpportunityEntity.from(proposalDTO, quotationEntity);

        opportunityRepository.persist(opportunityEntity);
    }

    @Override
    @Transactional
    public void saveQuotation(QuotationDTO quotationDTO) {
        quotationRepository.persist(QuotationEntity.from(quotationDTO));
    }

    @Override
    public List<OpportunityDTO> generateOpportunityData() {
        return opportunityRepository.findAll()
                .stream()
                .map(OpportunityDTO::from)
                .toList();
    }
}
