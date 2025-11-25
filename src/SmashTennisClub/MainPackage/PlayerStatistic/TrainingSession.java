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
}
