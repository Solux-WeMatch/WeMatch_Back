package WeMatch.wematch.domain.mail.service;

import WeMatch.wematch.domain.member.dto.TeamCreateRequestDto;
import WeMatch.wematch.domain.member.dto.TeamSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;

    // 팀 초대 메일 보내기
    public void sendTeamInviteMail(TeamSaveRequestDto teamSaveRequestDto){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("WeMatch : 모임에서 초대하였습니다"); // 메일 제목
        message.setTo(teamSaveRequestDto.getEmail()); // 받는 이메일
        message.setText("팀 생성을 수락하시겠습니까?\n" +
                "수락하시려면 아래 링크를 클릭하세요.\n" +
                "http://localhost:8000/acceptTeam?teamId=" + teamSaveRequestDto.getTeamId() + "&memberId=" + teamSaveRequestDto.getMemberId());

        javaMailSender.send(message);
    }

    public void sendChangePassword(String emailAddress,String randomPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("WeMatch : 비밀번호가 변경되었습니다");
        message.setTo(emailAddress);
        message.setText("변경된 비밀번호를 확인하세요\n"+
                randomPassword);
        javaMailSender.send(message);
    }
}
