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


//public class MemberWriter extends SuperWriter<Member> {
//
//    @Override
//    protected String getCSVFilePathWriter() {
//        return "data/members.csv";
//    }
//
//    @Override
//    protected String[] convertObjectToParts(Member m) {
//        return new String[] {
//                m.getId(),
//                m.getName(),
//                m.getGender().toString(),
//                m.getJoinDate().toString()
//        };
//    }
//}
