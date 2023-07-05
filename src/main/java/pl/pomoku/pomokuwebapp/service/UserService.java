package pl.pomoku.pomokuwebapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pomoku.pomokuwebapp.dto.CredentialsDto;
import pl.pomoku.pomokuwebapp.dto.SignUpDto;
import pl.pomoku.pomokuwebapp.dto.UserDto;
import pl.pomoku.pomokuwebapp.entity.AppUser;
import pl.pomoku.pomokuwebapp.entity.AppUserRole;
import pl.pomoku.pomokuwebapp.exception.AppException;
import pl.pomoku.pomokuwebapp.mapper.UserMapper;
import pl.pomoku.pomokuwebapp.repository.UserRepository;

import java.nio.CharBuffer;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserDto login(CredentialsDto credentialsDto){
        AppUser appUser = userRepository.findByEmail(credentialsDto.email())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if(passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password())
        , appUser.getPassword())){
            return userMapper.toUserDto(appUser);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto signUpDto) {
        Optional<AppUser> oUser = userRepository.findByEmail(signUpDto.email());
        if(oUser.isPresent()){
            throw new AppException("Email is already exist", HttpStatus.BAD_REQUEST);
        }
        AppUser appUser = userMapper.signUpToUser(signUpDto);
        appUser.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.password())));
        appUser.setAppUserRole(AppUserRole.USER);
        AppUser savedAppUser = userRepository.save(appUser);
        return userMapper.toUserDto(savedAppUser);
    }

    public Optional<AppUser> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public void update(AppUser user){
        userRepository.save(user);
    }
}
