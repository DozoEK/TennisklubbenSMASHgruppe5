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
    public boolean validateYesOrNo(String input) throws SmashException {
        input = input.toLowerCase();
        if(input.equals("ja")|| input.equals("j")|| input.equals("yes") || input.equals("true"))
            return true;
        if (input.equals("nej")|| input.equals("n")|| input.equals("no") || input.equals("false"))
            return false;

        throw new SmashException("Svar venligst ja eller nej.");
    }

    @Override
    public int validateInt(String input) throws SmashException {
        try{
            return Integer.parseInt(input);
        }catch (NumberFormatException e) {
            throw new SmashException("Input skal være et tal!");
        }
    }

    @Override
    public int validateSetCount(String input) throws SmashException {
        int sets = validateInt(input);
        if (sets !=3 && sets !=5){
            throw new SmashException("Antal af sets skal være 3 eller 5!");
        }
    return sets;
    }

    @Override
    public int validateWonSets(String input, int totalSets) throws SmashException {
        int won = validateInt(input);

        if (won < 0) {
            throw new SmashException("Vundne sæt kan ikke 0 eller negativt tal.");
        }
        if (won > totalSets) {
            throw new SmashException("Vundne sæt kan ikke overstige spillet antal sæt!");
        }
    return won;
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
    public LocalDate validateNoFutureDate(String input) throws SmashException {
        LocalDate date = validateDate(input);

        if (date.isAfter(LocalDate.now())){
            throw new SmashException("Fejl: Datoen må ikke ligge i fremtiden!");
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
