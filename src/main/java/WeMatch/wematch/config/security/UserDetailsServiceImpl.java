package WeMatch.wematch.config.security;

import WeMatch.wematch.domain.member.entity.Member;
import WeMatch.wematch.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try{
            Member member = memberRepository.findByEmail(email);
            return new CustomUserDetails(member);
        } catch(Exception e) {
            throw new UsernameNotFoundException("Invalid authentication!");
        }
    }
}
