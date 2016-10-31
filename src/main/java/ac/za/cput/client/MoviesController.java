package ac.za.cput.client;

import ac.za.cput.domain.Movie;
import ac.za.cput.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

/**
 * Created by Aphish on 2016/08/21.
 */

@RestController
public class MoviesController {

    @Autowired
    private MovieService movieService;

    /*********Create a movie***********/
    @RequestMapping(value = "/movie/", method = RequestMethod.POST)
    public ResponseEntity<Void> createMovie(@RequestBody Movie movie, UriComponentsBuilder ucBuilder){
        movieService.create(movie);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/movie/{id}").buildAndExpand(movie.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    /***********Retrieve a Single Movie**********/
    @RequestMapping(value = "/movie/{id}",method = RequestMethod.GET)
    public ResponseEntity<Movie> getMovie(@PathVariable("id") Long id){
        Movie movie = movieService.readById(id);
        if (movie==null){
            return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Movie>(movie,HttpStatus.OK);
    }

    /**********Retrieve all movies*********/
    @RequestMapping(value = "/movies/",method = RequestMethod.GET)
    public ResponseEntity<Set<Movie>> getMovies(){
        Set<Movie> movies = movieService.readAll();
        if (movies.isEmpty()){
            return new ResponseEntity<Set<Movie>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Set<Movie>>(movies,HttpStatus.OK);
    }

    /*************Update a movie*********/
    @RequestMapping(value = "/movie/",method = RequestMethod.PUT)
    public ResponseEntity<Movie> updateMovie(@PathVariable("id") Long id,@RequestBody Movie movie){
        Movie currentMovie = movieService.readById(id);
        if (currentMovie==null){
            return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
        }

        Movie updatedMovie = new Movie.Builder()
                .name(movie.getName())
                .releaseDate(movie.getReleaseDate())
                .durationTime(movie.getDurationTime())
                .build();
        movieService.update(updatedMovie);
        return new ResponseEntity<Movie>(updatedMovie,HttpStatus.OK);
    }

    /*************Delete a Movie************/
    @RequestMapping(value = "/movie/",method = RequestMethod.DELETE)
    public ResponseEntity<Movie> deleteMovie(@PathVariable("id") Long id){
        Movie movie = movieService.readById(id);
        if (movie==null){
            return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
        }
        movieService.delete(movie);
        return new ResponseEntity<Movie>(HttpStatus.NO_CONTENT);
    }

}
