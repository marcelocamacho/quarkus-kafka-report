package org.br.mineradora.controller;

import java.util.Date;
import java.util.List;

import org.br.mineradora.dto.OpportunityDTO;
import org.br.mineradora.services.OpportunityService;
import org.eclipse.microprofile.jwt.JsonWebToken;

import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.ServerErrorException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/opportunity")
@Authenticated
public class OpportunityController {

    @Inject
    OpportunityService opportunityService;

    @Inject
    JsonWebToken jsonWebToken;

    @GET
    @Path("/data")
    @RolesAllowed({"user","manager"})
    public List<OpportunityDTO> generateReport(){

        return opportunityService.generateOpportunityData();

        /*try{
            return Response.ok(
                opportunityService.generateCSVOpportunityReport(),
                MediaType.APPLICATION_OCTET_STREAM)
              .header("content-disposition",
              "attachment; filename = "+ new Date() +" --oportunidades-venda.csv")
              .build();
        } catch( ServerErrorException errorException){
            return Response.serverError().build();
        }*/
    }
    
}
