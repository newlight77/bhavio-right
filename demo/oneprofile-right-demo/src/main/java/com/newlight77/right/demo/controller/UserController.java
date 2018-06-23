package com.newlight77.right.demo.controller;

import com.newlight77.right.demo.model.User;
import com.newlight77.right.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping(value = "")
  public String findAll() {
    return "Users";
  }

  @PostMapping(value = "")
  public User create(@RequestBody User user) {
    return userService.save(user);
  }

  @GetMapping(value = "/{id}")
  public User findById(Long id) {
    return userService.findById(id);
  }

  @DeleteMapping(value = "/{id}")
  public void deleteById(Long aLong) {
    userService.deleteById(aLong);
  }

  @GetMapping(value = "/page")
  public Page<User> findAll(Pageable pageable) {
    return userService.findAll(pageable);
  }

  @GetMapping(value = "", params = {"username"})
  public Collection<User> findByUsername(@RequestParam String username) {
    return userService.findByUsername(username);
  }

  @GetMapping(value = "", params = {"firstname", "lastname"})
  public Collection<User> find(@RequestParam String firstname, @RequestParam String lastname) {
    return userService.find(firstname, lastname);
  }

}
