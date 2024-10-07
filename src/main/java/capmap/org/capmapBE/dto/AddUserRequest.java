package capmap.org.capmapBE.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class AddUserRequest {
    private String email;
    private String password;
    private String nickname;
    private int profile_pic;
    private int user_level;
    private int user_caps;

}