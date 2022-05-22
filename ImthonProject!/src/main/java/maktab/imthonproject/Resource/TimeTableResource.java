package maktab.imthonproject.Resource;

import lombok.RequiredArgsConstructor;
import maktab.imthonproject.DTO.ResponseDTO;
import maktab.imthonproject.DTO.TimeTableDTO;
import maktab.imthonproject.Service.TimeTableService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rosp")
public class TimeTableResource {
    public final TimeTableService timeTableService;

    @GetMapping("get")
    public ResponseDTO<Page<TimeTableDTO>> getAll(@RequestParam(defaultValue = "0") Integer page , @RequestParam(defaultValue = "5") Integer size)
    {
        return timeTableService.getAll(page,size);
    }

    @PostMapping("add")
    public ResponseDTO<TimeTableDTO> add(@RequestBody TimeTableDTO timeTableDTO)
    {
        return timeTableService.add(timeTableDTO);
    }

    @PutMapping("update")
    public ResponseDTO<TimeTableDTO> update(@RequestBody TimeTableDTO timeTableDTO)
    {
        return timeTableService.update(timeTableDTO);
    }
    @DeleteMapping("delete/{id}")
    public ResponseDTO<TimeTableDTO> delete(@PathVariable Integer id)
    {
        return timeTableService.delate(id);
    }

}
