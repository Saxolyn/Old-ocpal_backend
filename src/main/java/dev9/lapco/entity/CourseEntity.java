package dev9.lapco.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Document(value = "course")
public class CourseEntity extends BaseEntity {

    private String courseName;

    private ScheduleEntity schedule;

    private int estimatedTime;

    private SkillStyleEntity skillStyle;

    private String description;

    private int totalLession;

    private List<LessonEntity> lessionList;

}
