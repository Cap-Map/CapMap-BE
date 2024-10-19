package capmap.org.capmapBE.service;

import capmap.org.capmapBE.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import capmap.org.capmapBE.domain.User;
import capmap.org.capmapBE.dto.AddUserRequest;
import capmap.org.capmapBE.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    public Long save(AddUserRequest dto) {
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .nickname(dto.getNickname())
                .profile_pic(dto.getProfile_pic())
                .user_level(dto.getUser_level())
                .user_caps(dto.getUser_caps())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    /* 닉네임, 이메일 중복 여부 확인 */

    @Transactional(readOnly = true)
    //@Override
    public boolean checkNicknameDuplication(String nickname) {
        boolean nicknameDuplicate = userRepository.existsByNickname(nickname);
        return nicknameDuplicate;

    }
    @Transactional(readOnly = true)
    //@Override
    public boolean checkEmailDuplication(String email) {
        boolean emailDuplicate = userRepository.existsByEmail(email);
        return emailDuplicate;
    }

    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        return user;
    }

}
