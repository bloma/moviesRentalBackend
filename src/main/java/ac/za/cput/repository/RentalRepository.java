package ac.za.cput.repository;

import ac.za.cput.domain.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Aphish on 2016/08/20.
 */
@Repository
public interface RentalRepository extends CrudRepository<Rental,Long> {
}
