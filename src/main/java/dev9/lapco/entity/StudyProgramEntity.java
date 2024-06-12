package dev9.lapco.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Document(value = "study_program")
public class StudyProgramEntity extends BaseEntity {

    private String studyProgramName;

    private List<String> classCodeList;

    private int totalStudent;

    private LocalDate startDate;

    private LocalDate endDate;

    private String studyProgramCode;

    private String studyProgramDescription;

}
