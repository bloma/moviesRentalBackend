package ac.za.cput.client;

import ac.za.cput.domain.Rental;
import ac.za.cput.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

/**
 * Created by Aphish on 2016/08/27.
 */

@RestController
public class RentalController {

    @Autowired
    private RentalService rentalService;

    /**********Create Rental**************/

    @RequestMapping(value = "/rental/",method = RequestMethod.POST)
    public ResponseEntity<Void> createRental(@RequestBody Rental rental, UriComponentsBuilder ucBuilder){
        rentalService.create(rental);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/rental/{id}").buildAndExpand(rental.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    /***********Retrieve Rental********************/

    @RequestMapping(value = "/rental/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rental> getRental(@PathVariable("id") Long id){
        Rental rental = rentalService.readById(id);
        if (rental==null){
            return new ResponseEntity<Rental>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Rental>(rental,HttpStatus.OK);
    }

    /***************Retrieve all rentals***************/

    @RequestMapping(value = "/rental/", method = RequestMethod.GET)
    public ResponseEntity<Set<Rental>> getRental(){
        Set<Rental> rentals = rentalService.readAll();
        if (rentals.isEmpty()){
            return new ResponseEntity<Set<Rental>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Set<Rental>>(rentals,HttpStatus.OK);
    }

    /**************Update Rental details**************/

    @RequestMapping(value = "/rental/",method = RequestMethod.PUT)
    public ResponseEntity<Rental> updateRental(@PathVariable("id") Long id, @RequestBody Rental rental){
        Rental currentRental = rentalService.readById(id);

        if (currentRental==null){
            return new ResponseEntity<Rental>(HttpStatus.NOT_FOUND);
        }

        Rental updatedRental = new Rental.Builder().copy(currentRental)
                .rentalDate(rental.getRentalDate())
                .rentalNumber(rental.getRentalNumber())
                .build();
        rentalService.update(updatedRental);
        return  new ResponseEntity<Rental>(updatedRental,HttpStatus.OK);
    }

    /*************Delete an Rental***********/

    @RequestMapping(value = "/rental/",method = RequestMethod.DELETE)
    public ResponseEntity<Rental> deleteRental(@PathVariable("id") Long id){
        Rental rental = rentalService.readById(id);
        if(rental==null){
            return  new ResponseEntity<Rental>(HttpStatus.NOT_FOUND);
        }
        rentalService.delete(rental);
        return new ResponseEntity<Rental>(HttpStatus.NO_CONTENT);
    }

}
