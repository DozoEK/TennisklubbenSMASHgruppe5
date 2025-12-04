package SmashTennisClub.MainPackage.UserPackage;

import SmashTennisClub.FileSystem.FileHandler;
import SmashTennisClub.FileSystem.FileSystemSubClasses.MemberReader;
import SmashTennisClub.FileSystem.FileSystemSubClasses.MemberWriter;
import SmashTennisClub.MainPackage.EnumLists.DisciplineType;
import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MemberType;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;
import SmashTennisClub.MainPackage.ErrorAndValidation.SmashException;
import SmashTennisClub.MainPackage.ErrorAndValidation.ValidationInterface;
import SmashTennisClub.MainPackage.ErrorAndValidation.ValidationMethods;
import SmashTennisClub.MainPackage.MembershipTypes.Junior;
import SmashTennisClub.MainPackage.MembershipTypes.Member;
import SmashTennisClub.MainPackage.MembershipTypes.RecreationalPlayer;
import SmashTennisClub.MainPackage.MembershipTypes.Senior;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Chairman {
    ValidationInterface validator = new ValidationMethods();

    private ArrayList<Member> members;

    public Chairman(ArrayList<Member> members) {
        this.members = members;
    }



    public void chairmanMenu() {
        UserHelperClass uhc = new UserHelperClass(members);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n===== SMASH TENNIS CLUB | FORMANDS MENU =====");
            System.out.println("1. Opret medlem");
            System.out.println("2. Slet medlem");
            System.out.println("3. Redigér medlem");
            System.out.println("4. Vis alle medlemmer");
            System.out.println();
            System.out.println("('x' for afslut menu || 'exit' for afslut program)");
            System.out.print("Vælg en funktion (1-4): ");


            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    createAnyMember(scanner);
                    break;

                case "2":
                    deleteMember();
                    break;

                case "3":
                    editMember();
                    break;

                case "4":
                    uhc.printAllMembers();
                    break;

                case "5":
                    System.out.println("Lukker formands-menu...");
                    running = false;
                    break;

                case "x":
                    System.out.println("Går tilbage til startmenu...");
                    running = false;
                    break;

                case "exit":
                    System.out.println("Program afsluttes...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Ugyldigt valg. Prøv igen.");
            }
        }
    }





    public Member createAnyMember(Scanner scanner) {
        FileHandler fileHandler = new FileHandler();

        int lastUsedMemberId = 0;
        for (Member m : members) {
            if (m.getMemberId() > lastUsedMemberId) {
                lastUsedMemberId = m.getMemberId();
            }
        }

        int memberId = lastUsedMemberId + 1;


        System.out.println("\n--- Opret medlem ---");
        System.out.println();

            //memberName
            String name = null;
            while (true) {
                System.out.print("Indtast navn på medlem: ");
                String input = scanner.nextLine();
                try {
                    validator.validateName(input);
                    name = input;
                    break;
                } catch (SmashException e) {
                    System.out.println(e.getMessage());
            }
        }


            //memberGender
            Gender gender = null;
            while (true) {
                System.out.print("Indtast køn på medlem ('MALE' / 'FEMALE'): ");
                String input = scanner.nextLine().toUpperCase();
                try {
                    gender = Gender.valueOf(input);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Ugyldigt input.");
                }
            }



            //dateOfBirth
            LocalDate dateOfBirth = null;
            boolean isValid = false;
            while (!isValid) {
                //evt lav denne trycatch i en separat metode?
                System.out.print("Indtast medlemmets fødselsdato (Format på dato: YYYY-MM-DD): ");
                try {
                    String input = scanner.nextLine();
                    dateOfBirth = LocalDate.parse(input);

                    validator.validateDateTime(input);
                    validator.validateNoFutureDate(input);

                    isValid = true;

                } catch (DateTimeParseException e) {
                    System.out.println("Brug korrekt format = (Format på dato: YYYY-MM-DD)!");
                } catch (SmashException e) {
                    System.out.println(e.getMessage());
                }
            }


        //udregner alders tamtam
        int age = dateOfBirth.until(LocalDate.now()).getYears();
        System.out.println("Medlemmets alder: " + age);


            //medlems telefonnummer
            int phoneNumber = 0;
            while (true) {
                System.out.print("Indtast medlemmets telefon nummer: ");
                String input = scanner.nextLine();

                try {
                    phoneNumber = validator.validatePhoneNumberLength(input);
                    break;
                } catch (SmashException e) {
                    System.out.println(e.getMessage());
                }
            }



            //competitivePlayer
            boolean competitive = false;
            while (true) {
                System.out.print("Er medlemmet konkurencespiller? (Indtast: true/false): ");
                String input = scanner.nextLine();
                try {
                    competitive = validator.validateBoolean(input);
                    break;
                } catch (SmashException e) {
                    System.out.println(e.getMessage());
                }
            }

            DisciplineType discipline = null;

            //hvis kompetetiv =
            if (competitive) {
                while (true) {
                    System.out.print("Indtast diciplin type:");
                    for (DisciplineType d : DisciplineType.values()) {
                        System.out.print(d + " - ");
                    }
                    System.out.println();

                    try {
                        discipline = DisciplineType.valueOf(scanner.nextLine().toUpperCase());
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("ugyldig disciplin, prøv igen");
                    }
                }
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
        System.out.println();

        System.out.println("Følgende medlem er oprettet og gemt til CSV:");
        System.out.println(member);
        System.out.println();
        System.out.println("Årspris: " + membershipFee.getPrice());

        return member;
    }



    public void deleteMember() {
        FileHandler fileHandler = new FileHandler();
        Scanner input = new Scanner(System.in);
        UserHelperClass userHelper = new UserHelperClass(members);

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
                System.out.println();
                System.out.println("Medlemmet er nu slettet!\n");
                fileHandler.saveMembers(members);
                return;
            } else {
                System.out.println("Sletning annulleres!");
                return;
            }

        }

    }


    public void editMember() {
        FileHandler fileHandler = new FileHandler();
        Scanner scanner = new Scanner(System.in);
        UserHelperClass userHelper = new UserHelperClass(members);

        System.out.println("--- Rediger oplysninger på et medlem ---");

        Member selectedMember = userHelper.searchForMember();

        if (selectedMember == null) {
            System.out.println("Intet medlem fundet, redigering afbrudt.");
            return;
        }

        System.out.println("\nValgt medlem:");
        System.out.println(selectedMember);


        boolean editing = true;
        while (editing) {
            System.out.println("\nVælg, hvilken information du vil ændre:");
            System.out.println("1. Navn");
            System.out.println("2. Køn");
            System.out.println("3. Fødselsdato");
            System.out.println("4. Telefonnummer");
            System.out.println("5. Konkurrencespiller");
            System.out.println("6. Disciplin (kun konkurrencespillere)");
            System.out.println("7. Aktivt medlemskab");
            System.out.println("8. Afslut redigering");
            System.out.println();
            System.out.print("Indtast dit valg (1-8): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> editName(selectedMember, scanner);
                case "2" -> editGender(selectedMember, scanner);
                case "3" -> editDateOfBirth(selectedMember, scanner);
                case "4" -> editPhoneNumber(selectedMember, scanner);
                case "5" -> editCompetitive(selectedMember, scanner);
                case "6" -> editDiscipline(selectedMember, scanner);
                case "7" -> editActiveMembership(selectedMember, scanner);
                case "8" -> editing = false;
                default -> System.out.println("Ugyldigt valg, prøv igen.");
            }

            recalcMembership(selectedMember);

        }
        fileHandler.saveMembers(members);
        System.out.println("\nMedlemmet er blevet opdateret:");
        System.out.println(selectedMember);
    }

    private void editName(Member member, Scanner scanner) {
        System.out.print("Indtast nyt navn (nuværende: " + member.getMemberName() + "): ");
        member.setMemberName(scanner.nextLine());
    }

    private void editGender(Member member, Scanner scanner) {
        System.out.print("Indtast køn ('MALE' / 'FEMALE') (nuværende: " + member.getGenderOfMember() + "): ");
        member.setGenderOfMember(Gender.valueOf(scanner.nextLine().toUpperCase()));
    }

    private void editDateOfBirth(Member member, Scanner scanner) {
        boolean validDate = false;
        while (!validDate) {
            System.out.print("Indtast fødselsdato (YYYY-MM-DD) (nuværende: " + member.getDateOfBirth() + "): ");
            try {
                member.setDateOfBirth(LocalDate.parse(scanner.nextLine()));
                member.updateAge();
                System.out.println("Ny alder: " + member.getAge());
                validDate = true;
            } catch (DateTimeParseException e) {
                System.out.println("Ugyldigt format! Brug YYYY-MM-DD.");
            }
        }
    }

    private void editPhoneNumber(Member member, Scanner scanner) {
        System.out.print("Indtast nyt telefonnummer (nuværende: " + member.getPhoneNumber() + "): ");
        member.setPhoneNumber(Integer.parseInt(scanner.nextLine()));
    }

    private void editCompetitive(Member member, Scanner scanner) {
        System.out.print("Er medlemmet konkurrencespiller? (true/false) (nuværende: " + member.getCompetitivePlayer() + "): ");
        member.setCompetitivePlayer(Boolean.parseBoolean(scanner.nextLine()));
    }

    private void editDiscipline(Member member, Scanner scanner) {
        if (!member.getCompetitivePlayer()) {
            System.out.println("Medlemmet er ikke konkurrencespiller, disciplin kan ikke ændres.");
            return;
        }

        System.out.println("Vælg disciplin type:");
        for (DisciplineType d : DisciplineType.values()) {
            System.out.print(d + " - ");
        }
        System.out.println();

        if (member instanceof Junior) {
            System.out.println("(nuværende: " + ((Junior) member).getJuniorDisciplinType() + ")");
            ((Junior) member).setJuniorDisciplinType(DisciplineType.valueOf(scanner.nextLine().toUpperCase()));
        } else if (member instanceof Senior) {
            System.out.println("(nuværende: " + ((Senior) member).getSeniorDisciplinType() + ")");
            ((Senior) member).setSeniorDisciplinType(DisciplineType.valueOf(scanner.nextLine().toUpperCase()));
        } else {
            System.out.println("Kun Junior eller Senior medlemmer har disciplin.");
        }
    }

    private void editActiveMembership(Member member, Scanner scanner) {
        System.out.print("Er medlemskabet aktivt? (true/false) (nuværende: " + member.getActiveMembership() + "): ");
        member.setActiveMembership(Boolean.parseBoolean(scanner.nextLine()));
    }

    private void recalcMembership(Member member) {
        int age = member.getAge();
        boolean competitive = member.getCompetitivePlayer();

        MembershipPricelist fee = age <= 17 ? MembershipPricelist.JUNIOR :
                age > 60 ? MembershipPricelist.PENSIONIST : MembershipPricelist.SENIOR;
        member.setYearlyMembershipFee(fee);

        MemberType type = !competitive ? MemberType.RECREATIONALPLAYER :
                (age <= 17 ? MemberType.JUNIOR : MemberType.SENIOR);
        member.setMembershipType(type);
    }

}