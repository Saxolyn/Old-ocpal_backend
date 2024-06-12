package dev9.lapco.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Document(value = "completion_rate")
public class CompletionRateEntity extends BaseEntity{

    @JsonIgnore
    private String studentCode;

    @JsonIgnore
    private String courseCode;

    @JsonIgnore
    private String studyProgramCode;

    @JsonIgnore
    private long mark;

    private long completionRate;

    //thoi gian hoc: dv = hour
    private long studyTime;
}
