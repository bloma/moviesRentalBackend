package ac.za.cput.factories;


import ac.za.cput.domain.Credit;

/**
 * Created by Aphish on 2016/04/22.
 */
public class CreditFactory {

    public CreditFactory(){}

    public static Credit createCredit(Long id, String name, String cardNumber, String securityCode, String pin){
        Credit credit = new Credit
                .Builder()
                .id(id)
                .cardHolderName(name)
                .cardNumber(cardNumber)
                .securityCode(securityCode)
                .pin(pin)
                .build();
        return credit;
    }
}
