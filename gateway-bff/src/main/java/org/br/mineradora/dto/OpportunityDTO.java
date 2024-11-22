package org.br.mineradora.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@Jacksonized
@AllArgsConstructor
public class OpportunityDTO {

    private Long proposalId;
    private String customer;
    private BigDecimal priceTonne;
    private BigDecimal lastDollarQuotation;
}
