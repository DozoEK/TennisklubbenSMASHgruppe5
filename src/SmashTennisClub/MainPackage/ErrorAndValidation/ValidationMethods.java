package SmashTennisClub.MainPackage.ErrorAndValidation;

import SmashTennisClub.MainPackage.ErrorAndValidation.CustomExceptions.*;

import java.time.LocalDate;

public class ValidationMethods implements ValidationInterface {

    @Override
    public void validateName(String name) throws InputException {
        if (name.isEmpty()) {
            throw new InputException("Navnet må ikke være tomt.");
        }
        if (!name.matches("[a-zA-ZæøåÆØÅ ]+")) {
            throw new InputException("Navnet må kun indeholde bogstaver!");
        }
    }

    @Override
    public void validateLettersOrNumbersOnly(String input) throws InputException {
        if (input.isEmpty()) {
            throw new InputException("Søgning må ikke være tom.");
        }
        if (!input.matches("[a-zA-ZæøåÆØÅ0-9 ]+")) {
            throw new InputException("Input må kun indeholde bogstaver eller tal!");
        }
    }

    @Override
    public LocalDate validateDate(String input) throws DateException {
        try {
            return LocalDate.parse(input);
        } catch (Exception e) {
            throw new DateException("Ugyldig dato. Format: YYYY-MM-DD");
        }
    }

    @Override
    public LocalDate validateDateTime(String input) throws DateException {
        LocalDate date = validateDate(input);

        LocalDate hundredYearsMax = LocalDate.now().minusYears(100);
        if (date.isBefore(hundredYearsMax)) {
            throw new DateException("Datoen kan maksimalt være 100 år gammel");
        }
        return date;
    }

    @Override
    public LocalDate validateNoFutureDate(String input) throws DateException {
        LocalDate date = validateDate(input);

        if (date.isAfter(LocalDate.now())) {
            throw new DateException("Datoen må ikke ligge i fremtiden!");
        }
        return date;
    }

    @Override
    public int validatePhone(String input) throws ContactException {
        if (!input.matches("[0-9]+")) {
            throw new ContactException("Telefonnummer må kun bestå af tal!");
        }
        return Integer.parseInt(input);
    }

    @Override
    public int validatePhoneNumberLength(String input) throws ContactException {
        if (input.length() != 8) {
            throw new ContactException("Telefonnummer skal bestå af 8 tal.");
        }
        return validatePhone(input);
    }

    @Override
    public boolean validateBoolean(String input) throws InputException {
        if (input.equalsIgnoreCase("true")) return true;
        if (input.equalsIgnoreCase("false")) return false;

        throw new InputException("Skriv venligst True eller False.");
    }

    @Override
    public boolean validateYesOrNo(String input) throws InputException {
        input = input.toLowerCase();
        if (input.equals("ja") || input.equals("j") || input.equals("yes") || input.equals("true"))
            return true;
        if (input.equals("nej") || input.equals("n") || input.equals("no") || input.equals("false"))
            return false;

        throw new InputException("Svar venligst ja eller nej.");
    }

    @Override
    public int validateInt(String input) throws InputException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InputException("Input skal være et tal!");
        }
    }

    @Override
    public int validateAge(String input) throws MemberException {
        try {
            int age = Integer.parseInt(input);
            if (age < 1 || age > 120) {
                throw new MemberException("Alderen skal være mellem 1 og 120 år");
            }
            return age;
        } catch (NumberFormatException e) {
            throw new MemberException("Alder skal være et tal.");
        }
    }

    @Override
    public int validateSetCount(String input) throws TournamentException, InputException {
        int sets = validateInt(input);
        if (sets != 3 && sets != 5) {
            throw new TournamentException("Antal sæt skal være 3 eller 5!");
        }
        return sets;
    }

    @Override
    public int validateSetCountTournament(String input) throws TournamentException, InputException {
        int sets = validateInt(input);
        if (sets <= 0 || sets > 1000) {
            throw new TournamentException("Antal sets må ikke være negativ eller over 1000!");
        }
        return sets;
    }

    @Override
    public int validateWonSets(String input, int totalSets) throws TournamentException, InputException {
        int won = validateInt(input);

        if (won < 0) {
            throw new TournamentException("Vundne sæt kan ikke være negativt.");
        }
        if (won > totalSets) {
            throw new TournamentException("Vundne sæt kan ikke overstige spillet antal sæt!");
        }
        return won;
    }
}
