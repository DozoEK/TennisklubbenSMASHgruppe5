package SmashTennisClub.MainPackage.UserPackage;

import SmashTennisClub.FileSystem.FileHandler;
import SmashTennisClub.FileSystem.FileSystemSubClasses.PlayerEntryReader;
import SmashTennisClub.MainPackage.MembershipTypes.Member;
import SmashTennisClub.MainPackage.PlayerStatistic.PlayerEntry;

import java.util.ArrayList;
import java.util.Scanner;

public class Coach {


    void showTop5() {}
    void SeniorTop5() {} //(den viser top 5 for alle 3 discipliner = 15 spillere)
    void juniorTop5() {}
    void searchForCompetetivePlayer() {} //(næsten samme metode som Formanden har)
    void showAllCompetetivePlayers() {} //(næsten samme metode som Formanden har)


    //TODO createMatchSession()
    //TODO deleteMatchSession()
    //TODO editMatchSession()
    //TODO showAllMatchSession()
    //TODO searchForMatchSession()

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



        System.out.println("--- Opret spiller beretning ---");


        Member selectedMember = userHelper.searchForMember();

        if (selectedMember == null) {
            System.out.println("Ingen medlem valgt. Afbryder oprettelse af spiller beretning.");
            return null;
        }

        int memberId = selectedMember.getMemberId();
        String memberName = selectedMember.getMemberName();


        int setsPlayed = 0;
        while (setsPlayed != 3 && setsPlayed != 5) {
            System.out.print("Indtast antal spillede sæt (3 eller 5): ");
            setsPlayed = scanner.nextInt();
            scanner.nextLine();

            if (setsPlayed != 3 && setsPlayed != 5) {
                System.out.println("Antal sæt skal være enten 3 eller 5!");
            }
        }

        int maxWinningSets;
            if (setsPlayed == 3) { maxWinningSets = 2;
            } else {
                maxWinningSets = 3;
            }

        int setsWon = -1;
        while (setsWon < 0 || setsWon > setsPlayed) {
            System.out.print("Indtast antal vundne sæt: ");
            setsWon = scanner.nextInt();
            scanner.nextLine();

            if (setsWon < 0 || setsWon > setsPlayed) {
                System.out.println("Antal vundne sæt kan ikke være negativt eller større end spillede sæt!");
            }
        }

        boolean matchWinner = setsWon >= maxWinningSets;


        PlayerEntry playerEntry = new PlayerEntry(playerEntryId, memberId, memberName, setsPlayed,
                setsWon, matchWinner);


        playerEntries.add(playerEntry);
        fileHandler.savePlayerEntry(playerEntries);

        System.out.println("\nFølgende PlayerEntry er oprettet og gemt til CSV: ");
        System.out.println(playerEntry);

        return playerEntry;
    }

}
