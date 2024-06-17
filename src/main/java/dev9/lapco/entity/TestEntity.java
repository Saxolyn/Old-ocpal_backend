package dev9.lapco.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Document(value = "test")
public class TestEntity extends BaseEntity {

    private CourseEntity courseCode;

    private String testName;

    private String testCode;

    //phan loai chuong trinh dao tao = "Chuong trinh" + SkillStyleEntity.name
    private String skillStyleName;

    private long testTime;

    private ClassEntity joinedClass;

    private String testDescription;

    private String testNote;

    private List<QuestionEntity> testContent;

    private long pointPerQuestion;

    private Boolean isActive;

}
