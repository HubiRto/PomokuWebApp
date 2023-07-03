package pl.pomoku.pomokuwebapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.pomoku.pomokuwebapp.dto.SignUpDto;
import pl.pomoku.pomokuwebapp.dto.UserDto;
import pl.pomoku.pomokuwebapp.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);
}
