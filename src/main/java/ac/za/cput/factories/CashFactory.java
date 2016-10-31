package ac.za.cput.factories;


import ac.za.cput.domain.Cash;

/**
 * Created by Aphish on 2016/04/22.
 */
public class CashFactory {

    public CashFactory(){}

    public static Cash createCash(Long id, double money, String date){
        Cash cash = new Cash
                .Builder()
                .id(id)
                .cashPayed(money)
                .date(date)
                .build();
        return cash;
    }

}
