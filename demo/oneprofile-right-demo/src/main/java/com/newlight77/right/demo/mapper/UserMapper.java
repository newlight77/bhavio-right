package com.newlight77.right.demo.mapper;


import com.newlight77.right.demo.entity.UserEntity;
import com.newlight77.right.demo.model.UserModel;

public class UserMapper {

  public static UserModel to(UserEntity entity) {
    return UserModel.builder()
        .firstname(entity.getFirstname())
        .lastname(entity.getLastname())
        .username(entity.getUsername()).build();
  }

  public static UserEntity from(UserModel userModel) {
    return UserEntity.builder()
        .firstname(userModel.getFirstname())
        .lastname(userModel.getLastname())
        .username(userModel.getUsername())
        .build();
  }
}
