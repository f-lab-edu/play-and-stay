package com.world.playstay.user.mapper;

import com.world.playstay.user.dto.request.CreateGuestRequest;
import com.world.playstay.user.dto.response.GuestResponse;
import com.world.playstay.user.entity.Guest;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT
)
public interface GuestMapstructMapper {

  Guest toGuest(CreateGuestRequest guestRequest);

  GuestResponse toResponse(Guest guest);

}