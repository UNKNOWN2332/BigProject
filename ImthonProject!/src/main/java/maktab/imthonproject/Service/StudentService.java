package maktab.imthonproject.Service;

import lombok.RequiredArgsConstructor;
import maktab.imthonproject.DAO.Lesson;
import maktab.imthonproject.DAO.Student;
import maktab.imthonproject.DTO.LessonDTO;
import maktab.imthonproject.DTO.ResponseDTO;
import maktab.imthonproject.DTO.StudentDTO;
import maktab.imthonproject.DTO.ValidDTO;
import maktab.imthonproject.Helper.Validation.ValidByCode;
import maktab.imthonproject.Helper.Validation.ValidByName;
import maktab.imthonproject.Helper.ValidationChecks;
import maktab.imthonproject.Mapping.LessonMapping;
import maktab.imthonproject.Mapping.StudentMapping;
import maktab.imthonproject.Repository.LessonRepository;
import maktab.imthonproject.Repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public ResponseDTO<Page<StudentDTO>> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size); // bizaga barchasini nechtadan keyin bo'lishligini etamiz
        Page<Student> studentPage = studentRepository.findAll(pageable);//bunda biza bergan page size ga qarab bolib beradi

        List<StudentDTO> studentDTOList = studentPage
                .stream()
                .map(StudentMapping::toDto)
                .collect(Collectors.toList());// hamasini Dto ga ugurib beradi
        Page<StudentDTO> studentDTOS = new PageImpl<>(studentDTOList,pageable,studentPage.getTotalElements());// bookni listni beradi nechtasi kerak bolgan bosa page size ga qaraydi nechiga bolib berishiligi uchun va ohiri umumiy qancha page va size borligini chiqarib beradi
        return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,studentDTOS,null);
    }

    public ResponseDTO<StudentDTO> add(StudentDTO studentDTO) {
        try {
            if(studentDTO.getId()!=null) return new ResponseDTO<>(false,ValidByCode.VALIDATION_ERROR,"Add qilyapkanda Id qo'shib bo'lmaydi",null,null);

            List<ValidDTO> list_valids = ValidationChecks.checkByStudent(studentDTO);
            if(list_valids.size() > 0)
                new ResponseDTO<>(false,
                        ValidByCode.VALIDATION_ERROR,
                        ValidByName.VALIDATION_ERROR,
                        null,
                        list_valids);

            Student student = StudentMapping.toEntity(studentDTO);
            studentRepository.save(student);
            studentDTO.setId(student.getId());
            return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,studentDTO,null);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseDTO<>(false , ValidByCode.DATABASE_ERROR, ValidByName.DATABASE_ERROR,null,null);
        }
    }

    public ResponseDTO<StudentDTO> update(StudentDTO studentDTO) {
        try {
            if (studentDTO.getId() == null)
                return new ResponseDTO<>(false, ValidByCode.ID_NULL, ValidByName.ID_NULL, studentDTO, null);
            List<ValidDTO> validDTOList = ValidationChecks.checkByStudent(studentDTO);
            if(validDTOList.size() > 0)
                return new ResponseDTO<>(false, ValidByCode.VALIDATION_ERROR, ValidByName.VALIDATION_ERROR, studentDTO, validDTOList);

            if (!studentRepository.existsById(studentDTO.getId()))
                return new ResponseDTO<>(false, ValidByCode.NOT_FOUND, ValidByName.NOT_FOUND, studentDTO, null);

            Student student = StudentMapping.toEntity(studentDTO);

            studentRepository.save(student);
            return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,studentDTO,null);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseDTO<>(false, ValidByCode.DATABASE_ERROR, ValidByName.DATABASE_ERROR,null,null);
        }
    }


    public ResponseDTO<StudentDTO> delet(Integer id) {
        if(!studentRepository.existsById(id))
            return new ResponseDTO<>(false, ValidByCode.NOT_FOUND, ValidByName.NOT_FOUND,null,null);
        try {
            Student student = studentRepository.getById(id);
            student.setIsacteve(-1);
            studentRepository.save(student);
            return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,null,null);
        }catch (Exception e )
        {
            System.out.println(e.getMessage());
            return new ResponseDTO<>(false, ValidByCode.DATABASE_ERROR, ValidByName.DATABASE_ERROR,null,null);
        }
    }
}
