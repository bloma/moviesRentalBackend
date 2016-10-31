package ac.za.cput.factories;


import ac.za.cput.domain.Actors;

/**
 * Created by Aphish on 2016/04/22.
 */
public class ActorsFactory {

    public ActorsFactory(){}

    public static Actors createActors(Long id, String name, String surname, String height, String age){
        Actors actors = new Actors
                .Builder()
                .id(id)
                .name(name)
                .surname(surname)
                .age(age)
                .height(height)
                .build();
        return actors;
    }
}
