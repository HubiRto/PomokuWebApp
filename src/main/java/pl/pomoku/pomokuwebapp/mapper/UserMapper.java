package pl.pomoku.pomokuwebapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.pomoku.pomokuwebapp.dto.SignUpDto;
import pl.pomoku.pomokuwebapp.dto.UserDto;
import pl.pomoku.pomokuwebapp.entity.AppUser;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(AppUser appUser);

    @Mapping(target = "password", ignore = true)
    AppUser signUpToUser(SignUpDto signUpDto);
}
