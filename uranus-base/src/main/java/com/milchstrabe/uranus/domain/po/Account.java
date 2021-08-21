package com.milchstrabe.uranus.domain.po;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Long id;
    private String phone;
    private String email;
    private User user;
}
