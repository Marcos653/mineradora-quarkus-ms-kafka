package org.br.mineradora.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.br.mineradora.dto.OpportunityDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@UtilityClass
public class CSVHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSVHelper.class);
    private static final String[] CSV_HEADERS = {"ID Proposta", "Cliente", "Preço por Tonelada", "Melhor Cotação de Moeda"};

    public static ByteArrayInputStream generateCSVFromOpportunities(List<OpportunityDTO> opportunities) {
        if (opportunities == null || opportunities.isEmpty()) {
            throw new IllegalArgumentException("A lista de oportunidades está vazia ou nula.");
        }

        CSVFormat csvFormat = CSVFormat.DEFAULT.builder().setHeader(CSV_HEADERS).build();

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
             CSVPrinter csvPrinter = new CSVPrinter(writer, csvFormat)) {

            for (OpportunityDTO opportunity : opportunities) {
                List<String> data = List.of(
                        String.valueOf(opportunity.getProposalId()),
                        opportunity.getCustomer() != null ? opportunity.getCustomer() : "",
                        String.format("%.2f", opportunity.getPriceTonne()),
                        String.format("%.2f", opportunity.getLastDollarQuotation())
                );
                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(outputStream.toByteArray());

        } catch (IOException e) {
            LOGGER.error("Erro ao exportar dados para o arquivo CSV", e);
            throw new RuntimeException("Falha ao exportar dados para o arquivo CSV: " + e.getMessage(), e);
        }
    }
}
