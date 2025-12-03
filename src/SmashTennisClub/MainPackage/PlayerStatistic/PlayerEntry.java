package SmashTennisClub.MainPackage.PlayerStatistic;

import SmashTennisClub.MainPackage.EnumLists.DisciplineType;

import java.time.LocalDate;

public class PlayerEntry {

    private int playerEntryId;
    private int memberId;
    private String memberName;
    private boolean isTrainingMatch;
    private boolean isTournamentMatch;
    private int tournamentPlacement;
    private DisciplineType playerEntryDiscipline;
    private LocalDate playerEntryDate;
    private int setsPlayed;
    private int setsWon;
    private boolean matchWinner;


    public PlayerEntry(int playerEntryId, int memberId, String memberName, boolean isTrainingMatch, boolean isTournamentMatch,
                       int tournamentPlacement, DisciplineType playerEntryDiscipline,
                       LocalDate playerEntryDate, int setsPlayed, int setsWon, boolean matchWinner) {
        this.playerEntryId = playerEntryId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.isTrainingMatch = isTrainingMatch;
        this.isTournamentMatch = isTournamentMatch;
        this.tournamentPlacement = tournamentPlacement;
        this.playerEntryDate = playerEntryDate;
        this.setsPlayed = setsPlayed;
        this.setsWon = setsWon;
        this.playerEntryDiscipline = playerEntryDiscipline;
        this.matchWinner = matchWinner;
    }


    //Getters
    public int getPlayerEntryId() {
        return playerEntryId;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public boolean isTrainingMatch() {
        return isTrainingMatch;
    }

    public boolean isTournamentMatch() {
        return isTournamentMatch;
    }

    public int getTournamentPlacement() {
        return tournamentPlacement;
    }

    public DisciplineType getPlayerEntryDiscipline() {
        return playerEntryDiscipline;
    }

    public LocalDate getPlayerEntryDate() {
        return playerEntryDate;
    }

    public int getSetsPlayed() {
        return setsPlayed;
    }

    public int getSetsWon() {
        return setsWon;
    }

    public boolean isMatchWinner() {
        return matchWinner;
    }



    //setters
    public void setPlayerEntryId(int playerEntryId) {
        this.playerEntryId = playerEntryId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setTrainingMatch(boolean trainingMatch) {
        isTrainingMatch = trainingMatch;
    }

    public void setTournamentMatch(boolean tournamentMatch) {
        isTournamentMatch = tournamentMatch;
    }

    public void setTournamentPlacement(int tournamentPlacement) {
        this.tournamentPlacement = tournamentPlacement;
    }

    public void setPlayerEntryDiscipline(DisciplineType playerEntryDiscipline) {
        this.playerEntryDiscipline = playerEntryDiscipline;
    }

    public void setPlayerEntryDate(LocalDate playerEntryDate) {
        this.playerEntryDate = playerEntryDate;
    }

    public void setSetsPlayed(int setsPlayed) {
        this.setsPlayed = setsPlayed;
    }

    public void setSetsWon(int setsWon) {
        this.setsWon = setsWon;
    }

    public void setMatchWinner(boolean matchWinner) {
        this.matchWinner = matchWinner;
    }


    //toString
    @Override
    public String toString() {
        return String.format(
                "EntryID:%-3d  |  MemberID:%-3d  |  Name:%-20s  |  TrainingMatch:%-3s  |  TournamentMatch:%-3s  |  Placement:%-3d  |  Discipline:%-15s  |  Date:%-10s  |  SetsPlayed:%-3d  |  SetsWon:%-3d  |  Winner:%-3s  |",
                playerEntryId,
                memberId,
                memberName,
                isTrainingMatch ? "Ja" : "Nej",
                isTournamentMatch ? "Ja" : "Nej",
                tournamentPlacement,
                playerEntryDiscipline != null ? playerEntryDiscipline : "-",
                playerEntryDate != null ? playerEntryDate : "-",
                setsPlayed,
                setsWon,
                matchWinner ? "Ja" : "Nej"
        );
    }



}
