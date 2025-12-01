package SmashTennisClub.FileSystem.FileSystemSubClasses;

import SmashTennisClub.FileSystem.SuperWriter;
import SmashTennisClub.MainPackage.PlayerStatistic.MatchSession;
import SmashTennisClub.MainPackage.PlayerStatistic.PlayerEntry;

import java.util.ArrayList;

public class MatchSessionWriter extends SuperWriter<MatchSession> {

    @Override
    protected String getCSVFilePathWriter() {
        return "CSVFilesLib/Match_Session_Index.csv";
    }

    @Override
    protected String[] objectToParts(MatchSession object) {

        ArrayList<PlayerEntry> sessionList = object.getPlayerEntries();
        String playerData = "";

        for (int i = 0; i < sessionList.size(); i++) {
            PlayerEntry p = sessionList.get(i);

            playerData += p.getMemberId()
                    + "," + p.getMemberName()
                    + "," + p.getSetsPlayed()
                    + "," + p.getSetsWon()
                    + "," + p.isMatchWinner();

            if (i < sessionList.size() - 1) {
                playerData += "|";
            }
        }


        String[] parts = new String[7];
        parts[0] = String.valueOf(object.getSessionId());
        parts[1] = String.valueOf(object.isTournament());
        parts[2] = String.valueOf(object.getTournamentPlacement());
        parts[3] = object.getSessionDiscipline().toString();
        parts[4] = object.getSessionDate().toString();
        parts[5] = String.valueOf(sessionList.size());
        parts[6] = playerData;

        return parts;
    }

}
