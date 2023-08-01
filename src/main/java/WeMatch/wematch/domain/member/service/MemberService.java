package WeMatch.wematch.domain.member.service;

import WeMatch.wematch.domain.member.entity.Member;
import WeMatch.wematch.domain.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {
    public static MemberRepository memberRepository;

    public Member getCurrentMember() {
        return memberRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
