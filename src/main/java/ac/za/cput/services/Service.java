package ac.za.cput.services;

import java.util.Set;

/**
 * Created by Aphish on 2016/08/16.
 */
public interface Service<E, ID> {

    E create(E entity);

    E readById(ID id);

    Set<E> readAll();

    E update(E entity);

    void delete(E entity);
}
