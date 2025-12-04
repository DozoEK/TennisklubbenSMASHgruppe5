package SmashTennisClub.MainPackage.MembershipTypes;

import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MemberType;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MemberTest {

    private Member member;

    @BeforeEach
    public void setUp() {

        member = new Member(
                1,
                "Test Testperson",
                Gender.MALE,
                LocalDate.of(1990, 5, 15),
                34,
                12345678,
                false,
                MemberType.RECREATIONALPLAYER,
                MembershipPricelist.SENIOR,
                LocalDate.now().plusYears(1),
                true
        );
    }

    @Test
    @DisplayName("tester at ID er korrekt")
    public void testGetMemberId() {
        assertEquals(1, member.getMemberId(), "medlems id skal være 1");
    }

    @Test
    @DisplayName("Test at medlem har korrekt fødselsdato")
    public void testGetDateOfBirth() {
        assertEquals(LocalDate.of(1990, 5, 15), member.getDateOfBirth(),
                "Fødselsdato skal være 1990-05-15");
}

    @Test
    @DisplayName("Test at alder beregnes korrekt")
    public void testGetAge() {
        int expectedAge = LocalDate.now().getYear() - 1990;
        // Juster hvis fødselsdagen ikke er nået endnu i år
        if (LocalDate.now().isBefore(LocalDate.of(LocalDate.now().getYear(), 5, 15))) {
            expectedAge--;
        }
        assertEquals(expectedAge, member.getAge(), "Alder skal beregnes korrekt fra fødselsdato");
    }

    @Test
    @DisplayName("Test at medlemskabstype er korrekt")
    public void testGetMembershipType() {
        assertEquals(MemberType.RECREATIONALPLAYER, member.getMembershipType(),
                "Medlemskabstype skal være RECREATIONALPLAYER");
    }

    @Test
    @DisplayName("Test at yearly fee er korrekt")
    public void testGetYearlyMembershipFee() {
        assertEquals(MembershipPricelist.SENIOR, member.getYearlyMembershipFee(),
                "Yearly membership fee skal være SENIOR");
    }

    @Test
    @DisplayName("Test at updateAge opdaterer alderen korrekt")
    public void testUpdateAge() {
        // Opret et medlem med en kendt fødselsdato
        Member testMember = new Member(
                2,
                "Alder Test",
                Gender.FEMALE,
                LocalDate.of(2000, 1, 1),
                0, // Starter med forkert alder
                87654321,
                false,
                MemberType.RECREATIONALPLAYER,
                MembershipPricelist.SENIOR,
                LocalDate.now().plusYears(1),
                true
        );

        testMember.updateAge();
        int expectedAge = LocalDate.now().getYear() - 2000;
        if (LocalDate.now().isBefore(LocalDate.of(LocalDate.now().getYear(), 1, 1))) {
            expectedAge--;
        }

        assertEquals(expectedAge, testMember.getAge(),
                "Alder skal opdateres korrekt baseret på fødselsdato");
    }
}