package dev9.lapco.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Document(value = "course_mark")
public class CourseMark extends BaseEntity {

    private String testCode;

    private long testTime;

    private TeacherEntity createdTeacher;

    private long mark;

    private String description;

    private String comment;

}
