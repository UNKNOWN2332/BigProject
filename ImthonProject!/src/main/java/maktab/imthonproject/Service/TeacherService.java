package maktab.imthonproject.Service;

import lombok.RequiredArgsConstructor;
import maktab.imthonproject.DAO.Student;
import maktab.imthonproject.DAO.Teacher;
import maktab.imthonproject.DTO.ResponseDTO;
import maktab.imthonproject.DTO.StudentDTO;
import maktab.imthonproject.DTO.TeacherDTO;
import maktab.imthonproject.DTO.ValidDTO;
import maktab.imthonproject.Helper.Validation.ValidByCode;
import maktab.imthonproject.Helper.Validation.ValidByName;
import maktab.imthonproject.Helper.ValidationChecks;
import maktab.imthonproject.Mapping.StudentMapping;
import maktab.imthonproject.Mapping.TeacherMapping;
import maktab.imthonproject.Repository.TeacherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public ResponseDTO<Page<TeacherDTO>> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size); // bizaga barchasini nechtadan keyin bo'lishligini etamiz
        Page<Teacher> teacherPage = teacherRepository.findAll(pageable);//bunda biza bergan page size ga qarab bolib beradi

        List<TeacherDTO> teacherDTOList = teacherPage
                .stream()
                .map(TeacherMapping::toDto)
                .collect(Collectors.toList());// hamasini Dto ga ugurib beradi
        Page<TeacherDTO> teacherDTOS = new PageImpl<>(teacherDTOList,pageable,teacherPage.getTotalElements());// bookni listni beradi nechtasi kerak bolgan bosa page size ga qaraydi nechiga bolib berishiligi uchun va ohiri umumiy qancha page va size borligini chiqarib beradi
        return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,teacherDTOS,null);
    }

    public ResponseDTO<TeacherDTO> add(TeacherDTO teacherDTO) {
        try {
            if(teacherDTO.getId()!=null) return new ResponseDTO<>(false,ValidByCode.VALIDATION_ERROR,"Add qilyapkanda Id qo'shib bo'lmaydi",null,null);

            List<ValidDTO> list_valids = ValidationChecks.checkByTeacher(teacherDTO);
            if(list_valids.size() > 0)
                new ResponseDTO<>(false,
                        ValidByCode.VALIDATION_ERROR,
                        ValidByName.VALIDATION_ERROR,
                        null,
                        list_valids);

            Teacher teacher = TeacherMapping.toEntity(teacherDTO);
            teacherRepository.save(teacher);
            teacherDTO.setId(teacher.getId());
            return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,teacherDTO,null);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseDTO<>(false , ValidByCode.DATABASE_ERROR, ValidByName.DATABASE_ERROR,null,null);
        }
    }

    public ResponseDTO<TeacherDTO> update(TeacherDTO teacherDTO) {
        try {
            if (teacherDTO.getId() == null)
                return new ResponseDTO<>(false, ValidByCode.ID_NULL, ValidByName.ID_NULL, teacherDTO, null);
            List<ValidDTO> validDTOList = ValidationChecks.checkByTeacher(teacherDTO);
            if(validDTOList.size() > 0)
                return new ResponseDTO<>(false, ValidByCode.VALIDATION_ERROR, ValidByName.VALIDATION_ERROR, teacherDTO, validDTOList);

            if (!teacherRepository.existsById(teacherDTO.getId()))
                return new ResponseDTO<>(false, ValidByCode.NOT_FOUND, ValidByName.NOT_FOUND, teacherDTO, null);

            Teacher teacher = TeacherMapping.toEntity(teacherDTO);

            teacherRepository.save(teacher);
            return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,teacherDTO,null);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseDTO<>(false, ValidByCode.DATABASE_ERROR, ValidByName.DATABASE_ERROR,null,null);
        }
    }


    public ResponseDTO<TeacherDTO> delet(Integer id) {
        if(!teacherRepository.existsById(id))
            return new ResponseDTO<>(false, ValidByCode.NOT_FOUND, ValidByName.NOT_FOUND,null,null);
        try {
            Teacher teacher = teacherRepository.getById(id);
            teacher.setIsactive(-1);
            teacherRepository.save(teacher);
            return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,null,null);
        }catch (Exception e )
        {
            System.out.println(e.getMessage());
            return new ResponseDTO<>(false, ValidByCode.DATABASE_ERROR, ValidByName.DATABASE_ERROR,null,null);
        }
    }
}
