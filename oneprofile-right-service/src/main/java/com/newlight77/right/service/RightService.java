package com.newlight77.right.service;


import com.newlight77.right.model.Right;
import com.newlight77.right.model.TemporaryRight;

import java.util.Set;

public interface RightService {

  public void addRight(String primary, String secondary, Set<Right> rights, Set<TemporaryRight> tempRights);

  public boolean hasRight(RightFilter filter);
}
