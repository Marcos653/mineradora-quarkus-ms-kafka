package org.br.mineradora.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.br.mineradora.client.CurrencyPriceClient;
import org.br.mineradora.dto.CurrencyPriceDTO;
import org.br.mineradora.dto.QuotationDTO;
import org.br.mineradora.entity.QuotationEntity;
import org.br.mineradora.message.KafkaEvents;
import org.br.mineradora.repository.QuotationRepository;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class QuotationService {

    private final KafkaEvents kafkaEvents;
    private final QuotationRepository quotationRepository;

    @Inject
    @RestClient
    private CurrencyPriceClient currencyPriceClient;

    public void getCurrencyPrice() {
        CurrencyPriceDTO currencyPriceInfo = currencyPriceClient.getPriceByPair("USD-BRL");
        sendQuotationToKafka(currencyPriceInfo);
    }

    public void deleteAllQuotation() {
        quotationRepository.deleteAll();
    }

    private void sendQuotationToKafka(CurrencyPriceDTO currencyPriceInfo) {
        if (updateCurrentInfoPrice(currencyPriceInfo)) {
            kafkaEvents.sendQuotation(createQuotationDTO(currencyPriceInfo));
        }
    }

    private boolean updateCurrentInfoPrice(CurrencyPriceDTO currencyPriceInfo) {
        BigDecimal currentPrice = new BigDecimal(currencyPriceInfo.getUsdBrl().getBid());
        QuotationEntity quotationEntity = quotationRepository.findLastQuotation();

        return quotationEntity == null ? saveFirstQuotation(currencyPriceInfo)
                : updateQuotation(quotationEntity, currencyPriceInfo, currentPrice);
    }

    private boolean updateQuotation(QuotationEntity quotation,
                                    CurrencyPriceDTO currencyPriceInfo, BigDecimal currentPrice) {
        if (currentPrice.compareTo(quotation.getCurrencyPrice()) != 0) {
            saveQuotation(currencyPriceInfo);
            return true;
        }

        return false;
    }

    private boolean saveFirstQuotation(CurrencyPriceDTO currencyPriceInfo) {
        saveQuotation(currencyPriceInfo);
        return true;
    }

    private void saveQuotation(CurrencyPriceDTO currencyPriceInfo) {
        quotationRepository.persist(createQuotation(currencyPriceInfo));
    }

    private QuotationEntity createQuotation(CurrencyPriceDTO currencyPriceInfo) {
        return QuotationEntity
                .builder()
                .currencyPrice(new BigDecimal(currencyPriceInfo.getUsdBrl().getBid()))
                .date(new Date())
                .pctChange(currencyPriceInfo.getUsdBrl().getPctChange())
                .pair("USD-BRL")
                .build();
    }

    private QuotationDTO createQuotationDTO(CurrencyPriceDTO currencyPriceInfo) {
        return QuotationDTO
                .builder()
                .currencyPrice(new BigDecimal(currencyPriceInfo.getUsdBrl().getBid()))
                .date(new Date())
                .build();
    }
}
