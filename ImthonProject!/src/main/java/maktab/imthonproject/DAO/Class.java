package maktab.imthonproject.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Class {
    @Id
    @GeneratedValue(generator = "class_id_seq")
    @SequenceGenerator(name = "class_id_seq",sequenceName = "class_id_seq",allocationSize = 1)
    private Integer id;

    private String class_name;

    private Integer class_count;

    private Integer isactive=0;

    @OneToOne(mappedBy = "aClas")
    private Timetable timetable;

    public Class(Integer id, String class_name, Integer class_count) {
        this.id = id;
        this.class_name = class_name;
        this.class_count = class_count;
    }
}
