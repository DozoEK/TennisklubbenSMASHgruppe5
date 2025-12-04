package SmashTennisClub.MainPackage.UserPackage;

import SmashTennisClub.FileSystem.FileHandler;
import SmashTennisClub.FileSystem.FileSystemSubClasses.MemberReader;
import SmashTennisClub.FileSystem.FileSystemSubClasses.PlayerEntryReader;
import SmashTennisClub.FileSystem.FileSystemSubClasses.PlayerEntryWriter;
import SmashTennisClub.MainPackage.EnumLists.DisciplineType;
import SmashTennisClub.MainPackage.ErrorAndValidation.CustomExceptions.SmashException;
import SmashTennisClub.MainPackage.ErrorAndValidation.ValidationInterface;
import SmashTennisClub.MainPackage.ErrorAndValidation.ValidationMethods;
import SmashTennisClub.MainPackage.MembershipTypes.Member;
import SmashTennisClub.MainPackage.PlayerStatistic.CompetitivePlayerHelper;
import SmashTennisClub.MainPackage.PlayerStatistic.PlayerEntry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Coach {
    private final ArrayList<Member> members;
    ValidationInterface validator = new ValidationMethods();
    PlayerEntryReader playerEntryReader = new PlayerEntryReader();
    ArrayList<PlayerEntry> playerEntries = playerEntryReader.readFromFile();

    public Coach(ArrayList<Member> members) {
        this.members = members;
    }

    public void coachMenu() {


        CompetitivePlayerHelper cph = new CompetitivePlayerHelper();
        UserHelperClass uhc = new UserHelperClass(this.members);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        cph.updateAllCompetitiveStats();

        while (running) {
            System.out.println("\n===== SMASH TENNIS CLUB | COACH MENU =====");
            System.out.println("1. Vis alle konkurrencespillere");
            System.out.println("2. Top 5 spillere");
            System.out.println("3. Vis alt spillerstatistik");
            System.out.println();
            System.out.println("4. Vis alle Kampregistreringer");
            System.out.println("5. Søg Kampregistrering (via medlems ID / medlems navn)");
            System.out.println("6. Søg Kampregistrering (via Kampregistrerings-ID)");
            System.out.println("7. Opret Kampregistrering");
            System.out.println("8. Redigér Kampregistrering");
            System.out.println("9. Slet Kampregistrering");
            System.out.println();
            System.out.println("10. Opret spiller statistik (for medlem)");
            System.out.println();
            System.out.println("('x' for afslut menu || 'exit' for afslut program)");
            System.out.print("Vælg en funktion (1-10): ");

            String choice = scanner.nextLine().trim();

            try {
                validator.validateLettersOrNumbersOnly(choice);
            } catch (SmashException e) {
                System.out.println(e.getMessage());
                continue;
            }

            switch (choice) {

                case "1":
                    showAllCompetetivePlayers();
                    break;

                case "2":
                    cph.showTop5AllByDiscipline();
                    break;

                case "3":
                    cph.printAllCompetitivePlayerStats();
                    break;

                case "4":
                    showAllPlayerEntry();
                    break;

                case "5":
                    searchForPlayerEntryByMemberInfo();
                    break;

                case "6":
                    searchForPlayerEntry();
                    break;

                case "7":
                    createPlayerEntry(scanner);
                    break;

                case "8":
                    editPlayerEntry();
                    break;

                case "9":
                    deletePlayerEntry();
                    break;

                case "10":
                    Member member = uhc.searchForMember();
                    if (member != null) {
                        cph.createStatsForSingleCompetitiveMember(member);
                    }
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


    public void showAllCompetetivePlayers() {
        MemberReader mr = new MemberReader();
        ArrayList<Member> members = mr.readFromFile();

        System.out.println("--- Alle konkurrencespillere ---");
        System.out.println();

        boolean foundAny = false;

        for (Member member : members) {
            if (member.getCompetitivePlayer()) {
                System.out.println(member);
                foundAny = true;
            }
        }

        if (!foundAny) {
            System.out.println("Ingen konkurrencespillere fundet.");
        }
    }


    public void deletePlayerEntry() {
        PlayerEntryReader per = new PlayerEntryReader();
        PlayerEntryWriter pew = new PlayerEntryWriter();
        UserHelperClass userHelper = new UserHelperClass(members);
        Scanner input = new Scanner(System.in);
        ArrayList<PlayerEntry> playerEntries = per.readFromFile();


        Member selectedMember = userHelper.searchForMember();
        int memberIdForPlayerEntry = selectedMember.getMemberId();

        PlayerEntry foundEntry = null;
        for (PlayerEntry playerEntry : playerEntries) {
            if (playerEntry.getMemberId() == memberIdForPlayerEntry) {
                foundEntry = playerEntry;
            }
        }

        if (foundEntry == null) {
            System.out.println("Ingen kampregistrering fundet med ID: " + memberIdForPlayerEntry);
            return;
        }

        System.out.println("Kampregistrering til sletning: " + foundEntry);

        boolean confirmDelete;

        while (true) {
            System.out.print("Er du sikker på at du vil slette denne? (ja/nej): ");
            try {
                confirmDelete = validator.validateYesOrNo(input.nextLine());
                break;
            } catch (SmashException e) {
                System.out.println(e.getMessage());
            }
        }
        if (confirmDelete) {
            playerEntries.remove(foundEntry);
            pew.writeToFile(playerEntries);
            System.out.println("Entry er nu slettet!\n");
        } else {
            System.out.println("Sletning afbrudt.");
        }
    }


    public void editPlayerEntry() {
        PlayerEntryReader per = new PlayerEntryReader();
        PlayerEntryWriter pew = new PlayerEntryWriter();
        ArrayList<PlayerEntry> playerEntries = per.readFromFile();
        Scanner scanner = new Scanner(System.in);

        int playerEntryId;

        while (true) {
            System.out.print("Indtast kampregistrering-ID på entry der skal redigeres: ");
            String input = scanner.nextLine();

            try {
                playerEntryId = validator.validateInt(input);
                break;
            } catch (SmashException e) {
                System.out.println(e.getMessage());
            }
        }


        PlayerEntry playerEntryToEdit = null;

        for (PlayerEntry playerEntry : playerEntries) {
            if (playerEntry.getPlayerEntryId() == playerEntryId) {
                playerEntryToEdit = playerEntry;
                break;
            }
        }

        if (playerEntryToEdit == null) {
            System.out.println("Ingen kampregistrering fundet med ID: " + playerEntryId);
            return;
        }

        System.out.println("Følgende kampregistrering er valgt til redigering: " + playerEntryToEdit);

        boolean editing = true;

        while (editing) {
            System.out.println("\n--- Rediger kampregistrering ---");
            System.out.println("1. Skift kamp type (Træningskamp/Turnering)");
            System.out.println("2. Spillede sets");
            System.out.println("3. Vundne sets");
            System.out.println("4. Turneringsplacering");
            System.out.println("5. Dato for kampregistrering");
            System.out.println("0. Afslut og gem ændringer");

            System.out.print("Vælg venligst hvilken information du ønsker at ændre: ");
            String choice = scanner.nextLine().trim();

            try {
                validator.validateLettersOrNumbersOnly(choice);
            } catch (SmashException e) {
                System.out.println(e.getMessage());
                continue;
            }

            switch (choice) {

                case "1" -> {
                    System.out.println("Nuværende kamp type: " +
                            (playerEntryToEdit.isTournamentMatch() ? "Turnering" : "Træningskamp"));

                    boolean changeType;
                    while (true) {
                        System.out.print("Ændre kamp type? (ja/nej): ");
                        try {
                            changeType = validator.validateYesOrNo(scanner.nextLine());
                            break;
                        } catch (SmashException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    if (changeType) {
                        boolean newType = !playerEntryToEdit.isTournamentMatch();
                        playerEntryToEdit.setTournamentMatch(newType);
                        playerEntryToEdit.setTrainingMatch(!newType);

                        System.out.println("Kamptypen er nu ændret.");
                    }
                }

                case "2" -> {
                    int setsPlayed;
                    while (true) {
                        System.out.print("Indtast nye 'Spillede Sets': ");
                        try {
                            setsPlayed = playerEntryToEdit.isTournamentMatch() ?
                                    validator.validateSetCountTournament(scanner.nextLine()) :
                                    validator.validateSetCount(scanner.nextLine());
                            break;
                        } catch (SmashException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    playerEntryToEdit.setSetsPlayed(setsPlayed);
                    }


                case "3" -> {
                    int setsWon;
                    while (true) {
                        System.out.print("Indtast 'Sets Won' (max " + playerEntryToEdit.getSetsPlayed() + "): ");
                        try {
                            setsWon = validator.validateWonSets(scanner.nextLine(), playerEntryToEdit.getSetsPlayed());
                            break;
                        } catch (SmashException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    playerEntryToEdit.setSetsWon(setsWon);
                }

                case "4" -> {
                    int placement;
                    while (true) {
                        System.out.print("Indtast ny turneringsplacering: ");
                        try {
                            placement = validator.validateInt(scanner.nextLine());
                            if (placement < 1) throw new SmashException("Placering skal være 1 eller højere!");
                            break;

                        } catch (SmashException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    playerEntryToEdit.setTournamentPlacement(placement);
                    playerEntryToEdit.setMatchWinner(placement == 1);
                }

                case "5" -> {
                    LocalDate newDate;
                    while (true) {
                        System.out.print("Indtast dato (YYYY-MM-DD): ");
                        try {
                            newDate = validator.validateNoFutureDate(scanner.nextLine());
                            break;

                        } catch (SmashException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    playerEntryToEdit.setPlayerEntryDate(newDate);
                }

                case "0" -> editing = false;

                default -> System.out.println("Ugyldigt valg!");
            }
        }

        pew.writeToFile(playerEntries);
        System.out.println("Kampregistrering opdateret: " + playerEntryToEdit);
    }


    public void showAllPlayerEntry() {
        PlayerEntryReader per = new PlayerEntryReader();
        ArrayList<PlayerEntry> playerEntries = per.readFromFile();


        if (playerEntries.isEmpty()) {
            System.out.println("Ingen kampregistreringer fundet!");
            return;
        }

        System.out.println("\n--- Alle kampregistreringer for eksisterende medlemmer ---");
        boolean foundAny = false;

        for (PlayerEntry playerEntry : playerEntries) {
            boolean memberExists = false;

            for (Member member : members) {
                if (member.getMemberId() == playerEntry.getMemberId()) {
                    memberExists = true;
                    break;
                }
            }

            if (memberExists) {
                System.out.println(playerEntry);
                foundAny = true;
            }
        }

        if (!foundAny) {
            System.out.println("Ingen kampregistreringer matcher eksisterende medlemmer.");
        }
    }


    public void searchForPlayerEntryByMemberInfo() {
        UserHelperClass userHelper = new UserHelperClass(members);
        PlayerEntryReader per = new PlayerEntryReader();
        ArrayList<PlayerEntry> playerEntries = per.readFromFile();


        Member selectedMember = userHelper.searchForMember();

        if (selectedMember == null) {
            System.out.println("Ingen medlem valgt.");
            return;
        }

        int memberId = selectedMember.getMemberId();
        String memberName = selectedMember.getMemberName();

        ArrayList<PlayerEntry> matchingEntries = new ArrayList<>();

        for (PlayerEntry playerEntry : playerEntries) {
            if (playerEntry.getMemberId() == memberId) {
                matchingEntries.add(playerEntry);
            }
        }


        if (matchingEntries.isEmpty()) {
            System.out.println("Ingen kampregistreringer fundet for medlem: " + memberName);
        } else {
            System.out.println("Kampregistrering for medlem: \n" + memberName);
            for (PlayerEntry entry : matchingEntries) {
                System.out.println(entry);
            }
        }
    }


    public PlayerEntry searchForPlayerEntry() {
        PlayerEntryReader per = new PlayerEntryReader();
        Scanner input = new Scanner(System.in);
        ArrayList<PlayerEntry> playerEntries = per.readFromFile();

        System.out.print("Indtast kampregistrering ID: ");
        String rawInput = input.nextLine();

        int playerEntryId;
        try {
            playerEntryId = validator.validateInt(rawInput);
        } catch (SmashException e) {
            System.out.println(e.getMessage());
            return null;
        }


        for (PlayerEntry entry : playerEntries) {
            if (entry.getPlayerEntryId() == playerEntryId) {
                System.out.println("Følgende kampregistrering er fundet:\n" + entry);
                return entry;
            }
        }

        System.out.println("Ingen kampregistrering fundet med ID: " + playerEntryId);
        return null;
    }


    public PlayerEntry createPlayerEntry(Scanner scanner) {
        FileHandler fileHandler = new FileHandler();
        UserHelperClass userHelper = new UserHelperClass(members);


        int lastUsedPlayerEntryId = 0;
        for (PlayerEntry playerEntry : playerEntries) {
            if (playerEntry.getPlayerEntryId() > lastUsedPlayerEntryId) {
                lastUsedPlayerEntryId = playerEntry.getPlayerEntryId();
            }
        }
        int playerEntryId = lastUsedPlayerEntryId + 1;


        System.out.println("--- Opret Kampregistrering ---");


        Member selectedMember = userHelper.searchForMember();

        if (selectedMember == null) {
            System.out.println("Ingen medlem valgt. Afbryder oprettelse af spiller kampregistrering.");
            return null;
        }

        int memberId = selectedMember.getMemberId();
        String memberName = selectedMember.getMemberName();

        boolean isTournament;
        while (true) {
            System.out.print("Er dette en turnering?: ");
            try {
                isTournament = validator.validateYesOrNo(scanner.nextLine());
                break;
            } catch (SmashException e) {
                System.out.println(e.getMessage());
            }
        }
        boolean isTrainingMatch = !isTournament;


        boolean matchWinner = false;
        int tournamentPlacement = 0;
        if (isTournament) {
            while (true) {
                System.out.println("Indtast medlemmets turnerings placeringen: ");
                try {
                    tournamentPlacement = validator.validateInt(scanner.nextLine());
                    matchWinner = (tournamentPlacement == 1);
                    break;
                } catch (SmashException e) {
                    System.out.println(e.getMessage());
                }
            }
        }


        DisciplineType playerEntryDiscipline = null;
        while (true) {
            System.out.println("Indtast diciplin type:");
            for (DisciplineType d : DisciplineType.values()) {
                System.out.print(d + " - ");
            }
            System.out.println();
            try {
                playerEntryDiscipline = DisciplineType.valueOf(scanner.nextLine().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Ugyldig diciplin type!");
            }
        }

        LocalDate playerEntryDate;
        while (true) {
            System.out.print("Indtast session dato (ÅÅÅÅ-MM-DD): ");
            try {
                playerEntryDate = validator.validateNoFutureDate(scanner.nextLine());
                break;
            } catch (SmashException e) {
                System.out.println(e.getMessage());
            }
        }


        int setsPlayed = 0;
        while (true) {
            System.out.println("Indtast antal spillede sæt: ");
            if (isTournament) {
                try {
                    setsPlayed = validator.validateSetCountTournament(scanner.nextLine());
                    break;
                } catch (SmashException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try {
                    setsPlayed = validator.validateSetCount(scanner.nextLine());
                    break;
                } catch (SmashException e) {
                    System.out.println(e.getMessage());
                }
            }
        }


        int setsWon = 0;
        while (true) {
            System.out.println("Indtast hvor mange sæt spilleren har vundet:  ");
            try {
                setsWon = validator.validateWonSets(scanner.nextLine(), setsPlayed);
                break;
            } catch (SmashException e) {
                System.out.println(e.getMessage());
            }
        }


        PlayerEntry playerEntry = new PlayerEntry(playerEntryId, memberId, memberName, isTrainingMatch, isTournament,
                tournamentPlacement, playerEntryDiscipline, playerEntryDate, setsPlayed, setsWon, matchWinner);


        playerEntries.add(playerEntry);
        fileHandler.savePlayerEntries(playerEntries);

        System.out.println("\nFølgende kampregistrering er oprettet og gemt til CSV: ");
        System.out.println(playerEntry);

        return playerEntry;


    }
}
