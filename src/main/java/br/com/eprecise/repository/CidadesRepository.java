package br.com.eprecise.repository;
import br.com.eprecise.model.Cidade;

import br.com.eprecise.model.Estados;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;

import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

public class CidadesRepository implements PanacheRepository<Cidade> {

    public List<Cidade> listAll() {
        return listAll();
    }

    public List<Cidade> listCidadesSortNomeAndEstado() {
        return list("order by nome, estado");
    }

    public List<Cidade> listCidadesByPage(int page, int size) {
        PanacheQuery<Cidade> listCidades = find("nome", true);
        return listCidades.page(Page.of(page, size)).list();
    }

    public List<Cidade> findByName(String nome, int page, int size){
        PanacheQuery<Cidade> listCidades = find("nome", nome);
        return listCidades.page(Page.of(page, size)).list();
    }

    public Cidade findByStates(String estado, int page, int size){
        PanacheQuery<Cidade> listCidades = find("estado", estado);
        return listCidades.page(Page.of(page, size)).list();
    }

    public long countCidades() {
        return count();
    }

    @Transactional
    public Cidade save(Cidade cidade) {
        persist(cidade);
        return cidade;
    }

    @Transactional
    public Cidade update(Long id, Cidade cidade) {
        Cidade cidadeEntity = findById(id);

        if (cidadeEntity == null) {
            throw new WebApplicationException("Cidade com id  " + id + " não existe.", Response.Status.NOT_FOUND);
        }

        cidadeEntity.setNome(cidade.getNome());
        cidadeEntity.setEstado(cidade.getEstado());
        return cidadeEntity;
    }

    @Transactional
    public void remove(Long id) {
        Cidade cidadeEntity = findById(id);

        if (cidadeEntity == null) {
            throw new WebApplicationException("Cidade com id  " + id + " não existe.", Response.Status.NOT_FOUND);
        }
        delete(cidadeEntity);
    }

}
