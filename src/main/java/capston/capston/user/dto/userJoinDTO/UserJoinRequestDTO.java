package capston.capston.user.dto.userJoinDTO;



import capston.capston.security.Role;
import capston.capston.user.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@NoArgsConstructor
@Getter
public class UserJoinRequestDTO {

    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    @NotBlank
    private  String userName;





    @NotBlank
    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")
    private  String phoneNumber;


    public User toEntity(String passwordEncode, Role role, String studentId){

        return User.builder()
                .email(this.email)
                .password(passwordEncode)
                .userName(this.userName)
                .studentId(studentId)
                .phoneNumber(this.phoneNumber)
                .roles(role)
                .build();
    }
}
