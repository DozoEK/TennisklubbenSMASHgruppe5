package SmashTennisClub.FileSystem;

 public class SmashException extends Exception {
    public SmashException(String message) {
        super(message);
    }
}
 class setText {
    public static void main(String[] args) {
        try {
            throw new SmashException("Custom exeption");
        } catch (SmashException ex) {
            System.out.println("fanget!");
            System.out.println(ex.getMessage());
        }
    }
}