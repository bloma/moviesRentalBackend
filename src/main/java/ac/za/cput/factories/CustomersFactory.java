package ac.za.cput.factories;


import ac.za.cput.domain.Customers;

/**
 * Created by Aphish on 2016/04/22.
 */
public class CustomersFactory {

    public CustomersFactory(){}

    public static Customers createCustomers(Long id, String name, String surname, String age){
        Customers customers = new Customers
                .Builder()
                .id(id)
                .customerName(name)
                .customeruSurname(surname)
                .customerAge(age)
                .build();
        return customers;
    }
}
