package maktab.imthonproject.Mapping;

import maktab.imthonproject.DAO.Teacher;
import maktab.imthonproject.DTO.TeacherDTO;

public class TeacherMapping {
    public static TeacherDTO toDto(Teacher teacher)
    {
        return teacher==null? null : new TeacherDTO(
                teacher.getId(),
                teacher.getFirstname(),
                teacher.getLastname(),
                teacher.getLessonId(),
                teacher.getBirthdate(),
                teacher.getClassId(),
                teacher.getPhonenumber()
        );
    }
    public static Teacher toEntity(TeacherDTO teacherDTO)
    {
        return teacherDTO==null? null : new Teacher(
                teacherDTO.getId(),
                teacherDTO.getFirstname(),
                teacherDTO.getLastname(),
                teacherDTO.getLessonId(),
                teacherDTO.getBirthdate(),
                teacherDTO.getClassId(),
                teacherDTO.getPhonenumber()
        );
    }
}
