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
@NoArgsConstructor
@AllArgsConstructor
public class DayWeek {
    @Id
    @GeneratedValue(generator = "dayweek_id_seq")
    @SequenceGenerator(name = "dayweek_id_seq",sequenceName = "dayweek_id_seq",allocationSize = 1)
    private Integer id;

    private String dayName;

    private Integer isactive=0;

    public DayWeek(Integer id, String dayName) {
        this.id = id;
        this.dayName = dayName;
    }
}
