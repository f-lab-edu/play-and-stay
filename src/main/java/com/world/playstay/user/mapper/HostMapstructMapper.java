package com.world.playstay.user.mapper;

import com.world.playstay.user.dto.request.HostRequest;
import com.world.playstay.user.dto.response.HostResponse;
import com.world.playstay.user.entity.Host;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT
)
public interface HostMapstructMapper {

  Host toHost(HostRequest.Creation hostRequest);

  HostResponse toResponse(Host host);

}
