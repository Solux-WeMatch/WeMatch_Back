package WeMatch.wematch.domain.member.service;

import WeMatch.wematch.domain.member.dto.TeamListResponseDto;
import WeMatch.wematch.domain.member.entity.Member;
import WeMatch.wematch.domain.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MemberService {
    public final MemberRepository memberRepository;

    public Member getCurrentMember() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if(SecurityContextHolder.getContext()==null) {
            System.out.println("null!!!!");
        } else if(SecurityContextHolder.getContext().getAuthentication()==null) {
            System.out.println(SecurityContextHolder.getContext() +" 1234 success");
            System.out.println("a is null!!");
        } else if(SecurityContextHolder.getContext().getAuthentication().getName()==null) {
            System.out.println("name is null");
        } else {
            System.out.println(SecurityContextHolder.getContext().getAuthentication().getName()+" name sucess");
            System.out.println(email);
        }
        return memberRepository.findByEmail(email);
    }

    public List<TeamListResponseDto> getTeamList(Long memberId){
        return memberRepository.getTeamList(memberId);
    }


}
