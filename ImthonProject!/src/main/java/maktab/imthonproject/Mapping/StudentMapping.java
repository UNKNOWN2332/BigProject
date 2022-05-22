package maktab.imthonproject.Mapping;

import maktab.imthonproject.DAO.Student;
import maktab.imthonproject.DTO.StudentDTO;

public class StudentMapping {
    public static StudentDTO toDto(Student student)
    {
        return student==null ? null : new StudentDTO(
                student.getId(),
                student.getFirstname(),
                student.getLastname(),
                student.getTeacherId(),
                student.getClassId(),
                student.getParentId(),
                student.getPhoneNumber(),
                student.getBirthdate()
        );
    }
    public static Student toEntity(StudentDTO studentDTO)
    {
        return studentDTO==null ? null : new Student(
                studentDTO.getId(),
                studentDTO.getFirstname(),
                studentDTO.getLastname(),
                studentDTO.getTeacherId(),
                studentDTO.getClassId(),
                studentDTO.getParentId(),
                studentDTO.getPhoneNumber(),
                studentDTO.getBirthdate()
        );
    }
}
