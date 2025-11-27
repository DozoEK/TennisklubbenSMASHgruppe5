package SmashTennisClub.FileSystem;

import SmashTennisClub.MainPackage.MembershipTypes.Member;

public class MemberWriter extends SuperWriter<Member> {

    @Override
    protected String getCSVFilePathWriter() {
        return "CSVFilesLib/Member_Index.csv";
    }

    @Override
    protected String[] objectToParts(Member member) {
        String[] parts = new String[10];

        parts[0] = String.valueOf(member.getMemberId());
        parts[1] = member.getMemberName();
        parts[2] = String.valueOf(member.getAge());
        parts[3] = member.getDateOfBirth().toString();
        parts[4] = member.getGenderOfMember().toString();
        parts[5] = String.valueOf(member.getPhoneNumber());
        parts[6] = String.valueOf(member.getCompetitivePlayer());
        parts[7] = member.getYearlyMembershipFee().toString();
        parts[8] = member.getYearlyFeeDate().toString();
        parts[9] = String.valueOf(member.getActiveMembership());
        return parts;
    }
}

