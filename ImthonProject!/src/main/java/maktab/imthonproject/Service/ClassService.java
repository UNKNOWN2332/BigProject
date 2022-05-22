package maktab.imthonproject.Service;

import lombok.RequiredArgsConstructor;
import maktab.imthonproject.DAO.Class;
import maktab.imthonproject.DAO.Timetable;
import maktab.imthonproject.DTO.ClassDTO;
import maktab.imthonproject.DTO.ResponseDTO;
import maktab.imthonproject.DTO.TimeTableDTO;
import maktab.imthonproject.DTO.ValidDTO;
import maktab.imthonproject.Helper.Validation.ValidByCode;
import maktab.imthonproject.Helper.Validation.ValidByName;
import maktab.imthonproject.Helper.ValidationChecks;
import maktab.imthonproject.Mapping.ClassMapping;
import maktab.imthonproject.Mapping.TimeTableMapping;
import maktab.imthonproject.Repository.ClassRepository;
import maktab.imthonproject.Repository.TimeTableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class ClassService {
    private final ClassRepository classRepository;
    private final TimeTableRepository timeTableRepository;

    public ResponseDTO<Page<ClassDTO>> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size); // bizaga barchasini nechtadan keyin bo'lishligini etamiz
        Page<Class> classPage = classRepository.findAll(pageable);//bunda biza bergan page size ga qarab bolib beradi

        List<ClassDTO> classDTOList = classPage
                .stream()
                .map(ClassMapping::toDtos)
                .collect(Collectors.toList());// hamasini Dto ga ugurib beradi
        Page<ClassDTO> classDTOS = new PageImpl<>(classDTOList,pageable,classPage.getTotalElements());// bookni listni beradi nechtasi kerak bolgan bosa page size ga qaraydi nechiga bolib berishiligi uchun va ohiri umumiy qancha page va size borligini chiqarib beradi
        return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,classDTOS,null);
    }

    public ResponseDTO<ClassDTO> add(ClassDTO classDTO) {
        try {
            if(classDTO.getId()!=null) return new ResponseDTO<>(false,ValidByCode.VALIDATION_ERROR,"Add qilyapkanda Id qo'shib bo'lmaydi",null,null);

            List<ValidDTO> list_valids = ValidationChecks.checkByClass(classDTO);
            if(list_valids.size()>0)
                return new ResponseDTO<>(false,
                        ValidByCode.VALIDATION_ERROR,
                        ValidByName.VALIDATION_ERROR,
                        null,
                        list_valids);



            Class aClass = ClassMapping.toEntity(classDTO);


            classRepository.save(aClass);
            classDTO.setId(aClass.getId());
            return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,classDTO,null);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseDTO<>(false , ValidByCode.DATABASE_ERROR, ValidByName.DATABASE_ERROR,null,null);
        }
    }

    public ResponseDTO<ClassDTO> update(ClassDTO classDTO) {
        try {


            if (classDTO.getId() == null)
                return new ResponseDTO<>(false, ValidByCode.ID_NULL, ValidByName.ID_NULL, classDTO, null);
            List<ValidDTO> validDTOList = ValidationChecks.checkByClass(classDTO);
            if(validDTOList.size() > 0)
                return new ResponseDTO<>(false, ValidByCode.VALIDATION_ERROR, ValidByName.VALIDATION_ERROR, classDTO, validDTOList);

            if (!classRepository.existsById(classDTO.getId()))
                return new ResponseDTO<>(false, ValidByCode.NOT_FOUND, ValidByName.NOT_FOUND, classDTO, null);





            Class aClass = ClassMapping.toEntity(classDTO);


            classRepository.save(aClass);
            return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,classDTO,null);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseDTO<>(false, ValidByCode.DATABASE_ERROR, ValidByName.DATABASE_ERROR,null,null);
        }
    }


    public ResponseDTO<ClassDTO> delet(Integer id) {
        if(!classRepository.existsById(id))
            return new ResponseDTO<>(false, ValidByCode.NOT_FOUND, ValidByName.NOT_FOUND,null,null);
        try {
            Class aClass = classRepository.getById(id);
            aClass.setIsactive(-1);
            classRepository.save(aClass);
            return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,null,null);
        }catch (Exception e )
        {
            System.out.println(e.getMessage());
            return new ResponseDTO<>(false, ValidByCode.DATABASE_ERROR, ValidByName.DATABASE_ERROR,null,null);
        }
    }
}
