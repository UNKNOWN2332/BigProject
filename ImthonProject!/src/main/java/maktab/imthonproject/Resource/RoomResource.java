package maktab.imthonproject.Resource;

import lombok.RequiredArgsConstructor;
import maktab.imthonproject.DTO.ResponseDTO;
import maktab.imthonproject.DTO.RoomDTO;
import maktab.imthonproject.DTO.StudentDTO;
import maktab.imthonproject.Service.RoomService;
import maktab.imthonproject.Service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("room")
public class RoomResource {
    public final RoomService roomService;

    @GetMapping("get")
    public ResponseDTO<Page<RoomDTO>> getAll(@RequestParam(defaultValue = "0") Integer page , @RequestParam(defaultValue = "5") Integer size)
    {
        return roomService.getAll(page,size);
    }

    @PostMapping("add")
    public ResponseDTO<RoomDTO> add(@RequestBody RoomDTO roomDTO)
    {

        return roomService.add(roomDTO);
    }

    @PutMapping("update")
    public ResponseDTO<RoomDTO> update(@RequestBody RoomDTO roomDTO)
    {
        return roomService.update(roomDTO);
    }
    @DeleteMapping("delete/{id}")
    public ResponseDTO<RoomDTO> delete(@PathVariable Integer id)
    {
        return roomService.delet(id);
    }
}
