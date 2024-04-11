package submeet.backend.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import submeet.backend.apiPayLoad.ApiResponse;
import submeet.backend.apiPayLoad.code.status.SuccessStatus;
import submeet.backend.converter.MemberConverter;
import submeet.backend.entity.Member;
import submeet.backend.service.member.MemberService;
import submeet.backend.web.dto.member.MemberRequestDTO;
import submeet.backend.web.dto.member.MemberResponseDTO;

@RequiredArgsConstructor
@RequestMapping("/api/member")
@Slf4j
@RestController
public class MemberController {
    private final MemberService memberService;
    @PostMapping
    public ApiResponse<MemberResponseDTO.MemberJoinResultDTO> join(@RequestBody MemberRequestDTO.MemberJoinDTO memberJoinDTO){
        Member member = memberService.join(memberJoinDTO);
        return ApiResponse.of(SuccessStatus.MEMBER_JOIN,MemberConverter.toMemberJoinResultDTO(member));
    }
}
