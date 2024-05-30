package org.stepup.stream6;

import org.stepup.stream6.entity.Account;
import org.stepup.stream6.entity.CurTypes;
import org.stepup.stream6.exceptions.NothingToUndo;

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

        //Part2 for undo operation start
        System.out.println("Make some sequentially Undo...");
        Account acc2 = new Account(
                (x) -> x == null || x.isEmpty(),
                (y) -> y.signum() != 1);
        acc2.putCurrency(CurTypes.RUB, BigDecimal.valueOf(100));
        acc2.setName("Vasiliy Ivanov");
        acc2.putCurrency(CurTypes.RUB, BigDecimal.valueOf(300));
        acc2.setType("PremiumAccount");
        System.out.println("Ready for undo: " + acc2);

        while(acc2.canUndo()){
            acc2.undo();
            System.out.println("undo processed: " +  acc2);
        }
        System.out.println("undo finished: " +  acc2);

        System.out.println("Checking error for illegal undo");
        try{
            acc2.undo();
        } catch(NothingToUndo e) {
            System.out.println("undo processed with error: " + e.toString() + " " + acc2);
        }
        System.out.println("Some sequentially Undo processed...\n");
        //Part2 for undo operation finish

        System.out.println("Task01Mmln finished...");
    }
}
