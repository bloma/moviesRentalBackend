package ac.za.cput.factories;


import ac.za.cput.domain.Actors;
import ac.za.cput.domain.Movie;

import java.util.List;

/**
 * Created by Aphish on 2016/04/22.
 */
public class MoviesFactory {

    public MoviesFactory(){}

    public static Movie createMovies(String name, String duration, String date, List<Actors> actors){
        Movie movie = new Movie
                .Builder()
                .name(name)
                .durationTime(duration)
                .releaseDate(date)
                .actors(actors)
                .build();
        return movie;
    }
}
