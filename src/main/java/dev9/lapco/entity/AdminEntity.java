package dev9.lapco.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Document(collection = "admin_account")
public class AdminEntity extends AccountType{

    private String password;

    private boolean isLock;

}
