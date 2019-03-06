package io.oneprofile.right.demo.mapper;


import io.oneprofile.right.demo.entity.UserEntity;
import io.oneprofile.right.demo.model.UserModel;

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
