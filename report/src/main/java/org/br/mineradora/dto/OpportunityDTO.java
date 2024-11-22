package org.br.mineradora.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import org.br.mineradora.entity.OpportunityEntity;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
@Jacksonized
@AllArgsConstructor
public class OpportunityDTO {

    private Long id;
    private Date date;
    private Long proposalId;
    private String customer;
    private BigDecimal priceTonne;
    private BigDecimal lastDollarQuotation;

    public static OpportunityDTO from(OpportunityEntity opportunity) {
        return OpportunityDTO
                .builder()
                .id(opportunity.getId())
                .date(opportunity.getDate())
                .proposalId(opportunity.getProposalId())
                .customer(opportunity.getCustomer())
                .priceTonne(opportunity.getPriceTonne())
                .lastDollarQuotation(opportunity.getLastDollarQuotation())
                .build();
    }
}
