package maktab.imthonproject.Service;

import lombok.RequiredArgsConstructor;
import maktab.imthonproject.DAO.Parent;
import maktab.imthonproject.DAO.Student;
import maktab.imthonproject.DTO.ParentDTO;
import maktab.imthonproject.DTO.ResponseDTO;
import maktab.imthonproject.DTO.StudentDTO;
import maktab.imthonproject.DTO.ValidDTO;
import maktab.imthonproject.Helper.Validation.ValidByCode;
import maktab.imthonproject.Helper.Validation.ValidByName;
import maktab.imthonproject.Helper.ValidationChecks;
import maktab.imthonproject.Mapping.ParentMapping;
import maktab.imthonproject.Mapping.StudentMapping;
import maktab.imthonproject.Repository.ParentRepository;
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
public class ParentService {
    private final ParentRepository parentRepository;

    public ResponseDTO<Page<ParentDTO>> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size); // bizaga barchasini nechtadan keyin bo'lishligini etamiz
        Page<Parent> parentPage = parentRepository.findAll(pageable);//bunda biza bergan page size ga qarab bolib beradi

        List<ParentDTO> parentDTOList = parentPage
                .stream()
                .map(ParentMapping::toDto)
                .collect(Collectors.toList());// hamasini Dto ga ugurib beradi
        Page<ParentDTO> parentDTOS = new PageImpl<>(parentDTOList,pageable,parentPage.getTotalElements());// bookni listni beradi nechtasi kerak bolgan bosa page size ga qaraydi nechiga bolib berishiligi uchun va ohiri umumiy qancha page va size borligini chiqarib beradi
        return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,parentDTOS,null);
    }

    public ResponseDTO<ParentDTO> add(ParentDTO parentDTO) {
        try {
            if(parentDTO.getId()!=null) return new ResponseDTO<>(false,ValidByCode.VALIDATION_ERROR,"Add qilyapkanda Id qo'shib bo'lmaydi",null,null);

            List<ValidDTO> list_valids = ValidationChecks.checkByParent(parentDTO);
            if(list_valids.size() > 0)
                new ResponseDTO<>(false,
                        ValidByCode.VALIDATION_ERROR,
                        ValidByName.VALIDATION_ERROR,
                        null,
                        list_valids);

            Parent parent = ParentMapping.toEntity(parentDTO);
            parentRepository.save(parent);
            parentDTO.setId(parent.getId());
            return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,parentDTO,null);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseDTO<>(false , ValidByCode.DATABASE_ERROR, ValidByName.DATABASE_ERROR,null,null);
        }
    }

    public ResponseDTO<ParentDTO> update(ParentDTO parentDTO) {
        try {
            if (parentDTO.getId() == null)
                return new ResponseDTO<>(false, ValidByCode.ID_NULL, ValidByName.ID_NULL, parentDTO, null);
            List<ValidDTO> validDTOList = ValidationChecks.checkByParent(parentDTO);
            if(validDTOList.size() > 0)
                return new ResponseDTO<>(false, ValidByCode.VALIDATION_ERROR, ValidByName.VALIDATION_ERROR, parentDTO, validDTOList);

            if (!parentRepository.existsById(parentDTO.getId()))
                return new ResponseDTO<>(false, ValidByCode.NOT_FOUND, ValidByName.NOT_FOUND, parentDTO, null);

            Parent parent = ParentMapping.toEntity(parentDTO);

            parentRepository.save(parent);
            return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,parentDTO,null);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseDTO<>(false, ValidByCode.DATABASE_ERROR, ValidByName.DATABASE_ERROR,null,null);
        }
    }


    public ResponseDTO<ParentDTO> delet(Integer id) {
        if(!parentRepository.existsById(id))
            return new ResponseDTO<>(false, ValidByCode.NOT_FOUND, ValidByName.NOT_FOUND,null,null);
        try {
            Parent parent = parentRepository.getById(id);
            parent.setIsactive(-1);
            parentRepository.save(parent);
            return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,null,null);
        }catch (Exception e )
        {
            System.out.println(e.getMessage());
            return new ResponseDTO<>(false, ValidByCode.DATABASE_ERROR, ValidByName.DATABASE_ERROR,null,null);
        }
    }
}
