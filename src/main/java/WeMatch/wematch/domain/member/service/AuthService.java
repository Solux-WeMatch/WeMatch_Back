package WeMatch.wematch.domain.member.service;

import WeMatch.wematch.config.security.JwtProvider;
import WeMatch.wematch.domain.member.dto.JwtRequestDto;
import WeMatch.wematch.domain.member.dto.MemberSignUpRequestDto;
import WeMatch.wematch.domain.member.dto.SigninResponseDto;
import WeMatch.wematch.domain.member.entity.Member;
import WeMatch.wematch.domain.member.repository.MemberRepository;
import WeMatch.wematch.exception.DuplicateMemberException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder= PasswordEncoderFactories.createDelegatingPasswordEncoder();
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtProvider jwtProvider;

    public String test(String name) {
       return  memberRepository.test(name);
    }

    public void singUp(MemberSignUpRequestDto request) {
        boolean exists = memberRepository.ifExists(request.getEmail());
        if(exists) throw new DuplicateMemberException(request.getEmail());

        Member member = request.toEntity(passwordEncoder);
        memberRepository.save(member);
    }

    public SigninResponseDto login(JwtRequestDto request) {
        Member member= memberRepository.findByEmail(request.getEmail());
        if(!passwordEncoder.matches(request.getPassword(), member.getPassword() )) {
            throw new BadCredentialsException("입력한 정보를 확인하세요");
        }
        System.out.println("email&password 획득");
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(request.authenticate());
        System.out.println("authentication 획득");
        return SigninResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .token(jwtProvider.createToken(authentication))
                .build();
    }





}
