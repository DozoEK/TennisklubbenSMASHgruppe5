package SmashTennisClub.MainPackage.ErrorAndValidation;


import java.time.LocalDate;

public interface ValidationInterface {

 void validateName(String name) throws SmashException;

 LocalDate validateDate(String input) throws SmashException;

 int validatePhone(String input) throws SmashException;

 boolean validateBoolean(String input) throws SmashException;


}
