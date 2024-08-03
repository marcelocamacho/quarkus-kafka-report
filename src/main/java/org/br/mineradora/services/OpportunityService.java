package org.br.mineradora.services;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.br.mineradora.dto.OpportunityDTO;
import org.br.mineradora.dto.ProposalDTO;
import org.br.mineradora.dto.QuotationDTO;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface OpportunityService {
    void buildOpportunity(ProposalDTO proposal);

    void saveQuotation(QuotationDTO quotation);

    List<OpportunityDTO> generateOpportunityData();

    ByteArrayInputStream generateCSVOpportunityReport();
}
