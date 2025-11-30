package SmashTennisClub.MainPackage.PlayerStatistic;

import SmashTennisClub.MainPackage.EnumLists.DisciplineType;


import java.time.LocalDate;

public class Tournament {

    int tournamentId;
    String tournamentName;
    DisciplineType disciplineTypeForTournament;
    LocalDate dateOfTournament;
    int memberId;
    String memberName;
    int amountOfWonSets;
    int tournamentPlacement;



    public Tournament(int memberId, int tournamentId, String tournamentName, DisciplineType disciplineTypeForTournament, LocalDate dateOfTournament, String memberName, int amountOfWonSets, int tournamentPlacement) {
        this.memberId = memberId;
        this.tournamentId = tournamentId;
        this.tournamentName = tournamentName;
        this.disciplineTypeForTournament = disciplineTypeForTournament;
        this.dateOfTournament = dateOfTournament;
        this.memberName = memberName;
        this.amountOfWonSets = amountOfWonSets;
        this.tournamentPlacement = tournamentPlacement;
    }




    //getters
    public int getTournamentId() {
        return tournamentId;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public DisciplineType getDisciplineTypeForTournament() {
        return disciplineTypeForTournament;
    }

    public LocalDate getDateOfTournament() {
        return dateOfTournament;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public int getAmountOfWonSets() {
        return amountOfWonSets;
    }

    public int getTournamentPlacement() {
        return tournamentPlacement;
    }


    //Setters
    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public void setDisciplineTypeForTournament(DisciplineType disciplineTypeForTournament) {
        this.disciplineTypeForTournament = disciplineTypeForTournament;
    }

    public void setDateOfTournament(LocalDate dateOfTournament) {
        this.dateOfTournament = dateOfTournament;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setAmountOfWonSets(int amountOfWonSets) {
        this.amountOfWonSets = amountOfWonSets;
    }

    public void setTournamentPlacement(int tournamentPlacement) {
        this.tournamentPlacement = tournamentPlacement;
    }
}