package org.br.mineradora.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import org.br.mineradora.entity.ProposalEntity;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@Jacksonized
@AllArgsConstructor
public class ProposalDetailsDTO {

    private Long proposalId;
    private String customer;
    private BigDecimal priceTonne;
    private Integer tonnes;
    private String country;
    private Integer proposalValidityDays;

    public static ProposalDetailsDTO from(ProposalEntity proposal) {
        return ProposalDetailsDTO
                .builder()
                .proposalId(proposal.getId())
                .customer(proposal.getCustomer())
                .priceTonne(proposal.getPriceTonne())
                .tonnes(proposal.getTonnes())
                .country(proposal.getCountry())
                .proposalValidityDays(proposal.getProposalValidityDays())
                .build();
    }
}
