package submeet.backend.converter;

import submeet.backend.security.Token;
import submeet.backend.web.dto.token.TokenResponseDTO;

public class TokenConverter {
    public static TokenResponseDTO.TokenRefreshDTO toTokenRefreshDTO(Token token) {
        return TokenResponseDTO.TokenRefreshDTO.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }
}
