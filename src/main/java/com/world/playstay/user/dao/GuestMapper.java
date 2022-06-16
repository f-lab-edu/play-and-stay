package com.world.playstay.user.dao;


import com.world.playstay.user.entity.Guest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GuestMapper extends UserMapper<Guest> {

}

