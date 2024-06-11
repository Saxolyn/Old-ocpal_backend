package dev9.lapco.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BaseEntity {

    @Id
    private String id;

    @CreatedBy
    private String createdUser;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedBy
    private String updatedUser;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    @Field(value = "isDelete")
    private Boolean isDeleted;

}
