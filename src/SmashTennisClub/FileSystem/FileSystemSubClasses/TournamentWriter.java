package SmashTennisClub.FileSystem.FileSystemSubClasses;

import SmashTennisClub.FileSystem.SuperWriter;
import SmashTennisClub.MainPackage.PlayerStatistic.Tournament;

public class TournamentWriter extends SuperWriter<Tournament> {


    @Override
    protected String getCSVFilePathWriter() {
        return "CSVFilesLib/Tournament_index.csv";
    }

    @Override
    protected String[] objectToParts(Tournament object) {
        String[] parts = new String[8];

        parts[0] = Integer.toString(object.getTournamentId());
        parts[1] = object.getTournamentName();
        parts[2] = String.valueOf((object.getDisciplineTypeForTournament()));
        parts[3] = (object.getDateOfTournament() == null) ? "" : object.getDateOfTournament().toString();
        parts[4] = String.valueOf(object.getMemberId());
        parts[5] = object.getMemberName();
        parts[6] = String.valueOf(object.getAmountOfWonSets());
        parts[7] = String.valueOf(object.getTournamentPlacement());


        return parts;
    }
}
