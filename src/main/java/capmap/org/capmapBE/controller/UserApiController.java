package capmap.org.capmapBE.controller;

import capmap.org.capmapBE.dto.AddUserRequest;
import capmap.org.capmapBE.repository.UserRepository;
import capmap.org.capmapBE.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

public class UserApiController {

    private final UserService userService;
    private final UserRepository userRepository;
    public UserApiController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/auth/user")
    public ResponseEntity<String> signup(@RequestBody AddUserRequest request) {
        try {
            userService.save(request); // 회원 가입 메서드 호출
            return ResponseEntity.status(HttpStatus.CREATED).body("User signed up successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User signup failed: " + e.getMessage());
        }
    }

    /* 닉네임, 이메일 중복 체크 */
    @GetMapping("/auth/nickname/{nickname}/exists")
    public ResponseEntity<Boolean> checkNicknameDuplicate(@PathVariable String nickname){
        return ResponseEntity.ok(userService.checkNicknameDuplication(nickname));
    }

    @GetMapping("/auth/email/{email}/exists")
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String email){
        return ResponseEntity.ok(userService.checkEmailDuplication(email));
    }

}
