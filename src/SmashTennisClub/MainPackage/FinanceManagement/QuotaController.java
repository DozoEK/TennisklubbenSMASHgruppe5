package SmashTennisClub.MainPackage.FinanceManagement;


import java.time.LocalDate;
import java.util.ArrayList;

public class QuotaController {
    private ArrayList<Quota> payments = new ArrayList<>();

    public QuotaController(ArrayList<Quota> payments) {
        this.payments = payments;
    }

    public ArrayList<Quota> getAllPayments() {
        return payments;
    }

    public ArrayList<Quota> getUnpaidPayments() {
        ArrayList<Quota> paymentResult = new ArrayList<>();

        for (Quota r : payments) {
            if (r.getIsPaid() == false)  paymentResult.add(r);
        }
        return paymentResult;
    }

    public ArrayList<Quota> getPaidPayments() {
        ArrayList<Quota> paymentResult = new ArrayList<>();
        for (Quota r : payments) {
            if (r.getIsPaid() == true) paymentResult.add(r);
        }
        return paymentResult;
    }


    public boolean registerPayments(int memberId) {
        for (Quota m : payments) {
            if (m.getMemberId() == memberId) {
                if (m.getIsPaid()){
                    System.out.println("Medlemmet har allerede betalt");
                    return false;
                }
                m.setPaid(true);
                m.setActualDateOfPayment(LocalDate.now());

                System.out.println("Betaling registret for " + m.getMemberName());
                System.out.println("Beløb: " + m.getYearlyMembershipFee().getPrice() + "Kr.");
                System.out.println("Dato: " + LocalDate.now());
                return true;
            }
        }

        System.out.println("kontigent for Medlem er ikke fundet!" + memberId);
        return false;

    }


    public void showUnpaidMembers(){
        ArrayList<Quota> unpaid = getUnpaidPayments();

        if (unpaid.isEmpty()){
            System.out.println("ingen medlemmer mangler betaling.");
            return;
        }

        System.out.println("\n ---Medlemmer der mangler at betale---");
        for (Quota q : unpaid){
            System.out.println( "ID: " + q.getMemberId() +
                    "\n Navn: " + q.getMemberName() +
                    "\n Beløb: " + q.getYearlyMembershipFee().getPrice() + "kr"+
                    "\n Forfalder: " + q.getYearlyFeeDate());
        }

        System.out.println("Total: " + unpaid.size() + "Ubetalte kontigenter.");
    }




}
