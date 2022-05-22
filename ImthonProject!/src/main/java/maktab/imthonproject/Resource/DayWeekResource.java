package maktab.imthonproject.Resource;

import lombok.RequiredArgsConstructor;
import maktab.imthonproject.DTO.DayWeekDTO;
import maktab.imthonproject.DTO.LessonDTO;
import maktab.imthonproject.DTO.ResponseDTO;
import maktab.imthonproject.Service.DayWeekService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("day_week")
public class DayWeekResource {
    private final DayWeekService dayWeekService;

    @GetMapping("get")
    public ResponseDTO<Page<DayWeekDTO>> getAll(@RequestParam(defaultValue = "0") Integer page , @RequestParam(defaultValue = "5") Integer size)
    {
        return dayWeekService.getAll(page,size);
    }

    @PostMapping("add")
    public ResponseDTO<DayWeekDTO> add(@RequestBody DayWeekDTO dayWeekDTO)
    {

        return dayWeekService.add(dayWeekDTO);
    }

    @PutMapping("update")
    public ResponseDTO<DayWeekDTO> update(@RequestBody DayWeekDTO dayWeekDTO)
    {
        return dayWeekService.update(dayWeekDTO);
    }
    @DeleteMapping("delete/{id}")
    public ResponseDTO<DayWeekDTO> delete(@PathVariable Integer id)
    {
        return dayWeekService.delet(id);
    }

}
