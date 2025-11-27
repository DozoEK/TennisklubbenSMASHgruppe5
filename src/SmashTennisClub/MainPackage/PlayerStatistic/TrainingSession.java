package SmashTennisClub.MainPackage.PlayerStatistic;

import SmashTennisClub.MainPackage.EnumLists.DisciplineType;
import SmashTennisClub.MainPackage.EnumLists.MatchResult;

import java.sql.Date;

public class TrainingSession {

    int trainingId;
    DisciplineType disciplineTypeForTraining;
    Date dateOfTraining;
    int memberId; //(for alle spillere)
    String memberName;
    int amountOfWonSets;
    MatchResult trainingResult;


    public TrainingSession(int trainingId, DisciplineType disciplineTypeForTraining, Date dateOfTraining,
                           int memberId, String memberName, int amountOfWonSets, MatchResult trainingResult) {

        this.trainingId = trainingId;
        this.disciplineTypeForTraining = disciplineTypeForTraining;
        this.dateOfTraining = dateOfTraining;
        this.memberId = memberId;
        this.memberName = memberName;
        this.amountOfWonSets = amountOfWonSets;
        this.trainingResult = trainingResult;
    }

    public int getTrainingId() { return trainingId; }
    public String GetMemberName() {return memberName;}
    public int getMemberId() { return memberId; }
    public DisciplineType getDisciplineTypeForTraining() { return disciplineTypeForTraining; }
    public Date getDateOfTraining() { return dateOfTraining; }

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
        return "TrainingSession {" + "trainingId=" + trainingId + ", member=" + memberName + " (" + memberId + ")"
                + ", discipline=" + disciplineTypeForTraining + ", date=" + dateOfTraining + ", won sets=" + amountOfWonSets +
                ", result=" + trainingResult + "}";
    }
}
