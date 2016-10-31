package ac.za.cput.services.Impl;

import ac.za.cput.domain.Cash;
import ac.za.cput.repository.CashRepository;
import ac.za.cput.services.CashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Aphish on 2016/08/20.
 */

@Service
public class CashServiceImpl implements CashService {

    @Autowired
    CashRepository cashRepository;

    @Override
    public Cash create(Cash entity) {
        return cashRepository.save(entity);
    }

    @Override
    public Cash readById(Long aLong) {
        return cashRepository.findOne(aLong);
    }

    @Override
    public Set<Cash> readAll() {
        Set<Cash> cash = new HashSet<>();

        while (cashRepository.findAll().iterator().hasNext()){
            cash.add(cashRepository.findAll().iterator().next());
        }

        return cash;
    }

    @Override
    public Cash update(Cash entity) {
        return cashRepository.save(entity);
    }

    @Override
    public void delete(Cash entity) {
        cashRepository.delete(entity);
    }
}
