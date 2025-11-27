package SmashTennisClub.FileSystem.FileSystemSubClasses;

import SmashTennisClub.FileSystem.SuperReader;
import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;
import SmashTennisClub.MainPackage.MembershipTypes.Member;

import java.time.LocalDate;

public class MemberReader extends SuperReader<Member> {

    @Override
    protected String getCSVFilePathReader() {
        return "CSVFilesLib/Member_Index.csv";
    }

    @Override
    protected int setMaxIndexLength() {
        return 10;
    }

    @Override
    protected Member parseAttributes(String[] parts) {
        int memberId = Integer.parseInt(parts[0]);
        String memberName = parts[1];
        int age = Integer.parseInt(parts[2]);
        LocalDate dateOfBirth = LocalDate.parse(parts[3]);
        Gender genderOfMember = Gender.valueOf(parts[4]);
        int phoneNumber = Integer.parseInt(parts[5]);
        boolean competitivePlayer = Boolean.parseBoolean(parts[6]);
        MembershipPricelist yearlyMembershipFee = MembershipPricelist.valueOf(parts[7]);
        LocalDate yearlyFeeDate = LocalDate.parse(parts[8]);
        boolean activeMembership = Boolean.parseBoolean(parts[9]);

        return new Member(memberId, memberName, genderOfMember, dateOfBirth,
                age, phoneNumber, competitivePlayer, yearlyMembershipFee, yearlyFeeDate, activeMembership);
    }
}
