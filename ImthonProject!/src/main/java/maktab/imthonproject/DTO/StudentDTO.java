package maktab.imthonproject.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Integer id;
    private String firstname;
    private String lastname;
    private Integer teacherId;
    private Integer classId;
    private Integer parentId;
    private String phoneNumber;
    private Date birthdate;

    public StudentDTO(String firstname, String lastname, Integer teacherId, Integer classId, Integer parentId, String phoneNumber, Date birthdate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.teacherId = teacherId;
        this.classId = classId;
        this.parentId = parentId;
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate;
    }
}
