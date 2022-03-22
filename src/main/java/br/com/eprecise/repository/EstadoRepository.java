package br.com.eprecise.repository;

import br.com.eprecise.model.Estados;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class EstadoRepository implements PanacheRepository<Estados> {

    public List<Estados> listAll() {
        return listAll();
    }

    public List<Estados> listByPage(int page, int size) {
        PanacheQuery<Estados> listEstados = find("nome", true);
        return listEstados.page(Page.of(page, size)).list();
    }

    public Estados save(Estados estado) {
         persist(estado);
         return  estado;
    }

    public long countEstados() {
        return count();
    }

    @Transactional
    public Estados update(Long id, Estados estados) {
        Estados estadoEntity = findById(id);

        if (estadoEntity == null) {
            throw new WebApplicationException("Estado com id  " + id + " não existe.", Response.Status.NOT_FOUND);
        }

        estadoEntity.setNome(estados.getNome());
        estadoEntity.setSigla(estados.getSigla());
        return estadoEntity;
    }

    @Transactional
    public void remove(Long id) {
        Estados estadoEntity = findById(id);

        if (estadoEntity == null) {
            throw new WebApplicationException("Estado com id  " + id + " não existe.", Response.Status.NOT_FOUND);
        }
        delete(estadoEntity);
    }
}
