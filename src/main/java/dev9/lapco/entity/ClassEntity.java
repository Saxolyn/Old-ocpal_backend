package dev9.lapco.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Document(value = "class")
public class ClassEntity extends BaseEntity{

    private String className;

    private String classCode;

    private StudyProgramEntity studyProgram;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private int totalStudyTime;

    private TeacherEntity lecture;

    private TeacherEntity formTeacher;

    private ScheduleEntity schedule;

    private String todo;

    private List<StudentEntity> studentList;

}
