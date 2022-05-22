package maktab.imthonproject.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(generator = "lesson_id_seq" )
    @SequenceGenerator(name = "lesson_id_seq", sequenceName = "lesson_id_seq",allocationSize = 1)
    private Integer id;

    private String lesson_name;

    private Integer isactive=0;

    public Lesson( String lesson_name) {
        this.lesson_name = lesson_name;
    }
}
