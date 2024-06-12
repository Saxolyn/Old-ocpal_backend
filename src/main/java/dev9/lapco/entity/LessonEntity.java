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
@Document(value = "lesson")
public class LessonEntity extends BaseEntity {

    private String lessonName;

    private String lessonDescription;

    private List<VideoEntity> videoList;

}
