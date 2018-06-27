package com.newlight77.right.demo.controller;

import com.newlight77.right.demo.model.RightsModel;
import com.newlight77.right.demo.model.UserModel;
import com.newlight77.right.service.RightFilter;
import com.newlight77.right.service.RightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rights")
public class RightController {

  @Autowired
  private RightService rightService;

  @PostMapping(value = "")
  public void create(@RequestBody RightsModel rights) {
    rightService.addRight(rights.getPrimary(), rights.getSecondary(), rights.getRights(), rights.getTempRights());
  }

  @GetMapping(value = "")
  public boolean hasRight(@RequestBody RightFilter filter) {
    return rightService.hasRight(filter);
  }

}
