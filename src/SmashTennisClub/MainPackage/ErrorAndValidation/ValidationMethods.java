package SmashTennisClub.MainPackage.ErrorAndValidation;


import java.time.LocalDate;

public class ValidationMethods implements ValidationInterface {

    @Override
    public void validateName(String name) throws SmashException {
        if (name.isEmpty()){
         throw new SmashException("navnet må ikke være tomt.");
        }
        if (!name.matches("[a-z A-ZæøåÆØÅ ]+")){
            throw new SmashException("Navnet må kun indeholde bogstaver!");
        }
    }

//    @Override
//    public int validateMemberIdSearch (String input) throws SmashException{}


    @Override
    public LocalDate validateDate(String input) throws SmashException {
        try {
            return LocalDate.parse(input);
        } catch (Exception e) {
            throw new SmashException("Ugyldig dato. \n Format: YYYY-MM-DD");
        }
    }

    @Override
    public LocalDate validateDateTime(String input) throws SmashException {
        LocalDate date = validateDate(input);

        LocalDate hundredYearsMax = LocalDate.now().minusYears(100);
        if (date.isBefore(hundredYearsMax)){
            throw new SmashException("Datoen kan max være 100 år gammel");
        }
        return date;
    }


    @Override
    public int validatePhone(String input) throws SmashException {
        if (!input.matches("[0-9]+")){
            throw new SmashException("Telefonnummer må kun bestå af tal!");
        }
        return Integer.parseInt(input);
    }


    @Override
    public int validatePhoneNumberLength(String input) throws SmashException {
        if (input.length() != 8) {
            throw new SmashException("Telefonnummer skal bestå af 8 tal.");
        }
        return validatePhone(input);
    }


    @Override
    public boolean validateBoolean(String input) throws SmashException {
        if (input.equalsIgnoreCase("true")) return true;
        if (input.equalsIgnoreCase("false")) return false;

        throw new SmashException("Skriv venligst True eller False.");
    }

    @Override
    public int validateAge(String input) throws SmashException {
        try {
            int age = Integer.parseInt(input);
            if (age <1 || age > 120){
                throw new SmashException("Alderen skal være mellem 1 og 120 år");
            }
            return age;
        } catch (NumberFormatException e){
            throw new SmashException("Alder skal være et tal.");
        }
    }


}
