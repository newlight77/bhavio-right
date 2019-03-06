package io.oneprofile.right.demo.model;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class UserModel {
  private String firstname;
  private String lastname;
  private String username;
}
