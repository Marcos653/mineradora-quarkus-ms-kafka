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
import org.br.mineradora.dto.QuotationDTO;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "quotation")
public class QuotationEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Date date;

    @Column(name = "currency_price")
    private BigDecimal currencyPrice;

    public static QuotationEntity from(QuotationDTO quotationDTO) {
        return QuotationEntity
                .builder()
                .currencyPrice(quotationDTO.getCurrencyPrice())
                .date(quotationDTO.getDate())
                .build();
    }
}
