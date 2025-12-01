package SmashTennisClub.FileSystem.FileSystemSubClasses;

import SmashTennisClub.FileSystem.SuperWriter;
import SmashTennisClub.MainPackage.MembershipTypes.Junior;
import SmashTennisClub.MainPackage.MembershipTypes.Member;
import SmashTennisClub.MainPackage.MembershipTypes.RecreationalPlayer;
import SmashTennisClub.MainPackage.MembershipTypes.Senior;

public class MemberWriter extends SuperWriter<Member> {

    @Override
    protected String getCSVFilePathWriter() {
        return "CSVFilesLib/Member_Index.csv";
    }

    @Override
    protected String[] objectToParts(Member member) {
        String[] parts = new String[11];

        parts[0] = String.valueOf(member.getMemberId());
        parts[1] = member.getMemberName();
        parts[2] = member.getGenderOfMember().toString();
        parts[3] = member.getDateOfBirth().toString();
        parts[4] = String.valueOf(member.getAge());
        parts[5] = String.valueOf(member.getPhoneNumber());
        parts[6] = String.valueOf(member.getCompetitivePlayer());

        switch (member) {
            case Junior junior -> parts[7] = member.getMembershipType().toString();
            case Senior senior -> parts[7] = member.getMembershipType().toString();
            case RecreationalPlayer recreationalPlayer -> parts[7] = member.getMembershipType().toString();

            default -> parts[7] = "MEMBER";
        }

        parts[8] = member.getYearlyMembershipFee().toString();
        parts[9] = member.getYearlyFeeDate().toString();
        parts[10] = String.valueOf(member.getActiveMembership());
        return parts;
    }
}

