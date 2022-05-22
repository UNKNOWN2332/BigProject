package maktab.imthonproject.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeTableDTO {
    private Integer id;

    private DayWeekDTO day_week;

    private Integer lesson_numbers;

    private LessonDTO lessonDTOs;

    private Date start_time;

    private Date end_time;

    private RoomDTO roomDTO;

    private TeacherDTO teacherDTOs;

    private ClassDTO classDTO;

    public TimeTableDTO(Integer id, Integer lesson_numbers, Date start_time, Date end_time) {
        this.id = id;
        this.lesson_numbers = lesson_numbers;
        this.start_time = start_time;
        this.end_time = end_time;
    }

}
