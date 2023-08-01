package WeMatch.wematch.domain.member.repository;

import WeMatch.wematch.domain.member.entity.Member;
import WeMatch.wematch.mapper.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class MemberRepository {
    private Mapper memberDAO;

    public String test(String name) {return memberDAO.test(name);}

    public boolean ifExists(String email) {
        return memberDAO.ifExists(email);
    }

    public void save(Member member) {
        memberDAO.save(member);
    }

    public Member findByEmail(String email) {
        try {
            Member member = memberDAO.findByEmail(email);
            return member;
        } catch (Exception e) {
            throw new BadCredentialsException("입력한 정보를 확인하세요");
        }

    }

}
