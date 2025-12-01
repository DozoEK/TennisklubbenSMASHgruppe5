package SmashTennisClub.FileSystem.FileSystemSubClasses;

import SmashTennisClub.FileSystem.SuperReader;
import SmashTennisClub.MainPackage.EnumLists.DisciplineType;
import SmashTennisClub.MainPackage.PlayerStatistic.MatchSession;
import SmashTennisClub.MainPackage.PlayerStatistic.PlayerEntry;

import java.time.LocalDate;
import java.util.ArrayList;

public class MatchSessionReader extends SuperReader<MatchSession> {


    @Override
    protected String getCSVFilePathReader() {
        return "CSVFilesLib/Match_Session_Index.csv";
    }

    @Override
    protected int setMaxIndexLength() {
        return 7;
    }

    @Override
    protected MatchSession parseAttributes(String[] parts) {

        int sessionId = Integer.parseInt(parts[0]);
        boolean isTournament = Boolean.parseBoolean(parts[1]);
        int tournamentPlacement = Integer.parseInt(parts[2]);
        DisciplineType discipline = DisciplineType.valueOf(parts[3]);
        LocalDate date = LocalDate.parse(parts[4]);

        int playerCount = Integer.parseInt(parts[5]);
        String playersCombined = parts[6];

        ArrayList<PlayerEntry> playerList = new ArrayList<>();

        // split players: "p1|p2|p3"
        String[] playerStrings = playersCombined.split("\\|");


        for (int i = 0; i < playerStrings.length; i++) {
            String[] p = playerStrings[i].split(",");

            int memberId = Integer.parseInt(p[0]);
            String memberName = p[1];
            int setsPlayed = Integer.parseInt(p[2]);
            int setsWon = Integer.parseInt(p[3]);
            boolean isWinner = Boolean.parseBoolean(p[4]);

            PlayerEntry entry = new PlayerEntry(memberId, memberName, setsPlayed, setsWon, isWinner);
            playerList.add(entry);
        }


        if (playerList.size() != playerCount) {
            System.out.println("Warning: player count does not match CSV value.");
        }

        return new MatchSession(sessionId, isTournament, tournamentPlacement,
                discipline, date, playerList);
    }
}
