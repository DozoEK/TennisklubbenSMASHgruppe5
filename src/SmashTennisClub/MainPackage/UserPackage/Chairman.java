package SmashTennisClub.MainPackage.UserPackage;

import SmashTennisClub.FileSystem.FileHandler;
import SmashTennisClub.FileSystem.FileSystemSubClasses.MemberReader;
import SmashTennisClub.FileSystem.FileSystemSubClasses.MemberWriter;
import SmashTennisClub.MainPackage.EnumLists.DisciplineType;
import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;
import SmashTennisClub.MainPackage.MembershipTypes.Junior;
import SmashTennisClub.MainPackage.MembershipTypes.Member;
import SmashTennisClub.MainPackage.MembershipTypes.RecreationalPlayer;
import SmashTennisClub.MainPackage.MembershipTypes.Senior;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Chairman {
    MemberReader reader = new MemberReader();
    MemberWriter writer = new MemberWriter();

    private ArrayList<Member> members = reader.readFromFile();




        public Member createAnyMember(Scanner scanner, int lastUsedMemberId) {

           int memberId = lastUsedMemberId + 1;

            System.out.println("--- Opret medlem ---");

            //memberName
            System.out.print("Indtast navn på medlem: ");
            String name = scanner.nextLine();
            // TODO validate name not empty


            //memberGender
            System.out.println("Indtast køn på medlem ('MALE' / 'FEMALE'): ");
            Gender gender = Gender.valueOf(scanner.nextLine().toUpperCase());
            // TODO validate enum input


            //dateOfBirth
            LocalDate dateOfBirth = null;
            boolean isValid = false;
            while (!isValid) {
                //evt lav denne trycatch i en separat metode?
                System.out.print("Indtast medlemmets fødselsdato (Format på dato: YYYY-MM-DD): ");
                try {
                    dateOfBirth = LocalDate.parse(scanner.nextLine());
                    isValid = true;
                } catch (DateTimeParseException e) {
                    System.out.println("Brug korrekt format = (Format på dato: YYYY-MM-DD)!");
                }
            }


            //udregner alders tamtam
            int age = dateOfBirth.until(LocalDate.now()).getYears();
            System.out.println("Medlemmets alder: " + age);


            //medlems telefonnummer
            System.out.print("Indtast medlemmets telefon nummer: ");
            int phoneNumber = Integer.parseInt(scanner.nextLine());
            // TODO validate phoneNumber length


            //competitivePlayer
            System.out.println("Er medlemmet konkurencespiller? (Indtast: true/false): ");
            boolean competitive = Boolean.parseBoolean(scanner.nextLine());

            DisciplineType discipline = null;

            //hvis kompetetiv =
            if (competitive) {
                System.out.println("Indtast diciplin type:");
                for (DisciplineType d : DisciplineType.values()) {
                    System.out.print(d + " - ");
                }
                System.out.println();
                discipline = DisciplineType.valueOf(scanner.nextLine().toUpperCase());
                // TODO validate enum input
            }


            // automatisk udfyld af membership type
            MembershipPricelist membershipType;
            if (age < 18) {
                membershipType = MembershipPricelist.JUNIOR;
            } else if (age >= 65) {
                membershipType = MembershipPricelist.PENSIONIST;
            } else {
                membershipType = MembershipPricelist.SENIOR;
            }

            System.out.println("Tildelt medlemstype: " + membershipType.name());


            // automatisk udfyld af prisgruppe
            MembershipPricelist membershipFee = membershipType;
            System.out.println("Årspris: " + membershipFee.getPrice());



            //automatisk udfyld af feedate
            LocalDate feeDate = LocalDate.now().plusYears(1);


            //Sætter medlemskab til aktivt.
            boolean activeMembership = true;



            Member member;

            //hvis de ikke er kompetetive:
            if (competitive == false) {
                member = new RecreationalPlayer(
                        memberId, name, gender, dateOfBirth, age, phoneNumber,
                        false, membershipType, membershipFee, feeDate, activeMembership);
            }

            //hvis de er:
            else {
                if (age < 18) {
                        member = new Junior(
                                memberId, name, gender, dateOfBirth, age, phoneNumber,
                                true, membershipType, membershipFee, feeDate, discipline, activeMembership);
                } else {
                    member = new Senior(
                            memberId, name, gender, dateOfBirth, age, phoneNumber,
                            true, membershipType, membershipFee, feeDate, discipline, activeMembership);
                }
            }


            System.out.println("Følgende medlem er oprettet: ");
            System.out.println(member);

            return member;
        }






    public void deleteMember() {
        FileHandler fileHandler = new FileHandler();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("--- Slet medlem ---");
            System.out.println("Indtast medlemsID: ");

            if (input.hasNextInt()) {
                int memberIdToDelete = input.nextInt();
                input.nextLine();


                boolean isFound = false;
                for (Member m : members) {
                    if (m.getMemberId() == memberIdToDelete) {
                        isFound = true;
                        System.out.println("Følgende medlem vil blive slettet:");
                        System.out.println(m);
                        System.out.print("Er du sikker? (ja/nej): ");
                        String confirmation = input.nextLine();

                        if (confirmation.equalsIgnoreCase("ja")) {
                            members.remove(m);
                            System.out.println("Medlem er nu slettet!\n");
                            fileHandler.saveMembers(members);
                            return;
                        } else {
                            System.out.println("Sletning annulleret.");
                            return;
                        }
                    }
                }

                if (!isFound) {
                    System.out.println("Ingen medlemmer er fundet med følgende id: " + memberIdToDelete);
                }
            } else {
                System.out.println("Ugyldigt input! Indtast et tal.");
                input.nextLine();
            }
        }
    }


    public void editMember() {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("--- Ændre oplysninger på et medlem ---");
            System.out.println("Indtast venligst medlems ID på personen som du ønsker at ændre oplysninger på!");
            System.out.println("(for at vende tilbage til menuen, tast 'EXIT')");

            if (input.hasNextInt()) {
                int memberIdToSearchFor = input.nextInt();
                input.nextLine();


            }

            //fileHandler.saveMembers(members);
        }


    }

}
