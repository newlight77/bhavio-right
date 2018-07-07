package com.newlight77.right.service;


import com.newlight77.right.model.RightDto;

import java.util.Collection;

public interface RightService {

  public boolean hasRight(RightFilter filter);

  public RightDto addRight(RightDto right);

  public Collection<RightDto> findByPrimaryAndSecondary(String primary, String secondary);

  public void deleteByPrimaryAndSecondary(String primary, String secondary);
}
