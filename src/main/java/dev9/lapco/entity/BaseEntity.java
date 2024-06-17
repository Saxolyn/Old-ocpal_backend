package dev9.lapco.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@SuperBuilder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseEntity {

    @Id
    @JsonIgnore
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
