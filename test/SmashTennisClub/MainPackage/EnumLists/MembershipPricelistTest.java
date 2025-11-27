package SmashTennisClub.MainPackage.EnumLists;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class MembershipPricelistTest {

    @Test
    @DisplayName("Junior membership koster 800.00 kr")
    public void testJuniorPrice() {
        MembershipPricelist junior = MembershipPricelist.JUNIOR;

        double actualPrice = junior.getPrice();

        assertEquals(800.0, actualPrice, 0.01, "junior koster 800.00kr");

        System.out.println("Test lykkedes!");
    }

    @Test
    @DisplayName("Senior membership koster 1500.00 kr")
    public void testSeniorPrice() {
        MembershipPricelist senior = MembershipPricelist.SENIOR;

        double actualPrice = senior.getPrice();

        assertEquals(1500.0, actualPrice, 0.01, "Senior koster 1500.00kr");

        System.out.println("Test lykkedes!");
    }

    @Test
    @DisplayName("Pensionist membership koster 1125.00 kr")
    public void testPensionistPrice() {
        MembershipPricelist pensionist = MembershipPricelist.PENSIONIST;

        double actualPrice = pensionist.getPrice();

        assertEquals(1125.0, actualPrice, 0.01, "Pensionist koster 1125.00kr");

        System.out.println("Test lykkedes!");
    }

    @Test
    @DisplayName("Passive membership koster 250.00 kr")
    public void testPassivePrice() {
        MembershipPricelist passivt = MembershipPricelist.PASSIVT;

        double actualPrice = passivt.getPrice();

        assertEquals(250.0, actualPrice, 0.01, "Passivt koster 250.00kr");

        System.out.println("Test lykkedes!");
    }
}