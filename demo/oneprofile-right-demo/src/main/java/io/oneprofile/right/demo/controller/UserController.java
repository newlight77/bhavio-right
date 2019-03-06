package io.oneprofile.right.demo.controller;

import io.oneprofile.right.demo.model.UserModel;
import io.oneprofile.right.demo.service.UserService;
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
  public UserModel create(@RequestHeader("primary") String primary,
                          @RequestBody UserModel userModel) {
    return userService.save(primary, userModel);
  }

  @GetMapping(value = "/{id}")
  public UserModel findById(@RequestHeader("primary") String primary,
                            String id) {
    return userService.findById(primary, id);
  }

  @DeleteMapping(value = "/{id}")
  public void deleteById(@RequestHeader("primary") String primary,
                         String id) {
    userService.deleteById(primary, id);
  }

  @GetMapping(value = "/page")
  public Page<UserModel> findAll(@RequestHeader("primary") String primary,
                                 Pageable pageable) {
    return userService.findAll(primary, pageable);
  }

  @GetMapping(value = "", params = {"username"})
  public Collection<UserModel> findByUsername(@RequestHeader("primary") String primary,
                                              @RequestParam String username) {
    return userService.findByUsername(primary, username);
  }

  @GetMapping(value = "", params = {"firstname", "lastname"})
  public Collection<UserModel> find(@RequestHeader("primary") String primary,
                                    @RequestParam String firstname,
                                    @RequestParam String lastname) {
    return userService.find(primary, firstname, lastname);
  }

}
