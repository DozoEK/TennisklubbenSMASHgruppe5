package SmashTennisClub.FileSystem.FileSystemSubClasses;

import SmashTennisClub.FileSystem.SuperWriter;
import SmashTennisClub.MainPackage.FinanceManagement.Quota;

public class QuotaWriter extends SuperWriter<Quota> {


    @Override
    protected String getCSVFilePathWriter() {
        return "CSVFilesLib/Quota_Index.csv";
    }

    @Override
    protected String[] objectToParts(Quota object) {
        String[] parts = new String[7];

        parts[0] = String.valueOf(object.getQuotaId());
        parts[1] = String.valueOf(object.getMemberId());
        parts[2] = object.getMemberName();
        parts[3] = object.getYearlyMembershipFee().toString();
        parts[4] = String.valueOf(object.getIsPaid());
        parts[5] = object.getYearlyFeeDate().toString();
        parts[6] = (object.getActualDateOfPayment() == null) ? "" : object.getActualDateOfPayment().toString();

        return parts;
    }

}
