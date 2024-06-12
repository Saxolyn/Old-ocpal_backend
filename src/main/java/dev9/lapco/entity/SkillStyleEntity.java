package dev9.lapco.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Document(value = "skill_style")
public class SkillStyleEntity extends BaseEntity{
    private String skillStyleName;

    private String skillStyleDescription;

    private String skillStyleCode;

}
