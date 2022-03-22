package br.com.eprecise.controllers;

import br.com.eprecise.model.Cidade;
import br.com.eprecise.model.Estados;
import br.com.eprecise.repository.CidadesRepository;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/cidades")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Cidades", description = "Crud cidades")
public class CidadesController {

    @Inject
    private CidadesRepository cidadesRepository;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cidade> listAll() {
        return cidadesRepository.listAll();
    }

    @GET
    @Path("/listByPage")
    public List<Cidade> listByPage(@QueryParam("page") int page, @QueryParam("size") int size) {
        return cidadesRepository.listCidadesByPage(page, size);
    }

    @GET
    @Path("/listSortNameAndState")
    public List<Cidade> listSortNameAndState() {
        return cidadesRepository.listCidadesSortNomeAndEstado();
    }

    @GET
    @Path("/findByState")
    public Cidade findByState(
        @QueryParam("estado") String estado,
        @QueryParam("page") int page,
        @QueryParam("size") int size
    ) {
        return cidadesRepository.findByStates(estado, page, size);
    }

    @GET
    @Path("/findByName")
    public List<Cidade> findByName(
        @QueryParam("nome") String nome,
        @QueryParam("page") int page,
        @QueryParam("size") int size
    ) {
        return cidadesRepository.findByName(nome, page, size);
    }

    @GET
    @Path("/total")
    @Produces(MediaType.APPLICATION_JSON)
    public Long count() {
        return cidadesRepository.countCidades();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Cidade cidade) {
        Cidade cidadeEntity = cidadesRepository.save(cidade);
        return Response.ok(cidade).status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Cidade cidade) {
        Cidade cidadeUpdated = cidadesRepository.update(id, cidade);
        return Response.ok(cidadeUpdated).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long id) {
        cidadesRepository.remove(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }


}
