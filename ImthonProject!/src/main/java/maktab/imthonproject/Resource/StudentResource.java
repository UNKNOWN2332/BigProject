package maktab.imthonproject.Resource;

import lombok.RequiredArgsConstructor;
import maktab.imthonproject.DTO.LessonDTO;
import maktab.imthonproject.DTO.ResponseDTO;
import maktab.imthonproject.DTO.StudentDTO;
import maktab.imthonproject.Service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("student")
public class StudentResource {
    public final StudentService studentService;

    @GetMapping("get")
    public ResponseDTO<Page<StudentDTO>> getAll(@RequestParam(defaultValue = "0") Integer page , @RequestParam(defaultValue = "5") Integer size)
    {
        return studentService.getAll(page,size);
    }

    @PostMapping("add")
    public ResponseDTO<StudentDTO> add(@RequestBody StudentDTO studentDTO)
    {

        return studentService.add(studentDTO);
    }

    @PutMapping("update")
    public ResponseDTO<StudentDTO> update(@RequestBody StudentDTO studentDTO)
    {
        return studentService.update(studentDTO);
    }
    @DeleteMapping("delete/{id}")
    public ResponseDTO<StudentDTO> delete(@PathVariable Integer id)
    {
        return studentService.delet(id);
    }
}
