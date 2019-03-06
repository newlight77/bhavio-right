package io.oneprofile.right.demo.controller;

import io.oneprofile.right.demo.model.RightDto;
import io.oneprofile.right.demo.service.RightCrudService;
import io.oneprofile.right.service.RightFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rights")
public class RightController {

  @Autowired
  private RightCrudService rightCrudService;

  @PostMapping(value = "")
  public void create(@RequestHeader("primary") String primary,
                     @RequestBody RightDto right) {
    rightCrudService.save(primary, right);
  }

  @GetMapping(value = "")
  public Page<RightDto> findAll(@RequestHeader("primary") String primary,
                                Pageable pageable) {
    return rightCrudService.findAll(primary, pageable);
  }

  @GetMapping(value = "hasRight")
  public boolean hasRight(@RequestHeader("primary") String primary,
                          @RequestBody RightFilter filter) {
    return rightCrudService.hasRight(primary, filter);
  }

}
