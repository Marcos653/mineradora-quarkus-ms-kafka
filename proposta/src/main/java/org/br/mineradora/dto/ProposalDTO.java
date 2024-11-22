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
public class ProposalDTO {

    private Long proposalId;
    private String customer;
    private BigDecimal priceTonne;

    public static ProposalDTO from(ProposalEntity proposal) {
        return ProposalDTO
                .builder()
                .proposalId(proposal.getId())
                .customer(proposal.getCustomer())
                .priceTonne(proposal.getPriceTonne())
                .build();
    }
}
