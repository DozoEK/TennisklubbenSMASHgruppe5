package SmashTennisClub.MainPackage.EnumLists;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class MembershipPricelistTest {

    @Test
    @DisplayName("Junior membership should cost 800.00 kr")
    public void testJuniorPrice() {
        MembershipPricelist junior = MembershipPricelist.JUNIOR;

        double actualPrice = junior.getPrice();

        assertEquals(800.0, actualPrice, 0.01, "junior should cost 800.00kr");

        System.out.println("Test Successful!");
    }

    @Test
    @DisplayName("Senior membership should cost 1500.00 kr")
    public void testSeniorPrice() {
        MembershipPricelist senior = MembershipPricelist.SENIOR;

        double actualPrice = senior.getPrice();

        assertEquals(1500.0, actualPrice, 0.01, "Senior should cost 1500.00kr");

        System.out.println("Test Successful!");
    }

    @Test
    @DisplayName("Senior membership should cost 1125.00 kr")
    public void testPensionistPrice() {
        MembershipPricelist pensionist = MembershipPricelist.PENSIONIST;

        double actualPrice = pensionist.getPrice();

        assertEquals(1125.0, actualPrice, 0.01, "Pensionist should cost 1125.00kr");

        System.out.println("Test Successful!");
    }

    @Test
    @DisplayName("Passive membership should cost 250.00 kr")
    public void testPassivePrice() {
        MembershipPricelist passivt = MembershipPricelist.PASSIVT;

        double actualPrice = passivt.getPrice();

        assertEquals(250.0, actualPrice, 0.01, "Pensionist should cost 250.00kr");

        System.out.println("Test Successful!");
    }
}