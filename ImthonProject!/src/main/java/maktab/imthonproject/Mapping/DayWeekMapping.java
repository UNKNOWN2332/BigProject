package maktab.imthonproject.Mapping;

import maktab.imthonproject.DAO.DayWeek;
import maktab.imthonproject.DTO.DayWeekDTO;

public class DayWeekMapping {
    public static DayWeekDTO toDto(DayWeek dayWeek)
    {
        return dayWeek==null?null:new DayWeekDTO(
          dayWeek.getId(),
          dayWeek.getDayName()
        );
    }
    public static DayWeek toEntity(DayWeekDTO dayWeekDTO)
    {
        return dayWeekDTO==null ? null : new DayWeek(
                dayWeekDTO.getId(),
                dayWeekDTO.getDayName()
        );
    }
}
