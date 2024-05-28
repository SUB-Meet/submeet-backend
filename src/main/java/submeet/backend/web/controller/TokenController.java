package submeet.backend.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import submeet.backend.apiPayLoad.ApiResponse;
import submeet.backend.apiPayLoad.code.status.SuccessStatus;
import submeet.backend.converter.TokenConverter;
import submeet.backend.security.Token;
import submeet.backend.security.TokenService;
import submeet.backend.web.dto.token.TokenResponseDTO;

@RestController
@RequiredArgsConstructor
public class TokenController {
    private final TokenService tokenService;

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @GetMapping("/token/expired")
    public ApiResponse<TokenResponseDTO.TokenRefreshDTO> refreshAuth(HttpServletRequest request) {
        String token = request.getHeader("Refresh");
        if (token != null && tokenService.verifyToken(token)) {
            String email = tokenService.getUid(token);
            Token newToken = tokenService.generateToken(email, "USER");

            return ApiResponse.of(SuccessStatus.TOKEN_REFRESHED, TokenConverter.toTokenRefreshDTO(newToken));
        }

        throw new RuntimeException("유효한 refresh 토큰이 아닙니다.");
    }
}
