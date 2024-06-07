package dev9.lapco.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherEtity extends AccountType {

    private List<String> classCodes;

    private List<String> programCodes;

    private boolean isLock = false;


}
