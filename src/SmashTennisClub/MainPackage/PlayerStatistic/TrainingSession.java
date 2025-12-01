package SmashTennisClub.MainPackage.PlayerStatistic;

import SmashTennisClub.MainPackage.EnumLists.DisciplineType;
import SmashTennisClub.MainPackage.EnumLists.MatchResult;

import java.sql.Date;
import java.time.LocalDate;

public class TrainingSession {

    int trainingSessionId;
    DisciplineType disciplineTypeForTraining;
    LocalDate dateOfTraining;
    int memberId;
    String memberName;
    int amountOfWonSets;
    MatchResult trainingResult;


    public TrainingSession(int trainingSessionId, DisciplineType disciplineTypeForTraining, LocalDate dateOfTraining,
                           int memberId, String memberName, int amountOfWonSets, MatchResult trainingResult) {

        this.trainingSessionId = trainingSessionId;
        this.disciplineTypeForTraining = disciplineTypeForTraining;
        this.dateOfTraining = dateOfTraining;
        this.memberId = memberId;
        this.memberName = memberName;
        this.amountOfWonSets = amountOfWonSets;
        this.trainingResult = trainingResult;
    }

    public int getTrainingSessionId() { return trainingSessionId; }
    public String GetMemberName() {return memberName;}
    public int getMemberId() { return memberId; }
    public DisciplineType getDisciplineTypeForTraining() { return disciplineTypeForTraining; }
    public LocalDate getDateOfTraining() { return dateOfTraining; }

    public int getAmountOfWonSets() { return amountOfWonSets; }
    public void setAmountOfWonSets(int amountOfWonSets) {
        this.amountOfWonSets = amountOfWonSets;
    }
    public MatchResult getTrainingResult() { return trainingResult; }
    public void setTrainingResult(MatchResult trainingResult) {
        this.trainingResult = trainingResult;
    }

    @Override
    public String toString() {
        return "TrainingSession {" + "trainingId=" + trainingSessionId + ", member=" + memberName + " (" + memberId + ")"
                + ", discipline=" + disciplineTypeForTraining + ", date=" + dateOfTraining + ", won sets=" + amountOfWonSets +
                ", result=" + trainingResult + "}";
    }
}
