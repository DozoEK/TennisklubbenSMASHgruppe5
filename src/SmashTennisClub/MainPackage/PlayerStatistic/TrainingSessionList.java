package SmashTennisClub.MainPackage.PlayerStatistic;

import SmashTennisClub.MainPackage.EnumLists.MatchResult;

import java.util.ArrayList;

public class TrainingSessionList {
    private ArrayList<TrainingSession> trainingSessions = new ArrayList<>();

    public void addToStatistic(TrainingSession session){
        trainingSessions.add(session);
        System.out.println("Tilføj træningssessionen for: " + session.GetMemberName());
    }

    // Returnerer alle registrerede træningssessioner
    public ArrayList<TrainingSession> getTrainingSessions() {
        return trainingSessions;
    }

    //Bruges når man vil starte en ny registrering

    public void clear() {
        trainingSessions.clear();
    }

//    public void registrerResultat(int trainingId, MatchResult newResult, int amountOfWonSets) {
//        for (TrainingSession session : trainingSessions) {
//            if (session.getTrainingId() == trainingId) {
//
//                session.setTrainingResult(newResult);
//                session.setAmountOfWonSets(amountOfWonSets);
//
//                System.out.println("Resultat registreret for: " + session.getMemberName());
//                return;
//            }
//        }
//
//        System.out.println("Ingen træningssession fundet med ID: " + trainingId);
//    }


}
