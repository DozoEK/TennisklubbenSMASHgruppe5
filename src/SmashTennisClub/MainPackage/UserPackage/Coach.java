package SmashTennisClub.MainPackage.UserPackage;

import SmashTennisClub.FileSystem.FileHandler;
import SmashTennisClub.FileSystem.FileSystemSubClasses.PlayerEntryReader;
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
    // TODO void searchForCompetetivePlayer() {} (næsten samme metode som Formanden har)
    //TODO void showAllCompetetivePlayers() {} //(næsten samme metode som Formanden har)


    //TODO deletePlayerEntry()
    //TODO editPlayerEntry()
    //TODO showAllPlayerEntry()
    //TODO searchForPlayerEntry()



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
