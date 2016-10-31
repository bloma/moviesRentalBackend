package ac.za.cput.services.Impl;

import ac.za.cput.domain.Customers;
import ac.za.cput.repository.CustomersRepository;
import ac.za.cput.services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Aphish on 2016/08/20.
 */

@Service
public class CustomersServiceImpl implements CustomersService {

    @Autowired
    CustomersRepository repository;

    @Override
    public Customers create(Customers entity) {
        return repository.save(entity);
    }

    @Override
    public Customers readById(Long aLong) {
        return repository.findOne(aLong);
    }

    @Override
    public Set<Customers> readAll() {
        Set<Customers> customers = new HashSet<>();

        while (repository.findAll().iterator().hasNext()){
            customers.add(repository.findAll().iterator().next());
        }

        return customers;
    }

    @Override
    public Customers update(Customers entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Customers entity) {
            repository.delete(entity);
    }
}
