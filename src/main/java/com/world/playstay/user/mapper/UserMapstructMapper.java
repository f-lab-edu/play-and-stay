package com.world.playstay.user.mapper;

import com.world.playstay.global.mapper.GlobalMapstructMapper;
import com.world.playstay.user.dto.UserDto;
import com.world.playstay.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapstructMapper extends GlobalMapstructMapper<User, UserDto> {


}
