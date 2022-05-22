package maktab.imthonproject.Service;

import lombok.RequiredArgsConstructor;
import maktab.imthonproject.DAO.*;
import maktab.imthonproject.DAO.Class;
import maktab.imthonproject.DTO.ResponseDTO;
import maktab.imthonproject.DTO.TimeTableDTO;
import maktab.imthonproject.DTO.ValidDTO;
import maktab.imthonproject.Helper.ValidationChecks;
import maktab.imthonproject.Helper.Validation.ValidByCode;
import maktab.imthonproject.Helper.Validation.ValidByName;
import maktab.imthonproject.Mapping.TimeTableMapping;
import maktab.imthonproject.Repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimeTableService {
    private final TimeTableRepository timeTableRepository;
    private final ClassRepository classRepository;
    private  final RoomRepository roomRepository;
    private final LessonRepository lessonRepository;
    private final TeacherRepository teacherRepository;
    private final DayWeekRepository dayWeekRepository;

    public ResponseDTO<Page<TimeTableDTO>> getAll(Integer page,Integer size) {
        Pageable pageable = PageRequest.of(page,size); // bizaga barchasini nechtadan keyin bo'lishligini etamiz
        Page<Timetable> timetablePage=timeTableRepository.findAll(pageable);//bunda biza bergan page size ga qarab bolib beradi


        List<TimeTableDTO>  tableDTOList = timetablePage
                .stream().filter(a->a.getIsactive()==0)
                .map(TimeTableMapping::toDtos)
                .collect(Collectors.toList());// hamasini Dto ga ugurib beradi
        Page<TimeTableDTO> timeTableDTOS = new PageImpl<>(tableDTOList,pageable,timetablePage.getTotalElements());// bookni listni beradi nechtasi kerak bolgan bosa page size ga qaraydi nechiga bolib berishiligi uchun va ohiri umumiy qancha page va size borligini chiqarib beradi
        return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,timeTableDTOS,null);
    }

    public ResponseDTO<TimeTableDTO> add(TimeTableDTO timeTableDTO) {
        try {
            if(timeTableDTO.getId()!=null) return new ResponseDTO<>(false,ValidByCode.VALIDATION_ERROR,"Add qilyapkanda Id qo'shib bo'lmaydi",null,null);
            List<ValidDTO> list_valids = ValidationChecks.checkByTimeTable(timeTableDTO);
            if (list_valids.size() > 0) return new ResponseDTO<>(false,
                    ValidByCode.VALIDATION_ERROR,
                    ValidByName.VALIDATION_ERROR,
                    null,
                    list_valids);//validatsiya

            Timetable timetable = TimeTableMapping.toEntity(timeTableDTO);


            Class class_= classRepository.getById(timeTableDTO.getClassDTO().getId());
            Integer classCount = class_.getClass_count();
            Room room = roomRepository.getById(timeTableDTO.getRoomDTO().getId());
            Lesson lesson = lessonRepository.getById(timeTableDTO.getLessonDTOs().getId());
            Teacher teacher = teacherRepository.getById(timeTableDTO.getTeacherDTOs().getId());
            DayWeek dayWeek = dayWeekRepository.getById(timeTableDTO.getDay_week().getId());
            Integer roomCount = room.getRoom_capacity();
            if(roomCount>=classCount && room.getBusy()==false)
            {
                room.setBusy(true);
                timetable.setAClas(class_);
                timetable.setLessons(lesson);
                timetable.setRoom(room);
                timetable.setTeachers(teacher);
                timetable.setDayWeek(dayWeek);
                timeTableRepository.save(timetable);
                timeTableDTO.setId(timetable.getId());
                return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,timeTableDTO,null);}
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseDTO<>(false , ValidByCode.DATABASE_ERROR, ValidByName.DATABASE_ERROR,null,null);
        }
        return new ResponseDTO<>(false,ValidByCode.DATABASE_ERROR,"Bu hona band",null,null);
    }

    public ResponseDTO<TimeTableDTO> update(TimeTableDTO timeTableDTO) {
        try {
            if (timeTableDTO.getId() == null)
                return new ResponseDTO<>(false, ValidByCode.ID_NULL, ValidByName.ID_NULL, null, null);
            List<ValidDTO> validDTOList = ValidationChecks.checkByTimeTable(timeTableDTO);
            if (validDTOList.size() > 0)
                return new ResponseDTO<>(false, ValidByCode.VALIDATION_ERROR, ValidByName.VALIDATION_ERROR, null, validDTOList);

            if (!timeTableRepository.existsById(timeTableDTO.getId()))
                return new ResponseDTO<>(false, ValidByCode.NOT_FOUND, ValidByName.NOT_FOUND, null, null);


            Timetable timetable = TimeTableMapping.toEntity(timeTableDTO);

            timeTableRepository.save(timetable);
            return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,timeTableDTO,null);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseDTO<>(false, ValidByCode.DATABASE_ERROR, ValidByName.DATABASE_ERROR,null,null);
        }
     }


    public ResponseDTO<TimeTableDTO> delate(Integer id) {
        if(!timeTableRepository.existsById(id))
            return new ResponseDTO<>(false, ValidByCode.NOT_FOUND, ValidByName.NOT_FOUND,null,null);
        try {
            Timetable timetable = timeTableRepository.getById(id);
            timetable.setIsactive(-1);
            timeTableRepository.save(timetable);
            return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,null,null);
        }catch (Exception e )
        {
            System.out.println(e.getMessage());
            return new ResponseDTO<>(false, ValidByCode.DATABASE_ERROR, ValidByName.DATABASE_ERROR,null,null);
        }
    }
}
