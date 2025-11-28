package SmashTennisClub.FileSystem.FileSystemSubClasses;

import SmashTennisClub.FileSystem.SuperReader;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;
import SmashTennisClub.MainPackage.FinanceManagement.Quota;
import java.time.LocalDate;

public class QuotaReader extends SuperReader<Quota> {


    @Override
    protected String getCSVFilePathReader() {
        return "CSVFilesLib/Quota_Index.csv";
    }

    @Override
    protected int setMaxIndexLength() {
        return 7;
    }


    @Override
    protected Quota parseAttributes(String[] parts) {
        int quotaId = Integer.parseInt(parts[0]);
        int memberId = Integer.parseInt(parts[1]);
        String memberName = parts[2];
        MembershipPricelist yearlyMembershipFee = MembershipPricelist.valueOf(parts[3]);
        boolean isPaid = Boolean.parseBoolean(parts[4]);
        LocalDate yearlyFeeDate = LocalDate.parse(parts[5]);
        LocalDate actualDateOfPayment = LocalDate.parse(parts[6]);

        return new Quota(quotaId, memberId, memberName, yearlyMembershipFee,
                isPaid, yearlyFeeDate, actualDateOfPayment);
    }

}
