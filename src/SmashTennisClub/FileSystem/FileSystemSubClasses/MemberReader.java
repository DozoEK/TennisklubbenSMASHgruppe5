package SmashTennisClub.FileSystem.FileSystemSubClasses;

import SmashTennisClub.FileSystem.SuperReader;
import SmashTennisClub.MainPackage.EnumLists.DisciplineType;
import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MemberType;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;
import SmashTennisClub.MainPackage.MembershipTypes.Junior;
import SmashTennisClub.MainPackage.MembershipTypes.Member;
import SmashTennisClub.MainPackage.MembershipTypes.RecreationalPlayer;
import SmashTennisClub.MainPackage.MembershipTypes.Senior;

import java.time.LocalDate;

public class MemberReader extends SuperReader<Member> {

    @Override
    protected String getCSVFilePathReader() {
        return "CSVFilesLib/Member_Index.csv";
    }

    @Override
    protected int setMaxIndexLength() {
        return 12;
    }

    @Override
    protected Member parseAttributes(String[] parts) {
        int memberId = Integer.parseInt(parts[0]);
        String memberName = parts[1];
        Gender genderOfMember = Gender.valueOf(parts[2]);
        LocalDate dateOfBirth = LocalDate.parse(parts[3]);
        int age = Integer.parseInt(parts[4]);
        int phoneNumber = Integer.parseInt(parts[5]);
        boolean competitivePlayer = Boolean.parseBoolean(parts[6]);
        String memberType = parts[7].toUpperCase();
        MembershipPricelist yearlyMembershipFee = MembershipPricelist.valueOf(parts[8]);
        LocalDate yearlyFeeDate = LocalDate.parse(parts[9]);
        boolean activeMembership = Boolean.parseBoolean(parts[10]);


        DisciplineType discipline = null;
        if (parts.length > 11 && parts[11] != null && !parts[11].trim().isEmpty()) {
            discipline = DisciplineType.valueOf(parts[11].toUpperCase());
        }

        Member member = switch (memberType) {
            case "JUNIOR" -> new Junior(memberId, memberName, genderOfMember, dateOfBirth,
                    age, phoneNumber, true, MemberType.JUNIOR, yearlyMembershipFee,
                    yearlyFeeDate, discipline, activeMembership);

            case "SENIOR" -> new Senior(memberId, memberName, genderOfMember, dateOfBirth,
                    age, phoneNumber, true, MemberType.SENIOR, yearlyMembershipFee,
                    yearlyFeeDate, discipline, activeMembership);

            case "RECREATIONALPLAYER" -> new RecreationalPlayer(memberId, memberName, genderOfMember, dateOfBirth,
                    age, phoneNumber, false, MemberType.RECREATIONALPLAYER, yearlyMembershipFee,
                    yearlyFeeDate, activeMembership);
            default -> throw new IllegalStateException("Unexpected value: " + memberType);
        };

        return member;
    }
}
