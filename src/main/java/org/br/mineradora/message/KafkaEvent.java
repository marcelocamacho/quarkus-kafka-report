package org.br.mineradora.message;

import org.br.mineradora.dto.ProposalDTO;
import org.br.mineradora.dto.QuotationDTO;
import org.br.mineradora.services.OpportunityService;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.smallrye.common.annotation.Blocking;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

public class KafkaEvent {

    private final Logger LOG = LoggerFactory.getLogger(getClass());
    
    @Inject
    OpportunityService opportunityService;
    
    @Incoming("quotation")
    @Blocking
    public void receiveQuotation(QuotationDTO quotation){
        LOG.info("-- Recebendo Nova Cotação do Tópico kafka --");
        LOG.info(quotation.toString());

        opportunityService.saveQuotation(quotation);
    }

    @Incoming("proposal")
    @Transactional
    public void receiveProposal(ProposalDTO proposal){
        LOG.info("-- Recebendo Nova Proposta do Tópico kafka --");
        LOG.info(proposal.toString());
        opportunityService.buildOpportunity(proposal);
    }





}
