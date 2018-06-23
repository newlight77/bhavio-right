package com.newlight77.right.demo.service;


import com.newlight77.exception.NotFoundException;
import com.newlight77.right.aspect.Rights;
import com.newlight77.right.demo.entity.UserEntity;
import com.newlight77.right.demo.mapper.UserMapper;
import com.newlight77.right.demo.model.User;
import com.newlight77.right.demo.repository.UserRepository;
import com.newlight77.right.model.Right;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class UserService {

  private static final int LIMIT = 1000;

  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User save(User user) {
    UserEntity entity = UserMapper.from(user);
    return UserMapper.to(userRepository.save(entity));
  }

  public User findById(Long id) {
    return userRepository.findById(id)
        .map(UserMapper::to)
        .orElseThrow(() -> new NotFoundException("Resource not found"));
  }

  public void deleteById(Long aLong) {
    userRepository.deleteById(aLong);
  }

  public Page<User> findAll(Pageable pageable) {
    return userRepository.findAll(pageable)
        .map(UserMapper::to);
  }

  @Rights(rights = Right.READ)
  public List<User> findByUsername(String username) {
    log.info("findByFirstname with username={}", username);
    return userRepository.findByUsername(username)
        .stream()
        .map(UserMapper::to)
        .collect(Collectors.toList());
  }

  public List<User> find(String firstname, String lastname) {
    log.info("findByFirstname with firstname={} lastname={} username={}", firstname, lastname);
    return userRepository.findByFirstnameAndLastname(firstname, lastname)
        .stream()
        .map(UserMapper::to)
        .collect(Collectors.toList());
  }

}
