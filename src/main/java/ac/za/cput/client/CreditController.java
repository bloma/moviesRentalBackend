package ac.za.cput.client;

import ac.za.cput.domain.Credit;
import ac.za.cput.services.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;



@RestController
public class CreditController {

    @Autowired
    private CreditService creditService;

    @RequestMapping(value = "/credit/", method = RequestMethod.POST)
    public ResponseEntity<Void> createCredit(@RequestBody Credit credit, UriComponentsBuilder ucBuilder){
        creditService.create(credit);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/credit/{id}").buildAndExpand(credit.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/credit/{id}", method =RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Credit> getCredit(@PathVariable("id") Long id){
        Credit credit = creditService.readById(id);
        if (credit==null){
            return new ResponseEntity<Credit>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Credit>(credit,HttpStatus.OK);
    }

    @RequestMapping(value = "/credit/", method = RequestMethod.GET)
    public ResponseEntity<Set<Credit>> getCredit(){
        Set<Credit> credits = creditService.readAll();
        if (credits.isEmpty()){
            return new ResponseEntity<Set<Credit>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Set<Credit>>(credits,HttpStatus.OK);
    }

    @RequestMapping(value = "/credit/",method = RequestMethod.PUT)
    public ResponseEntity<Credit> updateCredit(@PathVariable("id") Long id, @RequestBody Credit credit){
        Credit credit1 = creditService.readById(id);

        if (credit1==null){
            return new ResponseEntity<Credit>(HttpStatus.NOT_FOUND);
        }

        Credit updatedCredit = new Credit.Builder().copy(credit1)
                .cardHolderName(credit.getCardHolderName())
                .cardNumber(credit.getCardNumber())
                .pin(credit.getPin())
                .build();
        creditService.update(updatedCredit);
        return  new ResponseEntity<Credit>(updatedCredit,HttpStatus.OK);
    }

    @RequestMapping(value = "/credit/",method = RequestMethod.DELETE)
    public ResponseEntity<Credit> deleteCredit(@PathVariable("id") Long id){
        Credit credit = creditService.readById(id);
        if(credit==null){
            return  new ResponseEntity<Credit>(HttpStatus.NOT_FOUND);
        }
        creditService.delete(credit);
        return new ResponseEntity<Credit>(HttpStatus.NO_CONTENT);
    }
}
