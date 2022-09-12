package source.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import source.constant.Role;
import source.dto.request.UserCreateRequestDto;
import source.entity.Account;
import source.entity.User;
import source.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserCreateRequestDto request) throws Exception {
        return userRepository.save(User.builder()
            .userName(request.getUserName())
            .role(Role.USER)
            .account(Account.builder()
                .email(request.getEmail())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .build()
            )
            .build()
        );
    }
}
