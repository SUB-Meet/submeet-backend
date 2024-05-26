package submeet.backend.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import submeet.backend.apiPayLoad.ApiResponse;
import submeet.backend.apiPayLoad.code.status.SuccessStatus;
import submeet.backend.converter.MemberConverter;
import submeet.backend.entity.Member;
import submeet.backend.security.Token;
import submeet.backend.security.TokenService;
import submeet.backend.service.member.MemberCommandService;
import submeet.backend.service.member.MemberQueryService;
import submeet.backend.web.dto.member.MemberRequestDTO;
import submeet.backend.web.dto.member.MemberResponseDTO;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@RequiredArgsConstructor
@RequestMapping("/api/member")
@Slf4j
@RestController
public class MemberController {
    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;
    private final TokenService tokenService;
    @PostMapping("/join")
    public ApiResponse<MemberResponseDTO.MemberJoinResultDTO> join(@RequestBody MemberRequestDTO.MemberJoinDTO memberJoinDTO){
        Member member = memberCommandService.join(memberJoinDTO);
        Token token = tokenService.generateToken(member.getEmail(), "USER");
        return ApiResponse.of(SuccessStatus.MEMBER_JOIN,MemberConverter.toMemberJoinResultDTO(member,token));
    }

    @GetMapping("/login")
    public ApiResponse<MemberResponseDTO.LoginResultDTO> loginMember(HttpServletRequest httpServletRequest) {
        Member member = memberQueryService.login(httpServletRequest);
        Token newToken = tokenService.generateToken(member.getEmail(), "USER");

        Date date = tokenService.getExpiration(newToken.getAccessToken());
        LocalDateTime expiration = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        return ApiResponse.of(SuccessStatus.MEMBER_LOGIN, MemberConverter.toLoginResultDTO(member, newToken, expiration) );
    }


}
