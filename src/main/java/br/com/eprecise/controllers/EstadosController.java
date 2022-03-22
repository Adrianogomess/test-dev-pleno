package br.com.eprecise.controllers;

import br.com.eprecise.model.Estados;
import br.com.eprecise.repository.EstadoRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/estados")
@Produces(MediaType.APPLICATION_JSON)
public class EstadosController {

    @Inject
    private EstadoRepository estadoRepository;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estados> listAll() {
        return estadoRepository.listAll();
    }

    @GET
    @Path("/listByPage")
    public List<Estados> listCarByPage(@QueryParam("page") int page, @QueryParam("size") int size) {
        return estadoRepository.listByPage(page, size);
    }

    @GET
    @Path("/total")
    @Produces(MediaType.APPLICATION_JSON)
    public Long count() {
        return estadoRepository.countEstados();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Estados estados) {
        Estados estadoEntity = estadoRepository.save(estados);
        return Response.ok(estados).status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Estados estados) {
        Estados estadoUpdated = estadoRepository.update(id, estados);
        return Response.ok(estadoUpdated).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
        estadoRepository.remove(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }


}
