package submeet.backend.service.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import submeet.backend.apiPayLoad.code.status.ErrorStatus;
import submeet.backend.apiPayLoad.exception.handler.MemberHandler;
import submeet.backend.entity.Member;
import submeet.backend.repository.MemberRepository;
import submeet.backend.web.dto.member.MemberRequestDTO;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberCommandServiceImpl implements MemberCommandService {
    private final MemberRepository memberRepository;
    @Override
    @Transactional
    public Member join(MemberRequestDTO.MemberJoinDTO memberJoinDTO) {
        if(memberRepository.existsByEmail(memberJoinDTO.getEmail())){
            return memberRepository.findByEmail(memberJoinDTO.getEmail()).orElseThrow(()-> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        }
        else{
            Member member = Member.builder()
                    .nickName(memberJoinDTO.getNick_name())
                    .profile_image(memberJoinDTO.getProfile_image())
                    .age(memberJoinDTO.getAge())
                    .email(memberJoinDTO.getEmail())
                    .gender(memberJoinDTO.getGender())
                    .build();
            return memberRepository.save(member);
        }

    }
}
