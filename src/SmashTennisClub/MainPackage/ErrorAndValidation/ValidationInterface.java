package SmashTennisClub.MainPackage.ErrorAndValidation;


import java.time.LocalDate;

public interface ValidationInterface {

 void validateName(String name) throws SmashException;

// int validateMemberIdSearch (String input) throws SmashException;

 LocalDate validateDate(String input) throws SmashException;

 LocalDate validateDateTime (String input) throws SmashException;

 LocalDate validateNoFutureDate (String input) throws SmashException;

 int validatePhone(String input) throws SmashException;

 int validatePhoneNumberLength(String input) throws SmashException;

 boolean validateBoolean(String input) throws SmashException;
 boolean validateYesOrNo(String input) throws SmashException;

 int validateAge(String input) throws SmashException;

 int validateInt (String input) throws SmashException;

 int validateSetCount(String input) throws SmashException;
 int validateWonSets (String input, int totalSets) throws SmashException;










}
