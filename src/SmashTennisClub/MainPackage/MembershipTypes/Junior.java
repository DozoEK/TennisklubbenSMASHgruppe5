package SmashTennisClub.MainPackage.MembershipTypes;

import SmashTennisClub.MainPackage.EnumLists.DisciplineType;
import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MemberType;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;

import java.time.LocalDate;

public class Junior extends Member {

    private final DisciplineType juniorDisciplinType;

    public Junior(int memberId, String name, Gender genderOfMember, LocalDate dateOfBirth,
                  int age, int phoneNumber, Boolean competitivePlayer,
                  MemberType membershipType, MembershipPricelist yearlyMembershipFee,
                  LocalDate yearlyFeeDate, DisciplineType juniorDisciplinType, Boolean activeMembership) {

        super(memberId, name, genderOfMember, dateOfBirth, age, phoneNumber, competitivePlayer,
                membershipType, yearlyMembershipFee, yearlyFeeDate, activeMembership);
        this.juniorDisciplinType = juniorDisciplinType;
    }

    public DisciplineType getJuniorDisciplinType() {
        return juniorDisciplinType;
    }

    @Override
    public String toString() {
        return String.format(
                "ID:%-3d  |  Navn:%-20s  |  Køn:%-6s  |  Født:%-10s  |  Alder:%-3d  |  Tlf:%-8s  |  Konkurrence:%-3s  |  Type:%-19s  |  Kontingent:%-10s  |  Forfald:%-10s  |  Aktiv:%-3s  |  Disciplin:%-12s  |",
                getMemberId(),
                getMemberName(),
                getGenderOfMember(),
                getDateOfBirth(),
                getAge(),
                getPhoneNumber(),
                getCompetitivePlayer() ? "Ja" : "Nej",
                getMembershipType(),
                getYearlyMembershipFee(),
                getYearlyFeeDate(),
                getActiveMembership() ? "Ja" : "Nej",
                juniorDisciplinType
        );
    }





}
