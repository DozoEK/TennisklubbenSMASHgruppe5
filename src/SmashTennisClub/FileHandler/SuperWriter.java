package SmashTennisClub.FileHandler;

import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;
import SmashTennisClub.MainPackage.MembershipTypes.Member;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SuperWriter {

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

    public void saveMembers(ArrayList<Member> members) {
        SuperWriter.writeToFile(members);
    }
}
