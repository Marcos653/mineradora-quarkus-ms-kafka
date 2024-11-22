package org.br.mineradora.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.br.mineradora.dto.ProposalDTO;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "opportunity")
public class OpportunityEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Date date;

    @Column(name = "proposal_id")
    private Long proposalId;

    private String customer;

    @Column(name = "price_tonne")
    private BigDecimal priceTonne;

    @Column(name = "last_currency_quotation")
    private BigDecimal lastDollarQuotation;

    public static OpportunityEntity from(ProposalDTO proposalDTO, QuotationEntity quotationEntity) {
        return OpportunityEntity
               .builder()
               .date(new Date())
               .proposalId(proposalDTO.getProposalId())
               .customer(proposalDTO.getCustomer())
               .priceTonne(proposalDTO.getPriceTonne())
               .lastDollarQuotation(quotationEntity.getCurrencyPrice())
               .build();
    }
}
