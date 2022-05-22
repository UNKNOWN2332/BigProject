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
public class Teacher {
    @Id
    @GeneratedValue(generator = "teacher_id_seq")
    @SequenceGenerator(name = "teacher_id_seq",sequenceName = "teacher_id_seq",allocationSize = 1)
    private Integer id;

    private String firstname;

    private String lastname;

    private Integer lessonId;

    private Date birthdate;

    private Integer classId;

    private String phonenumber;

    private Integer isactive=0;

    public Teacher(Integer id, String firstname, String lastname, Integer lessonId, Date birthdate, Integer classId, String phonenumber) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.lessonId = lessonId;
        this.birthdate = birthdate;
        this.classId = classId;
        this.phonenumber = phonenumber;
    }
}
