package ac.za.cput.client;

import ac.za.cput.domain.Actors;
import ac.za.cput.services.ActorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

/**
 * Created by Aphish on 2016/08/16.
 */

@RestController
public class ActorsController {

    @Autowired
    private ActorsService actorsService;

    /**********Create an Actor**************/

    @RequestMapping(value = "/actors/",method = RequestMethod.POST)
    public ResponseEntity<Void> createActors(@RequestBody Actors actors, UriComponentsBuilder ucBuilder){
        actorsService.create(actors);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/actors/{id}").buildAndExpand(actors.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    /***********Retrieve actor********************/

    @RequestMapping(value = "/actor/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Actors> getActors(@PathVariable("id") Long id){
        Actors actors = actorsService.readById(id);
        if (actors==null){
            return new ResponseEntity<Actors>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Actors>(actors,HttpStatus.OK);
    }

    /***************Retrieve all actors***************/

    @RequestMapping(value = "/actors/", method = RequestMethod.GET)
    public ResponseEntity<Set<Actors>> getActors(){
        Set<Actors> actorses = actorsService.readAll();
        if (actorses.isEmpty()){
            return new ResponseEntity<Set<Actors>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Set<Actors>>(actorses,HttpStatus.OK);
    }

    /**************Update Actors details**************/

    @RequestMapping(value = "/actors/",method = RequestMethod.PUT)
    public ResponseEntity<Actors> updateActors(@PathVariable("id") Long id, @RequestBody Actors actors){
        Actors currentActors = actorsService.readById(id);

        if (currentActors==null){
            return new ResponseEntity<Actors>(HttpStatus.NOT_FOUND);
        }

        Actors updatedActor = new Actors.Builder().copy(currentActors)
                .name(actors.getName())
                .surname(actors.getSurname())
                .age(actors.getAge())
                .height(actors.getHeight())
                .build();
        actorsService.update(updatedActor);
        return  new ResponseEntity<Actors>(updatedActor,HttpStatus.OK);
    }

    /*************Delete an Actor***********/

    @RequestMapping(value = "/actors/",method = RequestMethod.DELETE)
    public ResponseEntity<Actors> deleteActor(@PathVariable("id") Long id){
        Actors actors = actorsService.readById(id);
                if(actors==null){
                    return  new ResponseEntity<Actors>(HttpStatus.NOT_FOUND);
                }
                actorsService.delete(actors);
        return new ResponseEntity<Actors>(HttpStatus.NO_CONTENT);
    }

}
