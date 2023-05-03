package capston.capston.user.dto.emailAuthenticationDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SendAuthCodeResponseDTO {

    private String email;

    @Builder
    private SendAuthCodeResponseDTO(String email) {
        this.email = email;
    }
}
