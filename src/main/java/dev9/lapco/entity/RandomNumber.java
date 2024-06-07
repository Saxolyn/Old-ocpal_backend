package dev9.lapco.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class RandomNumber extends BaseEntity{

    private String number;

    private String user;

}
