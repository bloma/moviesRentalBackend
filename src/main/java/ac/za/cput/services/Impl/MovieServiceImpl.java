package ac.za.cput.services.Impl;

import ac.za.cput.domain.Movie;
import ac.za.cput.repository.MovieRepository;
import ac.za.cput.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Aphish on 2016/08/20.
 */

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Override
    public Movie create(Movie entity) {
        return movieRepository.save(entity);
    }

    @Override
    public Movie readById(Long aLong) {
        return movieRepository.findOne(aLong);
    }

    @Override
    public Set<Movie> readAll() {
        Set<Movie> movies = new HashSet<>();

        while (movieRepository.findAll().iterator().hasNext()){
            movies.add(movieRepository.findAll().iterator().next());
        }

        return movies;
    }

    @Override
    public Movie update(Movie entity) {
        return movieRepository.save(entity);
    }

    @Override
    public void delete(Movie entity) {
        movieRepository.delete(entity);
    }
}
