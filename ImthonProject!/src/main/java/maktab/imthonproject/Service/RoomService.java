package maktab.imthonproject.Service;

import lombok.RequiredArgsConstructor;
import maktab.imthonproject.DAO.Room;
import maktab.imthonproject.DAO.Student;
import maktab.imthonproject.DTO.ResponseDTO;
import maktab.imthonproject.DTO.RoomDTO;
import maktab.imthonproject.DTO.StudentDTO;
import maktab.imthonproject.DTO.ValidDTO;
import maktab.imthonproject.Helper.Validation.ValidByCode;
import maktab.imthonproject.Helper.Validation.ValidByName;
import maktab.imthonproject.Helper.ValidationChecks;
import maktab.imthonproject.Mapping.RoomMapping;
import maktab.imthonproject.Mapping.StudentMapping;
import maktab.imthonproject.Repository.RoomRepository;
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
public class RoomService {
    private final RoomRepository roomRepository;

    public ResponseDTO<Page<RoomDTO>> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size); // bizaga barchasini nechtadan keyin bo'lishligini etamiz
        Page<Room> roomPage = roomRepository.findAll(pageable);//bunda biza bergan page size ga qarab bolib beradi

        List<RoomDTO> roomDTOList = roomPage
                .stream()
                .map(RoomMapping::toDto)
                .collect(Collectors.toList());// hamasini Dto ga ugurib beradi
        Page<RoomDTO> roomDTOS = new PageImpl<>(roomDTOList,pageable,roomPage.getTotalElements());// bookni listni beradi nechtasi kerak bolgan bosa page size ga qaraydi nechiga bolib berishiligi uchun va ohiri umumiy qancha page va size borligini chiqarib beradi
        return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,roomDTOS,null);
    }

    public ResponseDTO<RoomDTO> add(RoomDTO roomDTO) {
        try {
            if(roomDTO.getId()!=null) return new ResponseDTO<>(false,ValidByCode.VALIDATION_ERROR,"Add qilyapkanda Id qo'shib bo'lmaydi",null,null);

            List<ValidDTO> list_valids = ValidationChecks.checkByRoom(roomDTO);
            if(list_valids.size() > 0)
                new ResponseDTO<>(false,
                        ValidByCode.VALIDATION_ERROR,
                        ValidByName.VALIDATION_ERROR,
                        null,
                        list_valids);

            Room room = RoomMapping.toEntity(roomDTO);
            roomRepository.save(room);
            roomDTO.setId(room.getId());
            return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,roomDTO,null);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseDTO<>(false , ValidByCode.DATABASE_ERROR, ValidByName.DATABASE_ERROR,null,null);
        }
    }

    public ResponseDTO<RoomDTO> update(RoomDTO roomDTO) {
        try {
            if (roomDTO.getId() == null)
                return new ResponseDTO<>(false, ValidByCode.ID_NULL, ValidByName.ID_NULL, roomDTO, null);
            List<ValidDTO> validDTOList = ValidationChecks.checkByRoom(roomDTO);
            if(validDTOList.size() > 0)
                return new ResponseDTO<>(false, ValidByCode.VALIDATION_ERROR, ValidByName.VALIDATION_ERROR, roomDTO, validDTOList);

            if (!roomRepository.existsById(roomDTO.getId()))
                return new ResponseDTO<>(false, ValidByCode.NOT_FOUND, ValidByName.NOT_FOUND, roomDTO, null);

            Room room = RoomMapping.toEntity(roomDTO);

            roomRepository.save(room);
            return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,roomDTO,null);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return new ResponseDTO<>(false, ValidByCode.DATABASE_ERROR, ValidByName.DATABASE_ERROR,null,null);
        }
    }


    public ResponseDTO<RoomDTO> delet(Integer id) {
        if(!roomRepository.existsById(id))
            return new ResponseDTO<>(false, ValidByCode.NOT_FOUND, ValidByName.NOT_FOUND,null,null);
        try {
            Room room = roomRepository.getById(id);
            room.setIsactive(-1);
            roomRepository.save(room);
            return new ResponseDTO<>(true, ValidByCode.OK, ValidByName.OK,null,null);
        }catch (Exception e )
        {
            System.out.println(e.getMessage());
            return new ResponseDTO<>(false, ValidByCode.DATABASE_ERROR, ValidByName.DATABASE_ERROR,null,null);
        }
    }
}
