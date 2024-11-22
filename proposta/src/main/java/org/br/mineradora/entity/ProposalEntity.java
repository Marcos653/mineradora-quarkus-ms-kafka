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
import org.br.mineradora.dto.ProposalDetailsDTO;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "proposal")
public class ProposalEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String customer;

    @Column(name = "price_tonne")
    private BigDecimal priceTonne;

    private Integer tonnes;

    private String country;

    @Column(name = "proposal_validity_days")
    private Integer proposalValidityDays;

    private Date created;

    public static ProposalEntity from(ProposalDetailsDTO proposalDetailsDTO) {
        return ProposalEntity
                .builder()
                .customer(proposalDetailsDTO.getCustomer())
                .priceTonne(proposalDetailsDTO.getPriceTonne())
                .tonnes(proposalDetailsDTO.getTonnes())
                .country(proposalDetailsDTO.getCountry())
                .proposalValidityDays(proposalDetailsDTO.getProposalValidityDays())
                .created(new Date())
                .build();
    }
}
