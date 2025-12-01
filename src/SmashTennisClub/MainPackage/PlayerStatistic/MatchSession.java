package SmashTennisClub.MainPackage.PlayerStatistic;

import SmashTennisClub.MainPackage.EnumLists.DisciplineType;

import java.time.LocalDate;
import java.util.ArrayList;


public class MatchSession {

    private int sessionId;
    private boolean isTournament;
    private int tournamentPlacement;
    private DisciplineType sessionDiscipline;   // SINGLE / DOUBLE / MIXED_DOUBLE
    private LocalDate sessionDate;
    private ArrayList<PlayerEntry> playerEntries; // ALWAYS size 1, 2 or 4

}
