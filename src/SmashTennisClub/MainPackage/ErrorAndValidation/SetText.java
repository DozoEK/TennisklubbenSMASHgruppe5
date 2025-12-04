package SmashTennisClub.MainPackage.ErrorAndValidation;


import SmashTennisClub.MainPackage.ErrorAndValidation.CustomExceptions.SmashException;

class SetText {
    public static void main(String[] args) {
        try {
            throw new SmashException("Custom exeption");
        } catch (SmashException ex) {
            System.out.println("fanget!");
            System.out.println(ex.getMessage());
        }
    }
}