package ac.za.cput.client;

import ac.za.cput.domain.Cash;
import ac.za.cput.services.CashService;
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
public class CashController {

    @Autowired
    private CashService cashService;

    @RequestMapping(value = "/cash/", method = RequestMethod.POST)
    public ResponseEntity<Void> createCash(@RequestBody Cash cash, UriComponentsBuilder ucBuilder){
        cashService.create(cash);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/credit/{id}").buildAndExpand(cash.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/cash/{id}", method =RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cash> getCash(@PathVariable("id") Long id){
        Cash cash = cashService.readById(id);
        if (cash==null){
            return new ResponseEntity<Cash>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cash>(cash,HttpStatus.OK);
    }

    @RequestMapping(value = "/cash/", method = RequestMethod.GET)
    public ResponseEntity<Set<Cash>> getCash(){
        Set<Cash> cash = cashService.readAll();
        if (cash.isEmpty()){
            return new ResponseEntity<Set<Cash>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Set<Cash>>(cash,HttpStatus.OK);
    }

    @RequestMapping(value = "/cash/",method = RequestMethod.PUT)
    public ResponseEntity<Cash> updateCash(@PathVariable("id") Long id, @RequestBody Cash cash){
        Cash cash1 = cashService.readById(id);

        if (cash1==null){
            return new ResponseEntity<Cash>(HttpStatus.NOT_FOUND);
        }

        Cash updatedCash = new Cash.Builder().copy(cash1)
                .cashPayed(cash.getCashPayed())
                .date(cash.getDate())
                .build();
        cashService.update(updatedCash);
        return  new ResponseEntity<Cash>(updatedCash,HttpStatus.OK);
    }

    @RequestMapping(value = "/cash/",method = RequestMethod.DELETE)
    public ResponseEntity<Cash> deleteCash(@PathVariable("id") Long id){
        Cash cash = cashService.readById(id);
        if(cash==null){
            return  new ResponseEntity<Cash>(HttpStatus.NOT_FOUND);
        }
        cashService.delete(cash);
        return new ResponseEntity<Cash>(HttpStatus.NO_CONTENT);
    }
}
