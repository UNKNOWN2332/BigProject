package maktab.imthonproject.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {
    private Integer id;

    private String firstname;
    private String lastname;
    private Integer lessonId;
    private Date birthdate;
    private Integer classId;
    private String phonenumber;

}
