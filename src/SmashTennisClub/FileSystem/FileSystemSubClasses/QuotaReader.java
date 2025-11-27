package SmashTennisClub.FileSystem.FileSystemSubClasses;

import SmashTennisClub.FileSystem.SuperReader;
import SmashTennisClub.MainPackage.FinanceManagement.Quota;
import SmashTennisClub.MainPackage.MembershipTypes.Member;

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
        Member member = String(Member)

        return new String[];

        //        this.quotaId = quotaId;
        //        this.memberId = memberId;
        //        this.memberName = memberName;
        //        this.yearlyMembershipFee = yearlyMembershipFee;
        //        this.isPaid = isPaid;
        //        this.yearlyFeeDate = yearlyFeeDate;
        //        this.actualDateOfPayment = actualDateOfPayment;

        //    protected Member parseAttributes(String[] parts) {
        //        int memberId = Integer.parseInt(parts[0]);
        //        String memberName = parts[1];
        //        int age = Integer.parseInt(parts[2]);
        //        LocalDate dateOfBirth = LocalDate.parse(parts[3]);
        //        Gender genderOfMember = Gender.valueOf(parts[4]);
        //        int phoneNumber = Integer.parseInt(parts[5]);
        //        boolean competitivePlayer = Boolean.parseBoolean(parts[6]);
        //        MembershipPricelist yearlyMembershipFee = MembershipPricelist.valueOf(parts[7]);
        //        LocalDate yearlyFeeDate = LocalDate.parse(parts[8]);
        //        boolean activeMembership = Boolean.parseBoolean(parts[9]);
        //
        //        return new Member(memberId, memberName, genderOfMember, dateOfBirth,
        //                age, phoneNumber, competitivePlayer, yearlyMembershipFee, yearlyFeeDate, activeMembership);
        //    }


    }
}
