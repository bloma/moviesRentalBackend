package ac.za.cput.repository;

import ac.za.cput.domain.Actors;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Aphish on 2016/08/16.
 */

@Repository
public interface ActorsRepository extends CrudRepository<Actors,Long> {
}
