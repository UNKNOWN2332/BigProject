package maktab.imthonproject.Mapping;

import maktab.imthonproject.DAO.Timetable;
import maktab.imthonproject.DTO.*;

import java.util.List;
import java.util.stream.Collectors;


public class TimeTableMapping {
    public static TimeTableDTO toDto(Timetable timeTable) {
        return timeTable == null ? null : new TimeTableDTO(
                timeTable.getId(),
                timeTable.getLesson_numbers(),
                timeTable.getStart_time(),
                timeTable.getEnd_time()
        );
    }
    public static TimeTableDTO toDtos(Timetable timeTable) {

        DayWeekDTO dayWeekDTO = DayWeekMapping.toDto(timeTable.getDayWeek());

        return timeTable == null ? null : new TimeTableDTO(
                timeTable.getId(),
                dayWeekDTO,
                timeTable.getLesson_numbers(),
                LessonMapping.toDto(timeTable.getLessons()),
                timeTable.getStart_time(),
                timeTable.getEnd_time(),
                RoomMapping.toDto(timeTable.getRoom()),
                TeacherMapping.toDto(timeTable.getTeachers()),
                ClassMapping.toDto(timeTable.getAClas())
        );
    }
    public static Timetable toEntity(TimeTableDTO timeTableDTO)
    {
        return timeTableDTO==null ?null : new Timetable(
                timeTableDTO.getId(),
                timeTableDTO.getLesson_numbers(),
                timeTableDTO.getStart_time(),
                timeTableDTO.getEnd_time());
    }

}
