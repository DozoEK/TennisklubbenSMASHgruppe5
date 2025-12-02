package SmashTennisClub.MainPackage.ErrorAndValidation;


import java.time.LocalDate;

public class ValidationMethods implements ValidationInterface {

    @Override
    public void validateName(String name) throws SmashException {
        if (name.isEmpty()){
         throw new SmashException("navnet må ikke være tomt.");
        }
        if (!name.matches("[a-z A-ZæøåÆØÅ ]")){
            throw new SmashException("Navnet må kun indeholde bogstaver!");
        }
    }



    @Override
    public LocalDate validateDate(String input) {
        return null;
    }

    @Override
    public int validatePhone(String input) {
        return 0;
    }

    @Override
    public boolean validateBoolean(String input) {
        return false;
    }

}
