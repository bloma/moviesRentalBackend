package ac.za.cput.services.Impl;

import ac.za.cput.domain.Actors;
import ac.za.cput.repository.ActorsRepository;
import ac.za.cput.services.ActorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Aphish on 2016/08/16.
 */

@Service
public class ActorsServiceImpl implements ActorsService{

    @Autowired
    ActorsRepository repository;

    @Override
    public Actors create(Actors entity) {
        return repository.save(entity);
    }

    @Override
    public Actors readById(Long aLong) {
        return repository.findOne(aLong);
    }

    @Override
    public Set<Actors> readAll() {
        Set<Actors> actors = new HashSet<>();

        while (repository.findAll().iterator().hasNext()){
            actors.add(repository.findAll().iterator().next());
        }

        return actors;
    }

    @Override
    public Actors update(Actors entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Actors entity) {
        repository.delete(entity);
    }
}
