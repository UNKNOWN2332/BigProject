package maktab.imthonproject.Service;

import lombok.RequiredArgsConstructor;
import maktab.imthonproject.DAO.DayWeek;
import maktab.imthonproject.DTO.DayWeekDTO;
import maktab.imthonproject.DTO.ResponseDTO;
import maktab.imthonproject.DTO.ValidDTO;
import maktab.imthonproject.Helper.Validation.ValidByCode;
import maktab.imthonproject.Helper.Validation.ValidByName;
import maktab.imthonproject.Helper.ValidationChecks;
import maktab.imthonproject.Mapping.DayWeekMapping;
import maktab.imthonproject.Repository.DayWeekRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DayWeekService {
    private final DayWeekRepository dayWeekRepository;

    public ResponseDTO<Page<DayWeekDTO>> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size); // bizaga barchasini nechtadan keyin bo'lishligini etamiz
        Page<DayWeek> dayWeekPage = dayWeekRepository.findAll(pageable);//bunda biza bergan page size ga qarab bolib beradi

        List<DayWeekDTO> dayWeekDTOList = dayWeekPage
                .stream()
                .map(DayWeekMapping::toDto)
                .collect(Collectors.toList());// hamasini Dto ga ugurib beradi
        Page<DayWeekDTO> lessonDTOS = new PageImpl<>(dayWeekDTOList,pageable,dayWeekPage.getTotalElements());// bookni listni beradi nechtasi kerak bolgan bosa page size ga qaraydi nechiga bolib berishiligi uchun va ohiri umumiy qancha page va size borligini chiqarib beradi
        return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,lessonDTOS,null);
    }

    public ResponseDTO<DayWeekDTO> add(DayWeekDTO dayWeekDTO) {
        try {
            if(dayWeekDTO.getId()!=null) return new ResponseDTO<>(false,ValidByCode.VALIDATION_ERROR,"Add qilyapkanda Id qo'shib bo'lmaydi",null,null);

            List<ValidDTO> list_valids = ValidationChecks.checkByDayWeek(dayWeekDTO);
            if(list_valids.size() > 0)
                new ResponseDTO<>(false,
                        ValidByCode.VALIDATION_ERROR,
                        ValidByName.VALIDATION_ERROR,
                        null,
                        list_valids);

            DayWeek dayWeek = DayWeekMapping.toEntity(dayWeekDTO);
            dayWeekRepository.save(dayWeek);
            dayWeekDTO.setId(dayWeek.getId());
            return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,dayWeekDTO,null);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseDTO<>(false , ValidByCode.DATABASE_ERROR, ValidByName.DATABASE_ERROR,null,null);
        }
    }

    public ResponseDTO<DayWeekDTO> update(DayWeekDTO dayWeekDTO) {
        try {
            if (dayWeekDTO.getId() == null)
                return new ResponseDTO<>(false, ValidByCode.ID_NULL, ValidByName.ID_NULL, dayWeekDTO, null);
            List<ValidDTO> validDTOList = ValidationChecks.checkByDayWeek(dayWeekDTO);
            if(validDTOList.size() > 0)
                return new ResponseDTO<>(false, ValidByCode.VALIDATION_ERROR, ValidByName.VALIDATION_ERROR, dayWeekDTO, validDTOList);

            if (!dayWeekRepository.existsById(dayWeekDTO.getId()))
                return new ResponseDTO<>(false, ValidByCode.NOT_FOUND, ValidByName.NOT_FOUND, dayWeekDTO, null);

            DayWeek dayWeek = DayWeekMapping.toEntity(dayWeekDTO);

            dayWeekRepository.save(dayWeek);
            return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,dayWeekDTO,null);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseDTO<>(false, ValidByCode.DATABASE_ERROR, ValidByName.DATABASE_ERROR,null,null);
        }
    }


    public ResponseDTO<DayWeekDTO> delet(Integer id) {
        if(!dayWeekRepository.existsById(id))
            return new ResponseDTO<>(false, ValidByCode.NOT_FOUND, ValidByName.NOT_FOUND,null,null);
        try {
            DayWeek dayWeek = dayWeekRepository.getById(id);
            dayWeek.setIsactive(-1);
            dayWeekRepository.save(dayWeek);
            return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,null,null);
        }catch (Exception e )
        {
            System.out.println(e.getMessage());
            return new ResponseDTO<>(false, ValidByCode.DATABASE_ERROR, ValidByName.DATABASE_ERROR,null,null);
        }
    }
}
