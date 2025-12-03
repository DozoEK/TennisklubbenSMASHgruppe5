package SmashTennisClub.MainPackage.MembershipTypes;

import SmashTennisClub.MainPackage.EnumLists.DisciplineType;
import SmashTennisClub.MainPackage.EnumLists.Gender;
import SmashTennisClub.MainPackage.EnumLists.MemberType;
import SmashTennisClub.MainPackage.EnumLists.MembershipPricelist;

import java.time.LocalDate;

public class Junior extends Member {

    private DisciplineType juniorDisciplinType;

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

    //setter
    public void setJuniorDisciplinType(DisciplineType juniorDisciplinType) {
        this.juniorDisciplinType = juniorDisciplinType;
    }

    @Override
    public String toString() {
        return String.format(
                "MemberID:%-3d  |  Name:%-20s  |  Gender:%-6s  |  DOB:%-10s  |  Age:%-3d  |  Phone:%-8s  |  Competitive:%-3s  |  Type:%-19s  |  Membership:%-10s  |  DueDate:%-10s  |  Active:%-3s  |  Discipline:%-12s  |",
                getMemberId(),
                getMemberName(),
                getGenderOfMember(),
                getDateOfBirth(),
                getAge(),
                getPhoneNumber(),
                getCompetitivePlayer() ? "Yes" : "No",
                getMembershipType(),
                getYearlyMembershipFee() != null ? getYearlyMembershipFee().name() : "-",
                getYearlyFeeDate() != null ? getYearlyFeeDate() : "-",
                getActiveMembership() ? "Yes" : "No",
                juniorDisciplinType
        );
    }








}
