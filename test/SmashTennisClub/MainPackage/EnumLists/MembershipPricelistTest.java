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

    }

