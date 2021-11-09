package edu.upc.dsa.services;

import edu.upc.dsa.Manager;
import edu.upc.dsa.models.PuntInteres;
import edu.upc.dsa.models.Usuari;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/joc", description = "Endpoint to Text Service")
@Path("/usuaris")

public class JocService {
    private Manager manager;

    public JocService(){}

    @GET
    @ApiOperation(value = "llista usuaris", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuari.class, responseContainer="List"),
    })
    @Path("/llistaUsuaris")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLlistaUsuaris() {
        List<Usuari> u = this.manager.llistaUsuaris();
        GenericEntity<List<Usuari>> entity = new GenericEntity<List<Usuari>>(u) {};
        return Response.status(201).entity(entity).build();
    }


    @POST
    @ApiOperation(value = "afegir Usuari", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=Usuari.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/afegirUsuari")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUsuari(Usuari u) {
        if (u.getIdUsuari() == null)
            return Response.status(500).entity(u).build();
        else {
            this.manager.addUsuari(u.getIdUsuari());
            return Response.status(201).entity(u).build();
        }
    }

    @GET
    @ApiOperation(value = "consultar Usuari", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuari.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/consultarUsuari/{idUsuari}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarUsuari(@PathParam("idUsuari") String idUsuari) {
        Usuari u = this.manager.getUsuariPerId(idUsuari);
        if (u == null)
            return Response.status(404).build();
        else
            return Response.status(201).entity(u).build();
    }

    @GET
    @ApiOperation(value = "llista Usuaris per un PI", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuari.class, responseContainer="List"),
    })
    @Path("/llistaUsuarisPerUnPI")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLlistaUsuarisPerUnPI(@PathParam("idPI") String idPI) {
        List<Usuari> l = manager.usuarisPerPI(idPI);
        GenericEntity<List<Usuari>> entity = new GenericEntity<List<Usuari>>(l) {};
        return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "llista PIs per un Usuari", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = PuntInteres.class, responseContainer="List"),
    })
    @Path("/llistaPIsPerUnUsuari")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLlistaPIsPerUsuari(@PathParam("idUsuari") String idUsuari) {
        List<PuntInteres> l = manager.consultarPIPerOnPassaUsuari(idUsuari);
        GenericEntity<List<PuntInteres>> entity = new GenericEntity<List<PuntInteres>>(l) {};
        return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "informar a Usuari del PI per on passa", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuari.class),
    })
    @Path("/informarUsuariPI")
    @Produces(MediaType.APPLICATION_JSON)
    public Response informarUsuariPI(@PathParam("idUsuari") String idUsuari) {
        List<PuntInteres> l = manager.consultarPIPerOnPassaUsuari(idUsuari);
        PuntInteres p = l.get(l.size());

        GenericEntity<PuntInteres> entity = new GenericEntity<PuntInteres>(p) {};
        return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "llista Usuaris segons PI creuats", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Usuari.class, responseContainer="List" ),
    })
    @Path("/llistaUsuarisSegonsPIsCreuats")
    @Produces(MediaType.APPLICATION_JSON)
    public Response llistaUsuarisPIsCreuats() {
        List<Usuari> l = this.manager.usuarisSegonsPI();
        GenericEntity<List<Usuari>> entity = new GenericEntity<List<Usuari>>(l) {};
        return Response.status(201).entity(entity).build();
        }

}
