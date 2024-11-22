package org.br.mineradora.scheduler;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.br.mineradora.service.QuotationService;

@ApplicationScoped
@RequiredArgsConstructor
public class QuotationScheduler {

    private final QuotationService quotationService;

    @Transactional
    @Scheduled(every = "35s", identity = "task-job-get-currency")
    void scheduleGetCurrencyPrice() {
        quotationService.getCurrencyPrice();
    }

    @Transactional
    @Scheduled(cron = "59 59 23 * * ?", identity = "task-job-delete-quotation")
    void scheduleDeleteProposal() {
        quotationService.deleteAllQuotation();
    }
}
