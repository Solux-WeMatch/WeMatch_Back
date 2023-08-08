package WeMatch.wematch.domain.member.service;

import WeMatch.wematch.config.security.JwtProvider;
import WeMatch.wematch.domain.group.dto.FindPasswordRequestDto;
import WeMatch.wematch.domain.mail.service.MailService;
import WeMatch.wematch.domain.member.dto.JwtRequestDto;
import WeMatch.wematch.domain.member.dto.MemberSignUpRequestDto;
import WeMatch.wematch.domain.member.dto.SigninResponseDto;
import WeMatch.wematch.domain.member.entity.Member;
import WeMatch.wematch.domain.member.repository.MemberRepository;
import WeMatch.wematch.exception.DuplicateMemberException;
import WeMatch.wematch.exception.WrongInformationException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Date;

@Service
@AllArgsConstructor
public class AuthService {
    @Autowired
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder= PasswordEncoderFactories.createDelegatingPasswordEncoder();
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MailService mailService;
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

    public void findPassword(FindPasswordRequestDto findPasswordRequestDto,Member member) {
        if(!(member.getName().equals(findPasswordRequestDto.getName()))
        || !(member.getEmail().equals(findPasswordRequestDto.getEmail()))) {
            throw new WrongInformationException("이메일과 이름을 다시 확인하세요");
        }
        String randomPassword = getRandomPassword();
        System.out.println(randomPassword);
        mailService.sendChangePassword(findPasswordRequestDto.getEmail(),randomPassword);
        memberRepository.changePassword(passwordEncoder.encode(randomPassword),member.getEmail());
    }

    String getRandomPassword() {
        char[] charSet = new char[] {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '!', '@', '#', '$', '%', '^', '&' };

        StringBuffer sb = new StringBuffer();
        SecureRandom sr = new SecureRandom();
        sr.setSeed(new Date().getTime());

        int idx = 0;
        int len = charSet.length;
        for (int i=0; i<8; i++) {
            // idx = (int) (len * Math.random());
            idx = sr.nextInt(len);    // 강력한 난수를 발생시키기 위해 SecureRandom을 사용한다.
            sb.append(charSet[idx]);
        }
        return sb.toString();
    }


}
