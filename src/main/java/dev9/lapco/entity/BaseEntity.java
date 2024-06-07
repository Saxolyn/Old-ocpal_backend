package dev9.lapco.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BaseEntity {

    @Id
    private String id;

    private String createdUser;

    private LocalDateTime createdDate = LocalDateTime.now();

    private String updatedUser;

    private LocalDateTime updatedDate =  LocalDateTime.now();;

}
