package maktab.imthonproject.Service;

import lombok.RequiredArgsConstructor;
import maktab.imthonproject.DAO.Class;
import maktab.imthonproject.DAO.Lesson;
import maktab.imthonproject.DTO.ClassDTO;
import maktab.imthonproject.DTO.LessonDTO;
import maktab.imthonproject.DTO.ResponseDTO;
import maktab.imthonproject.DTO.ValidDTO;
import maktab.imthonproject.Helper.Validation.ValidByCode;
import maktab.imthonproject.Helper.Validation.ValidByName;
import maktab.imthonproject.Helper.ValidationChecks;
import maktab.imthonproject.Mapping.ClassMapping;
import maktab.imthonproject.Mapping.LessonMapping;
import maktab.imthonproject.Repository.ClassRepository;
import maktab.imthonproject.Repository.LessonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;

    public ResponseDTO<Page<LessonDTO>> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size); // bizaga barchasini nechtadan keyin bo'lishligini etamiz
        Page<Lesson> lessonPages = lessonRepository.findAll(pageable);//bunda biza bergan page size ga qarab bolib beradi

        List<LessonDTO> lessonDTOList = lessonPages
                .stream()
                .map(LessonMapping::toDtos)
                .collect(Collectors.toList());// hamasini Dto ga ugurib beradi
        Page<LessonDTO> lessonDTOS = new PageImpl<>(lessonDTOList,pageable,lessonPages.getTotalElements());// bookni listni beradi nechtasi kerak bolgan bosa page size ga qaraydi nechiga bolib berishiligi uchun va ohiri umumiy qancha page va size borligini chiqarib beradi
        return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,lessonDTOS,null);
    }

    public ResponseDTO<LessonDTO> add(LessonDTO lessonDTO) {
        try {
            if(lessonDTO.getId()!=null) return new ResponseDTO<>(false,ValidByCode.VALIDATION_ERROR,"Add qilyapkanda Id qo'shib bo'lmaydi",null,null);

            List<ValidDTO> list_valids = ValidationChecks.checkByLesons(lessonDTO);
            if(list_valids.size() > 0)
                new ResponseDTO<>(false,
                        ValidByCode.VALIDATION_ERROR,
                        ValidByName.VALIDATION_ERROR,
                        null,
                        list_valids);

            Lesson lesson = LessonMapping.toEntity(lessonDTO);
            lessonRepository.save(lesson);
            lessonDTO.setId(lesson.getId());
            return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,lessonDTO,null);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseDTO<>(false , ValidByCode.DATABASE_ERROR, ValidByName.DATABASE_ERROR,null,null);
        }
    }

    public ResponseDTO<LessonDTO> update(LessonDTO lessonDTO) {
        try {
            if (lessonDTO.getId() == null)
                return new ResponseDTO<>(false, ValidByCode.ID_NULL, ValidByName.ID_NULL, lessonDTO, null);
            List<ValidDTO> validDTOList = ValidationChecks.checkByLesons(lessonDTO);
            if(validDTOList.size() > 0)
                return new ResponseDTO<>(false, ValidByCode.VALIDATION_ERROR, ValidByName.VALIDATION_ERROR, lessonDTO, validDTOList);

            if (!lessonRepository.existsById(lessonDTO.getId()))
                return new ResponseDTO<>(false, ValidByCode.NOT_FOUND, ValidByName.NOT_FOUND, lessonDTO, null);

            Lesson lesson = LessonMapping.toEntity(lessonDTO);

            lessonRepository.save(lesson);
            return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,lessonDTO,null);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseDTO<>(false, ValidByCode.DATABASE_ERROR, ValidByName.DATABASE_ERROR,null,null);
        }
    }


    public ResponseDTO<LessonDTO> delet(Integer id) {
        if(!lessonRepository.existsById(id))
            return new ResponseDTO<>(false, ValidByCode.NOT_FOUND, ValidByName.NOT_FOUND,null,null);
        try {
            Lesson lesson = lessonRepository.getById(id);
            lesson.setIsactive(-1);
            lessonRepository.save(lesson);
            return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,null,null);
        }catch (Exception e )
        {
            System.out.println(e.getMessage());
            return new ResponseDTO<>(false, ValidByCode.DATABASE_ERROR, ValidByName.DATABASE_ERROR,null,null);
        }
    }
}
