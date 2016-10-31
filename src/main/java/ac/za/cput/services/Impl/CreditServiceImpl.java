package ac.za.cput.services.Impl;


import ac.za.cput.domain.Credit;
import ac.za.cput.repository.CreditRepository;
import ac.za.cput.services.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Aphish on 2016/08/20.
 */

@Service
public class CreditServiceImpl implements CreditService {

    @Autowired
    CreditRepository creditRepository;
    @Override
    public Credit create(Credit entity) {
        return creditRepository.save(entity);
    }

    @Override
    public Credit readById(Long aLong) {
        return creditRepository.findOne(aLong);
    }

    @Override
    public Set<Credit> readAll() {
        Set<Credit> credits = new HashSet<>();

        while (creditRepository.findAll().iterator().hasNext()){
            credits.add(creditRepository.findAll().iterator().next());
        }

        return credits;
    }

    @Override
    public Credit update(Credit entity) {
        return creditRepository.save(entity);
    }

    @Override
    public void delete(Credit entity) {
        creditRepository.delete(entity);
    }
}
