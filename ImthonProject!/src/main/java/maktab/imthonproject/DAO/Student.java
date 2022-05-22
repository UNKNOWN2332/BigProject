package maktab.imthonproject.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(generator = "student_id_seq")
    @SequenceGenerator(name = "student_id_seq",sequenceName = "student_id_seq",allocationSize = 1)
    private Integer id;

    private String firstname;
    private String lastname;
    private Integer teacherId;
    private Integer classId;
    private Integer parentId;
    private String phoneNumber;
    private Date birthdate;
    private Integer isacteve=0;

    public Student(Integer id, String firstname, String lastname, Integer teacherId, Integer classId, Integer parentId, String phoneNumber, Date birthdate) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.teacherId = teacherId;
        this.classId = classId;
        this.parentId = parentId;
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate;
    }
}
