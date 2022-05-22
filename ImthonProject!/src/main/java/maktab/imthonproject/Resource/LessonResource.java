package maktab.imthonproject.Resource;

import lombok.RequiredArgsConstructor;
import maktab.imthonproject.DTO.LessonDTO;
import maktab.imthonproject.DTO.ResponseDTO;
import maktab.imthonproject.Service.LessonService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
@RequestMapping("lesoon")
public class LessonResource {
    public final LessonService lessonService;

    @GetMapping("get")
    public ResponseDTO<Page<LessonDTO>> getAll(@RequestParam(defaultValue = "0") Integer page , @RequestParam(defaultValue = "5") Integer size)
    {
        return lessonService.getAll(page,size);
    }

    @PostMapping("add")
    public ResponseDTO<LessonDTO> add(@RequestBody LessonDTO lessonDTO)
    {

        return lessonService.add(lessonDTO);
    }

    @PutMapping("update")
    public ResponseDTO<LessonDTO> update(@RequestBody LessonDTO lessonDTO)
    {
        return lessonService.update(lessonDTO);
    }
    @DeleteMapping("delete/{id}")
    public ResponseDTO<LessonDTO> delete(@PathVariable Integer id)
    {
        return lessonService.delet(id);
    }

}
