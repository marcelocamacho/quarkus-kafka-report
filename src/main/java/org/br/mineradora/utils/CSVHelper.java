package org.br.mineradora.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.br.mineradora.dto.OpportunityDTO;

public class CSVHelper {
    public static ByteArrayInputStream OpportunitiesToCSV(List<OpportunityDTO> opportunities){


        @SuppressWarnings("deprecation")
        final CSVFormat format = CSVFormat.DEFAULT
        .withHeader("ID Proposta", "Cliente", "Preço por Tonelada", "Melhor cotação da moeda");

        try(
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)){

                for (OpportunityDTO opps: opportunities){
                    List<String> data = Arrays.asList(String.valueOf(opps.getProposalId()),
                        opps.getCustomer(), String.valueOf(opps.getPriceTonne()),
                        String.valueOf(opps.getLastDollarQuotation()));
                        csvPrinter.printRecord(data);
                }
                csvPrinter.flush();
                return new ByteArrayInputStream(out.toByteArray());
                
            } catch( IOException e){
                throw new RuntimeException("Fail to import data to CSV file: "+ e.getMessage());
            }
        

    }
}
