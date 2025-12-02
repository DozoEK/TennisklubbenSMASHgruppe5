package SmashTennisClub.MainPackage.FinanceManagement;


import SmashTennisClub.FileSystem.FileHandler;
import SmashTennisClub.FileSystem.FileSystemSubClasses.QuotaReader;

import java.util.ArrayList;

public class QuotaController {
    QuotaReader reader = new QuotaReader();
    ArrayList<Quota> quotas = reader.readFromFile();


    public ArrayList<Quota> getAllPayments() {
        return quotas;
    }

    public ArrayList<Quota> getUnpaidPayments() {
        ArrayList<Quota> paymentResult = new ArrayList<>();
        for (Quota quota : quotas) {
            if (quota.getIsPaid() == false)  paymentResult.add(quota);
        }
        return paymentResult;
    }

    public ArrayList<Quota> getPaidPayments() {
        ArrayList<Quota> paymentResult = new ArrayList<>();
        for (Quota quota : quotas) {
            if (quota.getIsPaid() == true) paymentResult.add(quota);
        }
        return paymentResult;
    }

}
