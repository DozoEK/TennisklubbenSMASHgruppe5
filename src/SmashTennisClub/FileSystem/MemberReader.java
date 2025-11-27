package SmashTennisClub.FileSystem;

import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;
import SmashTennisClub.MainPackage.MembershipTypes.Member;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MemberReader {

    //Path ændres i hver subclass!
    String fileName = "CSVFilesLib/Member_Index.csv";


    public Member parseAttributes(String [] parts) {
        int memberId = Integer.parseInt(parts[0]);
        String memberName = parts[1];
        int age = Integer.parseInt(parts[2]);
        LocalDate dateOfBirth = LocalDate.parse(parts[3]);
        Gender genderOfMember = Gender.valueOf(parts[4]);
        int phoneNumber = Integer.parseInt(parts[5]);
        Boolean competitivePlayer = Boolean.valueOf(parts[6]);
        MembershipPricelist yearlyMembershipFee = MembershipPricelist.valueOf(parts[7]);
        LocalDate yearlyFeeDate =  LocalDate.parse(parts[8]);
        Boolean activeMembership = Boolean.valueOf(parts[9]);

        return new Member(memberId, memberName, genderOfMember, dateOfBirth,
                age, phoneNumber, competitivePlayer, yearlyMembershipFee, yearlyFeeDate, activeMembership);
    }


    public ArrayList<Member> readFromFile() {
        ArrayList<Member> members = new ArrayList<>();
        int indexMaxLength = 10;

        try (BufferedReader bufferedReader  = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == indexMaxLength) {
                    Member parsedMember = parseAttributes(parts);
                    members.add(parsedMember);
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning: " + e.getMessage());
        }
        return members;
    }

    public String getFileName() {
        return fileName;
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