package SmashTennisClub.FileSystem;

import SmashTennisClub.MainPackage.MembershipTypes.Member;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MemberWriter {

    //Skal måske slettes og skrives ind i de sub-klasser af REader / writer, hvor de skal bruges!!!!
    public static String memberIndex = "CSVFilesLib/Member_Index.csv";
    public String tournamentIndex = "CSVFilesLib/Tournament_index.csv";
    public String trainingSession = "CSVFilesLib/Training_Session_Index.csv";



    public static void writeToFile(ArrayList<Member> members) {
        try (FileWriter writer = new FileWriter(memberIndex)) {
            for (Member member : members) {
                String line = String.join(",",
                        String.valueOf(member.getMemberId()),
                        member.getMemberName(),
                        Integer.toString(member.getAge()),
                        member.getDateOfBirth().toString(),
                        String.valueOf(member.getGenderOfMember()),
                        String.valueOf(member.getPhoneNumber()),
                        String.valueOf(member.getCompetitivePlayer()),
                        String.valueOf(member.getYearlyMembershipFee()),
                        member.getYearlyFeeDate().toString(),
                        String.valueOf(member.getActiveMembership())
                );
                writer.write(line + "\n");
            }
            System.out.println("Successfully saved to file");


        } catch (IOException e) {
            System.out.println("Error while writing to file!" + e.getMessage());
        }
    }
}


//
//public class MemberReader extends SuperReader<Member> {
//
//    @Override
//    protected String getCSVFilePath() {
//        return "CSVFilesLib/Member_Index.csv";
//    }
//
//    @Override
//    protected int setMaxIndexLength() {
//        return 10; // Number of columns in the Member CSV
//    }
//
//    @Override
//    protected Member parseAttributes(String[] parts) {
//
//        int memberId = Integer.parseInt(parts[0]);
//        String memberName = parts[1];
//        int age = Integer.parseInt(parts[2]);
//        LocalDate dob = LocalDate.parse(parts[3]);
//        Gender gender = Gender.valueOf(parts[4]);
//        int phone = Integer.parseInt(parts[5]);
//        boolean competitive = Boolean.parseBoolean(parts[6]);
//        MembershipPricelist membershipFee = MembershipPricelist.valueOf(parts[7]);
//        LocalDate yearlyFeeDate = LocalDate.parse(parts[8]);
//        boolean active = Boolean.parseBoolean(parts[9]);
//
//        return new Member(memberId, memberName, gender, dob,
//                age, phone, competitive,
//                membershipFee, yearlyFeeDate, active);
//    }
//}
