package com.shelfwise.backend.modules.auth.utils.mapper;

import com.shelfwise.backend.modules.auth.model.User;
import com.shelfwise.backend.modules.auth.utils.dto.RegisterRequest;
import com.shelfwise.backend.modules.auth.utils.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);

    @Mapping(target = "userId", ignore = true)
//    @Mapping(target = "passwordHash", source = "password")
    User toUser(RegisterRequest registerRequest);
}
