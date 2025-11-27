package SmashTennisClub.FileSystem.FileSystemSubClasses;

import SmashTennisClub.FileSystem.FileHandler;
import SmashTennisClub.FileSystem.FileSystemSubClasses.MemberReader;
import SmashTennisClub.FileSystem.FileSystemSubClasses.QuotaWriter;
import SmashTennisClub.MainPackage.FinanceManagement.Quota;
import SmashTennisClub.MainPackage.MembershipTypes.Member;

import java.time.LocalDate;
import java.util.ArrayList;

public class QuotaGenerator {
    public void generateQuotasFromMembers() {

        MemberReader memberReader = new MemberReader();
        ArrayList<Member> members = memberReader.readFromFile();
        ArrayList<Quota> quotas = new ArrayList<>();

        int nextQuotaId = 1;


        for (Member m : members) {
            Quota q = new Quota(
                    nextQuotaId,
                    m.getMemberId(),
                    m.getMemberName(),
                    m.getYearlyMembershipFee(),
                    false,
                    m.getYearlyFeeDate(),
                    null
            );
            quotas.add(q);
            nextQuotaId++;
        }

        FileHandler fh = new FileHandler();
        fh.saveQuotas(quotas);


    }
}
