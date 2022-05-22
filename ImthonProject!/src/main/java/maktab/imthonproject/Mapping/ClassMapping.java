package maktab.imthonproject.Mapping;

import maktab.imthonproject.DAO.Class;
import maktab.imthonproject.DTO.ClassDTO;
public class ClassMapping {

    public static ClassDTO toDto(Class class_ )
    {
        return class_==null ?null:
                new ClassDTO(class_.getId(),
                        class_.getClass_name(),
                        class_.getClass_count());
    }
    public static ClassDTO toDtos(Class class_ )
    {


        return class_==null ?null:
                new ClassDTO(class_.getId(),
                        class_.getClass_name(),
                        class_.getClass_count(),
                        TimeTableMapping.toDto(class_.getTimetable()));
    }

    public static Class toEntity(ClassDTO classDTO)
    {
        return classDTO==null ? null:
                new Class(classDTO.getId(),
                        classDTO.getClass_name(),
                        classDTO.getClass_count());
    }

}
