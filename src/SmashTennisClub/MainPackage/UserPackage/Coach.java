package SmashTennisClub.MainPackage.UserPackage;

import SmashTennisClub.FileSystem.FileHandler;
import SmashTennisClub.FileSystem.FileSystemSubClasses.MemberReader;
import SmashTennisClub.FileSystem.FileSystemSubClasses.PlayerEntryReader;
import SmashTennisClub.FileSystem.FileSystemSubClasses.PlayerEntryWriter;
import SmashTennisClub.MainPackage.EnumLists.DisciplineType;
import SmashTennisClub.MainPackage.MembershipTypes.Member;
import SmashTennisClub.MainPackage.PlayerStatistic.PlayerEntry;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Coach {


    //TODO void showTop5() {}
    //TODO void SeniorTop5() {}(den viser top 5 for alle 3 discipliner = 15 spillere)
    // TODO void juniorTop5() {}



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
        UserHelperClass userHelper = new UserHelperClass();
        Scanner input = new Scanner(System.in);
        ArrayList<PlayerEntry> playerEntries = per.readFromFile();


        Member selectedMember = userHelper.searchForMember();
        int playerEntryId = selectedMember.getMemberId();

        PlayerEntry foundEntry = null;
        for (PlayerEntry playerEntry : playerEntries) {
            if (playerEntry.getPlayerEntryId() == playerEntryId) {
                foundEntry = playerEntry;
            }
        }

        if (foundEntry == null) {
            System.out.println("Ingen entry fundet med ID: " + playerEntryId);
            return;
        }

        System.out.println("Entry til sletning: " + foundEntry);
        System.out.print("Er du sikker på at du vil slette denne? (ja/nej): ");

        String confirm = input.nextLine();

        if (confirm.equalsIgnoreCase("ja")) {
            playerEntries.remove(foundEntry);
            System.out.println("Entry er nu slettet!\n");
            pew.writeToFile(playerEntries);
        } else {
            System.out.println("Sletning afbrudt.");
        }
    }



    public void editPlayerEntry() {
        PlayerEntryReader per = new PlayerEntryReader();
        PlayerEntryWriter pew = new PlayerEntryWriter();
        ArrayList<PlayerEntry> playerEntries = per.readFromFile();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Indtast playerEntry-ID på entry der skal redigeres: ");
        int playerEntryId = scanner.nextInt();
        scanner.nextLine();


        PlayerEntry playerEntryToEdit = null;

        for (PlayerEntry playerEntry : playerEntries) {
            if (playerEntry.getPlayerEntryId() == playerEntryId) {
                playerEntryToEdit = playerEntry;
            }
        }

        if (playerEntryToEdit == null) {
            System.out.println("Ingen entry fundet med ID: " + playerEntryId);
            return;
        }

        System.out.println("Følgende medlem er valgt til redigering: " + playerEntryToEdit);


            boolean editingPlayerEntry = true;
            while (editingPlayerEntry) {
                System.out.println("\n--- Rediger PlayerEntry ---");
                System.out.println("1. Skift kamp type (Træningskamp/Turnering)");
                System.out.println("2. Spillede sets");
                System.out.println("3. Vundne sets");
                System.out.println("4. Turneringsplacering");
                System.out.println("5. Dato for PlayerEntry");
                System.out.println("0. Afslut og gem ændringer");

                System.out.print("Vælg venligst hvilken information du ønsker at ændre: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        if (playerEntryToEdit.isTournamentMatch()) {
                            System.out.println("Den nuværende kamp type er: Turnering");
                        } else {
                            System.out.println("Den nuværende kamp type er: Træningskamp");
                        }

                        System.out.print("Ønsker du at ændre kamptypen? (ja/nej): ");
                        String input = scanner.nextLine().toLowerCase();

                        if (input.equals("ja")) {
                            boolean isTournament = !playerEntryToEdit.isTournamentMatch();
                            boolean isTrainingMatch = !playerEntryToEdit.isTrainingMatch();

                            playerEntryToEdit.setTournamentMatch(isTournament);
                            playerEntryToEdit.setTrainingMatch(isTrainingMatch);

                            System.out.println("Kamptypen er nu ændret.");
                        } else if (input.equals("nej")) {
                            System.out.println("Kamptypen forbliver uændret.");
                        } else {
                            System.out.println("Ugyldigt input. Kamptypen forbliver uændret.");
                        }
                    }
                    case 2 -> {
                        System.out.print("Indtast ny 'Sets Played': ");
                        int setsPlayed = scanner.nextInt();
                        scanner.nextLine();
                        playerEntryToEdit.setSetsPlayed(setsPlayed);

                        if (!playerEntryToEdit.isTournamentMatch()) {
                            int maxWinningSets;
                            if (setsPlayed == 3) {
                                maxWinningSets = 2;
                            } else {
                                maxWinningSets = 3;
                            }
                            if (playerEntryToEdit.getSetsWon() > maxWinningSets) {
                                playerEntryToEdit.setSetsWon(maxWinningSets);
                            }
                            playerEntryToEdit.setMatchWinner(playerEntryToEdit.getSetsWon() >= maxWinningSets);
                        }
                    }
                    case 3 -> {
                        int setsPlayed = playerEntryToEdit.getSetsPlayed();
                        int maxWinningSets = (!playerEntryToEdit.isTournamentMatch()) ? ((setsPlayed == 3) ? 2 : 3) : setsPlayed;

                        System.out.print("Indtast ny Sets Won (max " + maxWinningSets + "): ");
                        int setsWon = scanner.nextInt();
                        scanner.nextLine();

                        if (setsWon > maxWinningSets) {
                            System.out.println("Antal vundne sæt kan ikke overstige max (" + maxWinningSets + "). Sætter til max.");
                            setsWon = maxWinningSets;
                        }

                        playerEntryToEdit.setSetsWon(setsWon);

                        if (!playerEntryToEdit.isTournamentMatch()) {
                            playerEntryToEdit.setMatchWinner(setsWon >= maxWinningSets);
                        }
                    }
                    case 4 -> {
                        System.out.print("Indtast ny turnerings rangering: ");
                        int placement = scanner.nextInt();
                        scanner.nextLine();
                        playerEntryToEdit.setTournamentPlacement(placement);

                        if (playerEntryToEdit.isTournamentMatch()) {
                            if (placement == 1) {
                                playerEntryToEdit.setMatchWinner(true);
                            } else {
                                playerEntryToEdit.setMatchWinner(false);
                            }
                        }
                    }
                    case 5 -> {
                        System.out.print("Indtast ny PlayerEntry dato (Format: YYYY-MM-DD): ");
                        String dateStr = scanner.nextLine();
                        playerEntryToEdit.setPlayerEntryDate(java.time.LocalDate.parse(dateStr));
                    }
                    case 0 -> editingPlayerEntry = false;
                    default -> System.out.println("Ugyldigt valg, prøv igen!");
                }
            }

            pew.writeToFile(playerEntries);
            System.out.println("Entry opdateret: " + playerEntryToEdit);
        }





    public void showAllPlayerEntry() {
        PlayerEntryReader per = new PlayerEntryReader();
        ArrayList<PlayerEntry> playerEntries = per.readFromFile();

        if (playerEntries.isEmpty()) {
            System.out.println("Ingen Player Entries fundet!");
            return;
        }

        System.out.println("\n--- Alle Player Entries ---");
        for (PlayerEntry playerEntry : playerEntries) {
            System.out.println(playerEntry);
        }
    }


    public void searchForPlayerEntryByMemberInfo() {
        FileHandler fileHandler = new FileHandler();
        Scanner input = new Scanner(System.in);
        UserHelperClass userHelper = new UserHelperClass();
        PlayerEntryReader per = new PlayerEntryReader();
        ArrayList<PlayerEntry> playerEntries = per.readFromFile();


        Member selectedMember = userHelper.searchForMember();

        int memberId = selectedMember.getMemberId();
        String memberName = selectedMember.getMemberName();

        ArrayList<PlayerEntry> matchingEntries = new ArrayList<>();

        for (PlayerEntry playerEntry : playerEntries) {
            if (playerEntry.getMemberId() == memberId) {
                matchingEntries.add(playerEntry);
            }
        }


        if (matchingEntries.isEmpty()) {
            System.out.println("Ingen PlayerEntries fundet for medlem: " + memberName);
        } else {
            System.out.println("PlayerEntries for medlem: \n" + memberName);
            for (PlayerEntry entry : matchingEntries) {
                System.out.println(entry);
            }
        }
    }



    public PlayerEntry searchForPlayerEntry() {
        PlayerEntryReader per = new PlayerEntryReader();
        Scanner input = new Scanner(System.in);
        ArrayList<PlayerEntry> playerEntries = per.readFromFile();

        System.out.print("Indtast PlayerEntry ID: ");
        int playerEntryId = input.nextInt();
        input.nextLine();

        for (PlayerEntry entry : playerEntries) {
            if (entry.getPlayerEntryId() == playerEntryId) {
                System.out.println("Følgende Entry er fundet:\n" + entry);
                return entry;
            }
        }
        System.out.println("Ingen entry fundet med ID: " + playerEntryId);
        return null;
    }


    public PlayerEntry createPlayerEntry(Scanner scanner) {
        FileHandler fileHandler = new FileHandler();
        PlayerEntryReader playerEntryReader = new PlayerEntryReader();
        ArrayList<PlayerEntry> playerEntries = playerEntryReader.readFromFile();
        UserHelperClass userHelper = new UserHelperClass();


        int lastUsedPlayerEntryId = 0;
        for (PlayerEntry playerEntry : playerEntries) {
            if (playerEntry.getPlayerEntryId() > lastUsedPlayerEntryId) {
                lastUsedPlayerEntryId = playerEntry.getPlayerEntryId();
            }
        }
        int playerEntryId = lastUsedPlayerEntryId + 1;



        System.out.println("--- Opret PlayerEntry ---");


        Member selectedMember = userHelper.searchForMember();

        if (selectedMember == null) {
            System.out.println("Ingen medlem valgt. Afbryder oprettelse af spiller beretning.");
            return null;
        }

        int memberId = selectedMember.getMemberId();
        String memberName = selectedMember.getMemberName();


        System.out.print("Er dette en turnering? (ja/nej): ");
        String tournamentInput = scanner.nextLine().toLowerCase();

        boolean isTournament = tournamentInput.equals("ja");
        boolean isTrainingMatch = tournamentInput.equals("nej");


        boolean matchWinner = false;
        int tournamentPlacement = 0;
        if (isTournament == true) {
            System.out.print("Indtast medlemmets turnerings placeringen: ");
            tournamentPlacement = scanner.nextInt();
            scanner.nextLine();
            matchWinner = (tournamentPlacement == 1);
        }



        DisciplineType playerEntryDiscipline = null;

        System.out.println("Indtast diciplin type:");
        for (DisciplineType d : DisciplineType.values()) {
            System.out.print(d + " - ");
        }
        playerEntryDiscipline = DisciplineType.valueOf(scanner.nextLine().toUpperCase());


        System.out.print("Indtast session dato (ÅÅÅÅ-MM-DD): ");
        String dateInput = scanner.nextLine();
        LocalDate playerEntryDate = LocalDate.parse(dateInput);


        int setsPlayed = 0;
        if (isTournament == true) {
            System.out.println("Indtast antal spillede sæt: ");
            setsPlayed = scanner.nextInt();
            scanner.nextLine();
        } else while (setsPlayed != 3 && setsPlayed != 5) {
            System.out.print("Indtast antal spillede sæt (3 eller 5): ");
            setsPlayed = scanner.nextInt();
            scanner.nextLine();

            if (setsPlayed != 3 && setsPlayed != 5) {
                System.out.println("Antal sæt skal være enten 3 eller 5!");
            }
        }


        int maxWinningSets;
        if (setsPlayed == 3) {
            maxWinningSets = 2;
        } else {
            maxWinningSets = 3;
        }

        int setsWon = -1;
        if (isTournament == true) {
            System.out.println("Indtast hvor mange sæt spilleren har vundet: ");
            setsWon = scanner.nextInt();
            scanner.nextLine();

        } else while (setsWon < 0 || setsWon > maxWinningSets) {
            System.out.print("Indtast antal vundne sæt: ");
            setsWon = scanner.nextInt();
            scanner.nextLine();

            if (setsWon < 0 || setsWon > maxWinningSets) {
                System.out.println("Antal vundne sæt kan ikke være negativt eller større end spillede sæt!");
            }
            matchWinner = setsWon >= maxWinningSets;
        }



        PlayerEntry playerEntry = new PlayerEntry(playerEntryId, memberId, memberName, isTrainingMatch, isTournament,
                tournamentPlacement, playerEntryDiscipline, playerEntryDate, setsPlayed, setsWon, matchWinner);


        playerEntries.add(playerEntry);
        fileHandler.savePlayerEntries(playerEntries);

        System.out.println("\nFølgende PlayerEntry er oprettet og gemt til CSV: ");
        System.out.println(playerEntry);

        return playerEntry;
    }
}
