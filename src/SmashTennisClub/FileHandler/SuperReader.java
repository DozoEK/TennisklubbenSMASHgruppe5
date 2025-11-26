package SmashTennisClub.FileHandler;

import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;
import SmashTennisClub.MainPackage.MembershipTypes.Member;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SuperReader {


    ArrayList<String[]> members = new ArrayList<>();

    String fileName;


//    public SuperReader(String filename) {
//        this.fileName = setFileName(fileName);
//    }


    public static Member parseAttributes(String [] parts) {

        int memberId = Integer.parseInt(parts[0]);
        String memberName = parts[1];
        Gender genderOfMember = Gender.valueOf(parts[2]); //måske skal den have beskrivelse af at det er fra en ENUM list! "Gender.valueOf"
        LocalDate dateOfBirth = LocalDate.parse(parts[3]);
        int age = Integer.parseInt(parts[4]);
        int phoneNumber = Integer.parseInt(parts[5]);
        Boolean competitivePlayer = Boolean.valueOf(parts[6]);
        MembershipPricelist yearlyMembershipFee = MembershipPricelist.valueOf(parts[7]);
        LocalDate yearlyFeeDate =  LocalDate.parse(parts[8]);
        Boolean activeMembership = Boolean.valueOf(parts[9]);

        return new Member(memberId, memberName, genderOfMember, dateOfBirth,
                age, phoneNumber, competitivePlayer, yearlyMembershipFee, yearlyFeeDate, activeMembership);
    }




    public ArrayList<String[]> reader() {
        try (BufferedReader bufferedReader  = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) { // når linjen er null så er dokumentet færdig læst
                String[] parts = line.split(",");
                if (parts.length == 9) {
                    Member parsedMember = parseAttributes(parts);
                  //  members.add(parsedMember);
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning: " + e.getMessage());
        }
        return members;
    }



    private String setFilename(String filename){return "src/FileHandler/Files/Appointments/" + filename + ".csv";}


    public String getFileName() {
        return fileName;
    }
}
