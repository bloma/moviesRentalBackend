package ac.za.cput.services.Impl;

import ac.za.cput.domain.Rental;
import ac.za.cput.repository.RentalRepository;
import ac.za.cput.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Aphish on 2016/08/20.
 */

@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    RentalRepository rentalRepository;

    @Override
    public Rental create(Rental entity) {
        return rentalRepository.save(entity);
    }

    @Override
    public Rental readById(Long aLong) {
        return rentalRepository.findOne(aLong);
    }

    @Override
    public Set<Rental> readAll() {
        Set<Rental> rentals = new HashSet<>();

        while (rentalRepository.findAll().iterator().hasNext()){
            rentals.add(rentalRepository.findAll().iterator().next());
        }

        return rentals;
    }

    @Override
    public Rental update(Rental entity) {
        return rentalRepository.save(entity);
    }

    @Override
    public void delete(Rental entity) {
        rentalRepository.delete(entity);
    }
}
