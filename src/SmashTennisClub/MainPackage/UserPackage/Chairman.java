package SmashTennisClub.MainPackage.UserPackage;

import SmashTennisClub.FileSystem.FileHandler;
import SmashTennisClub.FileSystem.FileSystemSubClasses.MemberReader;
import SmashTennisClub.FileSystem.FileSystemSubClasses.MemberWriter;
import SmashTennisClub.MainPackage.EnumLists.DisciplineType;
import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MemberType;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;
import SmashTennisClub.MainPackage.MembershipTypes.Junior;
import SmashTennisClub.MainPackage.MembershipTypes.Member;
import SmashTennisClub.MainPackage.MembershipTypes.RecreationalPlayer;
import SmashTennisClub.MainPackage.MembershipTypes.Senior;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Chairman {
    MemberReader reader = new MemberReader();
    MemberWriter writer = new MemberWriter();

    private ArrayList<Member> members = reader.readFromFile();


    public Member createAnyMember(Scanner scanner) {
        FileHandler fileHandler = new FileHandler();
        MemberReader reader = new MemberReader();
        ArrayList<Member> members = reader.readFromFile();

        int lastUsedMemberId = 0;
        for (Member m : members) {
            if (m.getMemberId() > lastUsedMemberId) {
                lastUsedMemberId = m.getMemberId();
            }
        }

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
        if (competitive == true) {
            System.out.println("Indtast diciplin type:");
            for (DisciplineType d : DisciplineType.values()) {
                System.out.print(d + " - ");
            }
            System.out.println();
            discipline = DisciplineType.valueOf(scanner.nextLine().toUpperCase());
            // TODO validate enum input
        }


        // udfyld af membership pris
        MembershipPricelist membershipFee;
        if (age <= 17) {
            membershipFee = MembershipPricelist.JUNIOR;
        } else if (age > 60) {
            membershipFee = MembershipPricelist.PENSIONIST;
        } else {
            membershipFee = MembershipPricelist.SENIOR;
        }

        System.out.println("Årspris: " + membershipFee.getPrice());


        //membershipType
        MemberType membershipType;
        if (competitive == true && age <= 17) {
            membershipType = MemberType.JUNIOR;

        } else if (competitive == true && age >= 18) {
            membershipType = MemberType.SENIOR;

        } else {
            membershipType = MemberType.RECREATIONALPLAYER;
        }


        //automatisk udfyld af feedate
        LocalDate feeDate = LocalDate.now().plusYears(1);


        //Sætter medlemskab til aktivt.
        boolean activeMembership = true;


        Member member;
        //hvis de er:
        if (competitive == true && (age < 18)) {
            member = new Junior(
                    memberId, name, gender, dateOfBirth, age, phoneNumber,
                    true, membershipType, membershipFee, feeDate, discipline, activeMembership);
        } else if (competitive == true && age > 18) {
            member = new Senior(
                    memberId, name, gender, dateOfBirth, age, phoneNumber,
                    true, membershipType, membershipFee, feeDate, discipline, activeMembership);
        } else if (competitive == false) {
            member = new RecreationalPlayer(memberId, name, gender, dateOfBirth, age, phoneNumber,
                    false, membershipType, membershipFee, feeDate, activeMembership);
        } else {
            member = new Member(memberId, name, gender, dateOfBirth, age, phoneNumber,
                    false, membershipType, membershipFee, feeDate, activeMembership);
        }

        members.add(member);
        fileHandler.saveMembers(members);

        System.out.println("Følgende medlem er oprettet og gemt til CSV:");
        System.out.println(member);

        return member;
    }


    public void deleteMember() {
        FileHandler fileHandler = new FileHandler();
        Scanner input = new Scanner(System.in);
        UserHelperClass userHelper = new UserHelperClass();

        while (true) {
            System.out.println("--- Slet medlem ---");

            Member selectedMember = userHelper.searchForMember();

            if (selectedMember == null) {
                System.out.println("Ingen medlem valgt. Afbryder sletning af spiller.");
                return;
            }

            System.out.println("Følgende medlem er valgt: " + selectedMember);

            System.out.print("Er du sikker på at medlemmet skal slettes? (ja/nej): ");

            String confirmation = input.nextLine();
            if (confirmation.equalsIgnoreCase("ja")) {
                members.remove(selectedMember);
                System.out.println("Medlemmet er nu slettet!\n");
                fileHandler.saveMembers(members);
                return;
            } else {
                System.out.println("Sletning annulleres!");
                return;
            }

        }

    }


    //TODO lav editMember()
    public void editMember() {
        FileHandler fileHandler = new FileHandler();
        Scanner scanner = new Scanner(System.in);
        UserHelperClass userHelper = new UserHelperClass();

        System.out.println("--- Ændre oplysninger på et medlem ---");

        Member selectedMember = userHelper.searchForMember();

        if (selectedMember == null) {
            System.out.println("intet medlem fundet, redigering afbrudt");
            return;
        }

        System.out.println("\nvalgt medlem:");
        System.out.println(selectedMember);

        int memberPosition = -1;
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getMemberId() == selectedMember.getMemberId()) {
                memberPosition = i;
                break;
            }
        }
        if (memberPosition == -1) {
            System.out.println("kunne ikke finde medlemmet på liste");
            return;
        }

        int memberId = selectedMember.getMemberId();

        System.out.println("---indtast de nye oplysninger---");

        //ændre navn
        System.out.println("Indtast nyt navn ( nuværende: " + selectedMember.getMemberName() + "): ");
        String name = scanner.nextLine();

        //ændre køn
        System.out.println("indtast køn på medlem ('MALE' / 'FEMALE' (nuværende: " + selectedMember.getGenderOfMember() + "): ");
        Gender gender = Gender.valueOf(scanner.nextLine().toUpperCase());
        //Fødselsdag
        LocalDate dateOfBirth = null;
        boolean isValid = false;

        System.out.println("registreret fødselsdag: " + selectedMember.getDateOfBirth());
        while (!isValid) {
            System.out.print("indtast den korrekte fødselsdag  (format: YYYY-MM-DD): ");
            try {
                dateOfBirth = LocalDate.parse(scanner.nextLine());
                isValid = true;
            } catch (DateTimeParseException e) {
                System.out.println("Skriv det korrekte format! (YYYY-MM-DD)");
            }
        }
        //beregner alder ud fra ny fødselsdag
        int age = dateOfBirth.until(LocalDate.now()).getYears();
        System.out.println("medlemmets nye registrerede alder: " + age + "år ");

        //Telefonnummer
        System.out.print("Indtast nyt telefonnummer (nuværende: " + selectedMember.getPhoneNumber() + "): ");
        int phoneNumber = Integer.parseInt(scanner.nextLine());

        // Konkurrencespiller
        System.out.println("Er medlemmet konkurencespiller? (true/false) (nuværende: " + selectedMember.getCompetitivePlayer() + "): ");
        boolean competitive = Boolean.parseBoolean(scanner.nextLine());

        DisciplineType discipline = null;

        // Hvis konkurrencespiller, vælg disciplin
        if (competitive == true) {
            System.out.println("Indtast disciplin type:");
            for (DisciplineType d : DisciplineType.values()) {
                System.out.print(d + " - ");
            }
            System.out.println();

            // Vis nuværende disciplin hvis det er Junior eller Senior
            if (selectedMember instanceof Junior) {
                System.out.println("(nuværende: " + ((Junior) selectedMember).getJuniorDisciplinType() + ")");
            } else if (selectedMember instanceof Senior) {
                System.out.println("(nuværende: " + ((Senior) selectedMember).getSeniorDisciplinType() + ")");
            }

            discipline = DisciplineType.valueOf(scanner.nextLine().toUpperCase());
        }
        // Beregn membership fee ud fra alder
        MembershipPricelist membershipFee;
        if (age <= 17) {
            membershipFee = MembershipPricelist.JUNIOR;
        } else if (age > 60) {
            membershipFee = MembershipPricelist.PENSIONIST;
        } else {
            membershipFee = MembershipPricelist.SENIOR;
        }

        System.out.println("Ny årspris: " + membershipFee.getPrice());

        // Beregn membership type
        MemberType membershipType;
        if (competitive == true && age <= 17) {
            membershipType = MemberType.JUNIOR;
        } else if (competitive == true && age >= 18) {
            membershipType = MemberType.SENIOR;
        } else {
            membershipType = MemberType.RECREATIONALPLAYER;
        }
        // Behold den gamle yearlyFeeDate og activeMembership
        LocalDate feeDate = selectedMember.getYearlyFeeDate();
        boolean activeMembership = selectedMember.getActiveMembership();

        // Step 4: Opret nyt medlem objekt med samme ID
        Member newMember;

        if (competitive == true && age < 18) {
            newMember = new Junior(
                    memberId, name, gender, dateOfBirth, age, phoneNumber,
                    true, membershipType, membershipFee, feeDate, discipline, activeMembership);
        } else if (competitive == true && age >= 18) {
            newMember = new Senior(
                    memberId, name, gender, dateOfBirth, age, phoneNumber,
                    true, membershipType, membershipFee, feeDate, discipline, activeMembership);
        } else {
            newMember = new RecreationalPlayer(
                    memberId, name, gender, dateOfBirth, age, phoneNumber,
                    false, membershipType, membershipFee, feeDate, activeMembership);
        }
        // Step 5: Slet det gamle medlem og indsæt det nye på samme plads
        members.remove(memberPosition);
        members.add(memberPosition, newMember);

        // Step 6: Gem til fil
        fileHandler.saveMembers(members);

        System.out.println("\nMedlemmet er blevet opdateret!");
        System.out.println("Nyt medlem:");
        System.out.println(newMember);
    }
}