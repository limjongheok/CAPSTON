package capston.capston.user.dto.emailAuthenticationDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CheckAuthCodeResponseDTO {
    private String email;

    @Builder
    private CheckAuthCodeResponseDTO(String email) {
        this.email = email;
    }
}
