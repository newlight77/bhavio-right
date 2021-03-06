package io.oneprofile.right.demo.service;


import io.oneprofile.right.aspect.Rights;
import io.oneprofile.right.demo.entity.UserEntity;
import io.oneprofile.right.demo.mapper.UserMapper;
import io.oneprofile.right.demo.model.UserModel;
import io.oneprofile.right.demo.repository.UserRepository;
import io.oneprofile.right.model.Right;
import io.oneprofile.right.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class UserService {

  private static final int LIMIT = 1000;

  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Rights(rights = Right.ADMIN_WRITE)
  public UserModel save(String primary, UserModel userModel) {
    UserEntity entity = UserMapper.from(userModel);
    return UserMapper.to(userRepository.save(entity));
  }

  @Rights(rights = Right.ADMIN_DELETE)
  public void deleteById(String primary, String id) {
    userRepository.deleteById(id);
  }

  @Rights(rights = Right.ADMIN_READ)
  public UserModel findById(String primary, String id) {
    return userRepository.findById(id)
        .map(UserMapper::to)
        .orElseThrow(() -> new ResourceAccessException("Resource not found"));
  }

  @Rights(rights = Right.ADMIN_READ)
  public Page<UserModel> findAll(String primary, Pageable pageable) {
    return userRepository.findAll(pageable)
        .map(UserMapper::to);
  }

  @Rights(rights = Right.ADMIN_READ)
  public List<UserModel> findByUsername(String primary, String username) {
    log.info("findByFirstname with username={}", username);
    return userRepository.findByUsername(username)
        .stream()
        .map(UserMapper::to)
        .collect(Collectors.toList());
  }

  @Rights(rights = Right.ADMIN_READ)
  public List<UserModel> find(String primary, String firstname, String lastname) {
    log.info("findByFirstname with firstname={} lastname={} username={}", firstname, lastname);
    return userRepository.findByFirstnameAndLastname(firstname, lastname)
        .stream()
        .map(UserMapper::to)
        .collect(Collectors.toList());
  }

}
