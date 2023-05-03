package capston.capston.user.dto.userJoinDTO;


import capston.capston.user.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class UserJoinResponseDTO {
    private String email;
    private  String userName;
    private  String phoneNumber;
    private String studentId;
    private LocalDateTime createDate;
    private  LocalDateTime modifiedDate;

    @Builder
    private UserJoinResponseDTO(String email, String userName, String phoneNumber,String studentId, LocalDateTime createDate, LocalDateTime modifiedDate) {
        this.email = email;
        this.userName = userName;


        this.studentId = studentId;

        this.phoneNumber = phoneNumber;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }
    public static  UserJoinResponseDTO toUserJoinResponseDTO(User user){
        return UserJoinResponseDTO.builder()
                .email(user.getEmail())
                .userName(user.getUserName())
                .studentId(user.getStudentId())
                .phoneNumber(user.getPhoneNumber())
                .createDate(user.getCreateDate())
                .modifiedDate(user.getModifiedDate())
                .build();

    }

}
