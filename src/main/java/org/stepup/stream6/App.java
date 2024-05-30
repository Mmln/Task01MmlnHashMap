package org.stepup.stream6;

import org.stepup.stream6.entity.Account;
import org.stepup.stream6.entity.CurTypes;

import java.math.BigDecimal;

public class App
{
    public static void main( String[] args )
    {
        System.out.println("Task01Mmln started...\n");

        //Account acc1 = new Account(); // this line not compiled
        Account acc = new Account(
                (x) -> x == null || x.isEmpty(),
                (y) -> y.signum() != 1);
        acc.setName("Ivan Petrov");

        acc.putCurrency(CurTypes.RUB, BigDecimal.valueOf(100));
        System.out.println("Setting currency value to 100: " + acc);
        acc.putCurrency(CurTypes.RUB, BigDecimal.valueOf(200));
        System.out.println("Changing currency value to 200: " + acc);
        acc.putCurrency(CurTypes.USD, BigDecimal.valueOf(50));
        System.out.println("Adding new currency: " + acc);

        //this lines throw error
        System.out.println("\nChecking restrictions...");
        try {
            Account accErr = new Account(
                    (x) -> x == null || x.isEmpty(),
                    (y) -> y.signum() != 1);
            accErr.setName("");
        } catch(IllegalArgumentException e) {
            System.out.println("Error: " + e.toString());
        }
        try {
            acc.putCurrency(CurTypes.RUB, BigDecimal.valueOf(-100));
        } catch(IllegalArgumentException e) {
            System.out.print(acc);
            System.out.println(" Error: " + e.toString() + "\n");
        }

        System.out.println("Task01Mmln finished...");
    }
}
