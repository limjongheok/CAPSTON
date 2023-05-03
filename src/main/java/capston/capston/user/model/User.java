package capston.capston.user.model;

import capston.capston.jpaAuditing.BaseTimeEntity;
import capston.capston.security.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name ="User_ID")
    private String user;


    @Column(nullable = false)
    private String email;


    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String studentId;


    @Column(nullable = false)
    private String userName;

    @Column(nullable = false,length=13)
    private String phoneNumber;







    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role roles;


    public List<Role> getRoleList(){
        if(this.roles!=null){
            return Arrays.asList(this.roles);
        }

        return new ArrayList<>();
    }

    @Builder
    private User(String user, String email, String password,String studentId, String userName, String phoneNumber, Role roles) {
        this.user = user;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.studentId = studentId;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }
}
