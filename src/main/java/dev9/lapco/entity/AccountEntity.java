package dev9.lapco.entity;

import dev9.lapco.constant.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Document(collection = "account")
public class AccountEntity extends BaseEntity {

    @Field(value = "phone_number")
    private String phoneNumber;

    private String username;

    private String password;

    private ERole role;

}
