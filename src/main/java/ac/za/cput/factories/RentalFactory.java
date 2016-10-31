package ac.za.cput.factories;

import ac.za.cput.domain.Customers;
import ac.za.cput.domain.Rental;

import java.util.List;
import java.util.UUID;

/**
 * Created by Aphish on 2016/04/22.
 */
public class RentalFactory {

    public RentalFactory(){}

    public static Rental createRental(String date, List<Customers> customers){
        Rental rental = new Rental
                .Builder()
                .rentalNumber(UUID.randomUUID().toString())
                .rentalDate(date)
                .customers(customers)
                .build();
        return rental;
    }

}
