package SmashTennisClub.MainPackage.UserPackage;

import SmashTennisClub.FileSystem.FileHandler;
import SmashTennisClub.FileSystem.FileSystemSubClasses.MatchSessionReader;
import SmashTennisClub.FileSystem.FileSystemSubClasses.PlayerEntryReader;
import SmashTennisClub.MainPackage.EnumLists.DisciplineType;
import SmashTennisClub.MainPackage.MembershipTypes.Member;
import SmashTennisClub.MainPackage.PlayerStatistic.MatchSession;
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


    public MatchSession createMatchSession(Scanner scanner) {
        FileHandler fileHandler = new FileHandler();
        MatchSessionReader matchSessionReader = new MatchSessionReader();
        ArrayList<MatchSession> matchSessions = matchSessionReader.readFromFile();
        UserHelperClass userHelper = new UserHelperClass();


        int lastUsedSessionId = 0;
        for (MatchSession session : matchSessions) {
            if (session.getSessionId() > lastUsedSessionId) {
                lastUsedSessionId = session.getSessionId();
            }
        }
        int sessionId = lastUsedSessionId + 1;


        System.out.println("--- Opret match session ---");

        System.out.print("Er dette en turnering? (ja/nej): ");
        String tournamentInput = scanner.nextLine().toLowerCase();

        boolean isTournament = false;
        if (tournamentInput == "ja") {
            isTournament = true;
        } else if (tournamentInput == "nej") {
            isTournament = false;
        }


        int tournamentPlacement = 0;
        if (isTournament) {
            System.out.print("Indtast medlemmets turnerings placeringen: ");
            tournamentPlacement = scanner.nextInt();
            scanner.nextLine();
        }


        DisciplineType sessionDiscipline = null;

        System.out.println("Indtast diciplin type:");
        for (DisciplineType d : DisciplineType.values()) {
            System.out.print(d + " - ");
        }
        sessionDiscipline = DisciplineType.valueOf(scanner.nextLine().toUpperCase());

        int requiredPlayers = 0;
        if (sessionDiscipline == DisciplineType.SINGLE && isTournament) {
            requiredPlayers = 1;
        } else if (sessionDiscipline == DisciplineType.DOUBLE || sessionDiscipline == DisciplineType.MIXED_DOUBLE) {
            requiredPlayers = 4;
        } else if (sessionDiscipline == DisciplineType.SINGLE && !isTournament)
            requiredPlayers = 2;


        System.out.print("Indtast session dato (ÅÅÅÅ-MM-DD): ");
        String dateInput = scanner.nextLine();
        LocalDate sessionDate = LocalDate.parse(dateInput);


        ArrayList<PlayerEntry> playerEntries = new ArrayList<>();
        System.out.println("\nOpret " + requiredPlayers + " antal spiller beretning(er): ");

        for (int i = 0; i < requiredPlayers; i++) {
            System.out.println("Indtast oplysninger for spiller nummer "+ (i + 1) + "ud af " + requiredPlayers + ": ");
            PlayerEntry playerEntry = createPlayerEntry(scanner);

            if (playerEntry == null) {
                System.out.println("Fejl ved oprettelse af spiller beretning. Afbryder oprettelse af match session.");
                return null;
            }
            playerEntries.add(playerEntry);
        }


        MatchSession matchSession = new MatchSession(sessionId, isTournament, tournamentPlacement,
                sessionDiscipline, sessionDate, playerEntries);

        matchSessions.add(matchSession);
        fileHandler.saveMatchSessions(matchSessions);

        System.out.println("\nFølgende MatchSession er oprettet og gemt til CSV: ");
        System.out.println(matchSession);

        return matchSession;
    }

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
        if (setsPlayed == 3) {
            maxWinningSets = 2;
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
        fileHandler.savePlayerEntries(playerEntries);

        System.out.println("\nFølgende PlayerEntry er oprettet og gemt til CSV: ");
        System.out.println(playerEntry);

        return playerEntry;
    }
}
