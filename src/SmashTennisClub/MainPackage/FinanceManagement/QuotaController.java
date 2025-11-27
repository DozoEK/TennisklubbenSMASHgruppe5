package SmashTennisClub.MainPackage.FinanceManagement;


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


//    public boolean registerPayments(int memberId) {
//        for (Quota m : payments) {
//            if (m.memberId == memberId) {
//                isPaid = true;
//                m.actualDateOfPayment = new Date();
//
//                System.out.println("Betalingen er registeret! ");
//            }
//
//        }
//        System.out.println("Medlem er ikke fundet!");
//        return false;
//
//
//        ArrayList<Quota> quoatoPayments = new ArrayList<>();
//        int searchedMemberId;
//
//        boolean makepayment() {
//            for (Quota q : quoatoPayments) {
//                if (searchedMemberId == Quota.getMemberId && Quota.getIsPaid() == false) {
//                    q.actualDateOfPayment == LocalDate.of(LocalDate.now());
//
//
//                } else {
//                    System.out.println("No quotas were found without payment for: " + Quota.getMemberId);
//                }
//            }
//            return setIsPaid()==true;
//        }
//
//    }


}
