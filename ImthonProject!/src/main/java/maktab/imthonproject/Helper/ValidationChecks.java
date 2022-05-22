package maktab.imthonproject.Helper;
import maktab.imthonproject.DTO.*;
import maktab.imthonproject.Helper.Validation.ValidByName;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationChecks {
    static List<ValidDTO> errors  =new ArrayList<>();
    public static List<ValidDTO> checkByTimeTable(TimeTableDTO timeTableDTO)
    {
        if(timeTableDTO.getClassDTO()==null)
            errors.add(new ValidDTO("classDTO",ValidByName.EMPTY_FIELD));
        if(timeTableDTO.getDay_week()==null)
            errors.add(new ValidDTO("day_week", ValidByName.EMPTY_FIELD));

        if(timeTableDTO.getLesson_numbers()==null)
            errors.add(new ValidDTO("lesson_numbers", ValidByName.EMPTY_FIELD));
        else if (timeTableDTO.getLesson_numbers()<1)
            errors.add(new ValidDTO("lesson_numbers", ValidByName.MINUS_VALUE));

        if(timeTableDTO.getLessonDTOs()==null)
            errors.add(new ValidDTO("lessonDTO", ValidByName.EMPTY_FIELD));

        if(timeTableDTO.getStart_time()==null)
            errors.add(new ValidDTO("start_time", ValidByName.EMPTY_FIELD));

        if(timeTableDTO.getEnd_time()==null)
            errors.add(new ValidDTO("end time", ValidByName.EMPTY_FIELD));

        if(timeTableDTO.getRoomDTO()==null)
            errors.add(new ValidDTO("roomDTO", ValidByName.EMPTY_FIELD));

        if(timeTableDTO.getTeacherDTOs()==null)
            errors.add(new ValidDTO("teacherDTO", ValidByName.EMPTY_FIELD));

        return errors;
    }
    public static List<ValidDTO> checkByClass(ClassDTO classDTO)
    {
         errors.clear();

        if(classDTO.getClass_name()==null)
            errors.add(new ValidDTO("class_name",ValidByName.EMPTY_FIELD));
        if(classDTO.getClass_name().equals(""))
            errors.add(new ValidDTO("class_name",ValidByName.EMPTY_FIELD));
        if(classDTO.getClass_count()==null)
            errors.add(new ValidDTO("class_count",ValidByName.EMPTY_FIELD));
        else if(classDTO.getClass_count()<1)
            errors.add(new ValidDTO("class_count", ValidByName.MINUS_VALUE));
        return errors;
    }

    public static List<ValidDTO> checkByLesons(LessonDTO lessonDTO) {
         List<ValidDTO> errors  =new ArrayList<>();
        if(lessonDTO.getLesson_name()==null)
            errors.add(new ValidDTO("lesson_name",ValidByName.EMPTY_FIELD));
        else if(lessonDTO.getLesson_name().equals(""))
            errors.add(new ValidDTO("lesson_name",ValidByName.EMPTY_FIELD));
        return errors;
    }
    public static List<ValidDTO> checkByStudent(StudentDTO studentDTO)
    {
        errors.clear();

        if(studentDTO.getFirstname()==null)
            errors.add(new ValidDTO("firstname",ValidByName.EMPTY_FIELD));

        if(studentDTO.getFirstname().equals(""))
            errors.add(new ValidDTO("firstname",ValidByName.EMPTY_FIELD));

        if(studentDTO.getLastname()==null)
            errors.add(new ValidDTO("lastname",ValidByName.EMPTY_FIELD));

        if(studentDTO.getLastname().equals(""))
            errors.add(new ValidDTO("lastname",ValidByName.EMPTY_FIELD));

        if(studentDTO.getTeacherId()==null)
            errors.add(new ValidDTO("teacherDTO",ValidByName.EMPTY_FIELD));

        if(studentDTO.getClassId()==null)
            errors.add(new ValidDTO("classDTO",ValidByName.EMPTY_FIELD));

        if(studentDTO.getParentId()==null)
            errors.add(new ValidDTO("parentDTO",ValidByName.EMPTY_FIELD));
        if(studentDTO.getPhoneNumber()==null)
            errors.add(new ValidDTO("phoneNumber",ValidByName.EMPTY_FIELD));
        Pattern pattern = Pattern.compile("\\+998[[3][7-9]][0-9]-[0-9]{3}-[0-9]{2}-[0-9]{2}");
        Matcher matcher = pattern.matcher(studentDTO.getPhoneNumber());
        Pattern pattern2 = Pattern.compile("\\+998[[3][7-9]][0-9][0-9]{3}[0-9]{2}[0-9]{2}");
        Matcher matcher2 = pattern2.matcher(studentDTO.getPhoneNumber());
        Pattern pattern3 = Pattern.compile("\\+998[[3][7-9]][0-9] [0-9]{3} [0-9]{2} [0-9]{2}");
        Matcher matcher3 = pattern3.matcher(studentDTO.getPhoneNumber());
        if(!matcher.find() || !matcher2.find() || !matcher3.find())
            errors.add(new ValidDTO("phoneNumber",ValidByName.NUMBER_FORMAT));
        if(studentDTO.getBirthdate()==null)
            errors.add(new ValidDTO("birthdate",ValidByName.EMPTY_FIELD));
        return errors;
    }
    public static List<ValidDTO> checkByParent(ParentDTO parentDTO)
    {
        errors.clear();
        if(parentDTO.getFirstname()==null)
            errors.add(new ValidDTO("firstname",ValidByName.EMPTY_FIELD));

        else if(parentDTO.getFirstname().equals(""))
            errors.add(new ValidDTO("firstname",ValidByName.EMPTY_FIELD));

        if(parentDTO.getLastname()==null)
            errors.add(new ValidDTO("lastname",ValidByName.EMPTY_FIELD));

        else if(parentDTO.getLastname().equals(""))
            errors.add(new ValidDTO("lastname",ValidByName.EMPTY_FIELD));
        if(parentDTO.getPhonenumbers()==null)
            errors.add(new ValidDTO("phoneNumber",ValidByName.EMPTY_FIELD));
        if(parentDTO.getBirthdate()==null)
            errors.add(new ValidDTO("birthdate",ValidByName.EMPTY_FIELD));
        Pattern pattern = Pattern.compile("\\+998[[3][7-9]][0-9]-[0-9]{3}-[0-9]{2}-[0-9]{2}");
        Matcher matcher = pattern.matcher(parentDTO.getPhonenumbers());
        Pattern pattern2 = Pattern.compile("\\+998[[3][7-9]][0-9][0-9]{3}[0-9]{2}[0-9]{2}");
        Matcher matcher2 = pattern2.matcher(parentDTO.getPhonenumbers());
        Pattern pattern3 = Pattern.compile("\\+998[[3][7-9]][0-9] [0-9]{3} [0-9]{2} [0-9]{2}");
        Matcher matcher3 = pattern3.matcher(parentDTO.getPhonenumbers());
        if(!matcher.find() || !matcher2.find() || !matcher3.find())
            errors.add(new ValidDTO("phoneNumber",ValidByName.NUMBER_FORMAT));
        return errors;
    }
    public static List<ValidDTO> checkByRoom(RoomDTO roomDTO)
    {
        errors.clear();
        if(roomDTO.getRoom_capacity()<1)
            errors.add(new ValidDTO("room_capacity",ValidByName.MINUS_VALUE));
        if(roomDTO.getRoomName()==null)
            errors.add(new ValidDTO("room_name",ValidByName.EMPTY_FIELD));
        else if(roomDTO.getRoomName().equals(""))
            errors.add(new ValidDTO("room_name",ValidByName.EMPTY_FIELD));

        if(roomDTO.getRoomNumber()==null)
            errors.add(new ValidDTO("room_number",ValidByName.EMPTY_FIELD));
        else if(roomDTO.getRoomNumber()<1)
            errors.add(new ValidDTO("room_number",ValidByName.MINUS_VALUE));
        return errors;
    }
    public static List<ValidDTO> checkByTeacher(TeacherDTO teacherDTO)
    {
        errors.clear();

        if(teacherDTO.getFirstname()==null)
            errors.add(new ValidDTO("firstname",ValidByName.EMPTY_FIELD));

        if(teacherDTO.getFirstname().equals(""))
            errors.add(new ValidDTO("firstname",ValidByName.EMPTY_FIELD));

        if(teacherDTO.getLastname()==null)
            errors.add(new ValidDTO("lastname",ValidByName.EMPTY_FIELD));

        if(teacherDTO.getLastname().equals(""))
            errors.add(new ValidDTO("lastname",ValidByName.EMPTY_FIELD));

        if(teacherDTO.getLessonId()==null)
            errors.add(new ValidDTO("lessonDTO",ValidByName.EMPTY_FIELD));

        if(teacherDTO.getBirthdate()==null)
            errors.add(new ValidDTO("birthdate",ValidByName.EMPTY_FIELD));

        if(teacherDTO.getClassId()==null)
            errors.add(new ValidDTO("classDTO",ValidByName.EMPTY_FIELD));

        if(teacherDTO.getPhonenumber()==null)
            errors.add(new ValidDTO("phoneNumber",ValidByName.EMPTY_FIELD));
        Pattern pattern = Pattern.compile("\\+998[[3][7-9]][0-9]-[0-9]{3}-[0-9]{2}-[0-9]{2}");
        Matcher matcher = pattern.matcher(teacherDTO.getPhonenumber());
        Pattern pattern2 = Pattern.compile("\\+998[[3][7-9]][0-9][0-9]{3}[0-9]{2}[0-9]{2}");
        Matcher matcher2 = pattern2.matcher(teacherDTO.getPhonenumber());
        Pattern pattern3 = Pattern.compile("\\+998[[3][7-9]][0-9] [0-9]{3} [0-9]{2} [0-9]{2}");
        Matcher matcher3 = pattern3.matcher(teacherDTO.getPhonenumber());
        if(!matcher.find() || !matcher2.find() || !matcher3.find())
            errors.add(new ValidDTO("phoneNumber",ValidByName.NUMBER_FORMAT));

        return errors;

    }
    public static List<ValidDTO> checkByDayWeek(DayWeekDTO dayWeekDTO)
    {
        errors.clear();

        if(dayWeekDTO.getDayName()==null)
            errors.add(new ValidDTO("day_name",ValidByName.EMPTY_FIELD));

        else if(dayWeekDTO.getDayName().equals(""))
            errors.add(new ValidDTO("day_name",ValidByName.EMPTY_FIELD));

        if(!dayWeekDTO.getDayName().equals("Monday".trim().toLowerCase()))
            errors.add(new ValidDTO("day_name",ValidByName.NAME_FORMAT));

        if(!dayWeekDTO.getDayName().equals("Dushanba".trim().toLowerCase()))
            errors.add(new ValidDTO("day_name",ValidByName.NAME_FORMAT));

        if(!dayWeekDTO.getDayName().equals("Понедельник".trim().toLowerCase()))
            errors.add(new ValidDTO("day_name",ValidByName.NAME_FORMAT));
        return errors;
    }
}
