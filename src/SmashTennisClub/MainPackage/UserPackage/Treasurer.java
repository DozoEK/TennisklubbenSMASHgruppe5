package SmashTennisClub.MainPackage.UserPackage;

import SmashTennisClub.FileSystem.FileHandler;
import SmashTennisClub.MainPackage.FinanceManagement.Quota;
import SmashTennisClub.MainPackage.FinanceManagement.QuotaController;

import java.util.Scanner;

public class Treasurer {
    private QuotaController controller;



    public void showAllPaidQuotas() {
        System.out.println("---Betalte kontigent---");
        for (Quota b : controller.getPaidPayments()){
            System.out.println(b);
        }
    }


    public void showAllUnpaidQuotas() {
        System.out.println("---Ubetalte kontigent--- ");
        for (Quota u : controller.getUnpaidPayments()){
            System.out.println(u);
        }
    }

   public void searchForQuota() {
        Scanner scanner = new Scanner (System.in);
       System.out.println("Indtast venligst MemberId:  ");
       int Id = scanner.nextInt();
       scanner.nextLine();

       boolean isfound = false;

       for (Quota m : controller.getAllPayments()){
           if (m.getMemberId() == Id) {
               System.out.println("kontigentet fundet for medlemmet!");
               System.out.println(m);
               isfound = true;

           }
       }

       if(!isfound){
           System.out.println("ingen betaling fundet for dette medlem!");
       }
   }


    public void printAllQuotas() {
        FileHandler fileHandler = new FileHandler();
        fileHandler.printAllQuotas();
    }

}
