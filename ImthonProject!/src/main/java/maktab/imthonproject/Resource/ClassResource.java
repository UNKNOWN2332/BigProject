package maktab.imthonproject.Resource;

import lombok.RequiredArgsConstructor;
import maktab.imthonproject.DTO.ClassDTO;
import maktab.imthonproject.DTO.ResponseDTO;
import maktab.imthonproject.DTO.TimeTableDTO;
import maktab.imthonproject.Service.ClassService;
import maktab.imthonproject.Service.TimeTableService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("class")
public class ClassResource {
    public final ClassService classService;

    @GetMapping("get")
    public ResponseDTO<Page<ClassDTO>> getAll(@RequestParam(defaultValue = "0") Integer page , @RequestParam(defaultValue = "5") Integer size)
    {
        return classService.getAll(page,size);
    }

    @PostMapping("add")
    public ResponseDTO<ClassDTO> add(@RequestBody ClassDTO classDTO)
    {
        return classService.add(classDTO);
    }

    @PutMapping("update")
    public ResponseDTO<ClassDTO> update(@RequestBody ClassDTO classDTO)
    {
        return classService.update(classDTO);
    }
    @DeleteMapping("delete/{id}")
    public ResponseDTO<ClassDTO> delete(@PathVariable Integer id)
    {
        return classService.delet(id);
    }

}
