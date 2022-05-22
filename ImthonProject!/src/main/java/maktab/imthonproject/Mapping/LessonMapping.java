package maktab.imthonproject.Mapping;

import maktab.imthonproject.DAO.Lesson;
import maktab.imthonproject.DTO.LessonDTO;

public class LessonMapping {
    public static LessonDTO toDto(Lesson lesson)
    {
        return lesson==null ? null: new LessonDTO(lesson.getId(),
                lesson.getLesson_name());
    }
    public static LessonDTO toDtos(Lesson lesson)
    {
        return lesson==null ? null: new LessonDTO(lesson.getId(),
                lesson.getLesson_name());
    }
    public static Lesson toEntity(LessonDTO lessonDTO)
    {
        return lessonDTO==null ? null: new Lesson(
                lessonDTO.getLesson_name());
    }
}
