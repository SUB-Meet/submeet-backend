package submeet.backend.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import submeet.backend.apiPayLoad.ApiResponse;
import submeet.backend.apiPayLoad.code.status.SuccessStatus;
import submeet.backend.converter.MemberConverter;
import submeet.backend.entity.Member;
import submeet.backend.service.member.MemberCommandService;
import submeet.backend.web.dto.member.MemberRequestDTO;
import submeet.backend.web.dto.member.MemberResponseDTO;

@RequiredArgsConstructor
@RequestMapping("/api/member")
@Slf4j
@RestController
public class MemberController {
    private final MemberCommandService memberCommandService;
    @PostMapping
    public ApiResponse<MemberResponseDTO.MemberJoinResultDTO> join(@RequestBody MemberRequestDTO.MemberJoinDTO memberJoinDTO){
        Member member = memberCommandService.join(memberJoinDTO);
        return ApiResponse.of(SuccessStatus.MEMBER_JOIN,MemberConverter.toMemberJoinResultDTO(member));
    }


}
