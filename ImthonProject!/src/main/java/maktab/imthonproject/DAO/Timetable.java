package maktab.imthonproject.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Timetable {
    @Id
    @GeneratedValue(generator = "timetable_id_seq")
    @SequenceGenerator(name = "timetable_id_seq",sequenceName = "timetable_id_seq",allocationSize = 1)
    private Integer id ;
    @ManyToOne
    @JoinColumn(name = "day_week")
    private DayWeek dayWeek;

    private Integer lesson_numbers;

    @OneToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lessons;

    private Date  start_time;

    private Date end_time;

    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToOne
    @JoinColumn(name = "teacherId")
    private Teacher teachers;

    private Integer isactive=0;

    @OneToOne
    @JoinColumn(name = "class_id",referencedColumnName = "id")
    private Class aClas;



    public Timetable(Integer id, DayWeek dayWeek, Integer lesson_numbers, Lesson lessons, Date start_time, Date end_time, Room room, Teacher teachers, Class aClas) {
        this.id = id;
        this.dayWeek = dayWeek;
        this.lesson_numbers = lesson_numbers;
        this.lessons = lessons;
        this.start_time = start_time;
        this.end_time = end_time;
        this.room = room;
        this.teachers = teachers;
        this.aClas = aClas;
    }

    public Timetable(Integer id, Integer lesson_numbers, Date start_time, Date end_time) {
        this.id = id;
        this.lesson_numbers = lesson_numbers;
        this.start_time = start_time;
        this.end_time = end_time;
    }
}
