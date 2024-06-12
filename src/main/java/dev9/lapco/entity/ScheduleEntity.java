package dev9.lapco.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Document(value = "schedule")
public class ScheduleEntity extends BaseEntity {

    private List<LocalDateTime> studyTime;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String description;

    private String studyProgramCode;

    private String courseCode;

    private String classCode;

}
