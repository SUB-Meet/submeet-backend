package submeet.backend.service.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import submeet.backend.entity.Member;
import submeet.backend.repository.member.MemberRepository;
import submeet.backend.web.dto.member.MemberRequestDTO;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    @Override
    @Transactional
    public Member join(MemberRequestDTO.MemberJoinDTO memberJoinDTO) {
        log.info("name = {}",memberJoinDTO.getName());
        Member member = Member.builder()
                .name(memberJoinDTO.getName())
                .nickName(memberJoinDTO.getNick_name())
                .profile_image(memberJoinDTO.getProfile_image())
                .age(memberJoinDTO.getAge())
                .build();
        return memberRepository.save(member);
    }
}
