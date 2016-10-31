package ac.za.cput.client;

import ac.za.cput.domain.Customers;
import ac.za.cput.services.CustomersService;
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
public class CustomerController {

    @Autowired
    private CustomersService customersService;

    /**********Create an Customer**************/

    @RequestMapping(value = "/customers/",method = RequestMethod.POST)
    public ResponseEntity<Void> createCustomer(@RequestBody Customers customers, UriComponentsBuilder ucBuilder){
        customersService.create(customers);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/actors/{id}").buildAndExpand(customers.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    /***********Retrieve a Customer********************/

    @RequestMapping(value = "/customers/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customers> getCustomer(@PathVariable("id") Long id){
        Customers customers = customersService.readById(id);
        if (customers==null){
            return new ResponseEntity<Customers>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Customers>(customers,HttpStatus.OK);
    }

    /***************Retrieve all customers***************/

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public ResponseEntity<Set<Customers>> getCustomers(){
        Set<Customers> customerses = customersService.readAll();
        if (customerses.isEmpty()){
            return new ResponseEntity<Set<Customers>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Set<Customers>>(customerses,HttpStatus.OK);
    }

    /**************Update Customers details**************/

    @RequestMapping(value = "/customers/",method = RequestMethod.PUT)
    public ResponseEntity<Customers> updateCustomer(@PathVariable("id") Long id, @RequestBody Customers customers){
        Customers currentCustomer = customersService.readById(id);

        if (currentCustomer==null){
            return new ResponseEntity<Customers>(HttpStatus.NOT_FOUND);
        }

        Customers updatedCustomer = new Customers.Builder().copy(currentCustomer)
                .customerName(customers.getName())
                .customeruSurname(customers.getSurname())
                .customerAge(customers.getAge())
                .build();
        customersService.update(updatedCustomer);
        return  new ResponseEntity<Customers>(updatedCustomer,HttpStatus.OK);
    }

    /*************Delete a Customer***********/

    @RequestMapping(value = "/customers/",method = RequestMethod.DELETE)
    public ResponseEntity<Customers> deleteCustomer(@PathVariable("id") Long id){
        Customers customers = customersService.readById(id);
        if(customers==null){
            return  new ResponseEntity<Customers>(HttpStatus.NOT_FOUND);
        }
        customersService.delete(customers);
        return new ResponseEntity<Customers>(HttpStatus.NO_CONTENT);
    }

}
