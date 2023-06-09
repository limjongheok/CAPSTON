package capston.capston.user.dto.emailAuthenticationDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
public class SendAuthCodeRequestDTO {
    @NotBlank
    @Email
    private String email;


    public  SendAuthCodeRequestDTO(String email){
        this.email = email;

    }

}
