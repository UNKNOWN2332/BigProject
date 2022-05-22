package maktab.imthonproject.Resource;

import lombok.RequiredArgsConstructor;
import maktab.imthonproject.DAO.Teacher;
import maktab.imthonproject.DTO.ResponseDTO;
import maktab.imthonproject.DTO.StudentDTO;
import maktab.imthonproject.DTO.TeacherDTO;
import maktab.imthonproject.Service.StudentService;
import maktab.imthonproject.Service.TeacherService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("teacher")
public class TeacherResource {
    public final TeacherService teacherService;

    @GetMapping("get")
    public ResponseDTO<Page<TeacherDTO>> getAll(@RequestParam(defaultValue = "0") Integer page , @RequestParam(defaultValue = "5") Integer size)
    {
        return teacherService.getAll(page,size);
    }

    @PostMapping("add")
    public ResponseDTO<TeacherDTO> add(@RequestBody TeacherDTO teacherDTO)
    {

        return teacherService.add(teacherDTO);
    }

    @PutMapping("update")
    public ResponseDTO<TeacherDTO> update(@RequestBody TeacherDTO teacherDTO)
    {
        return teacherService.update(teacherDTO);
    }
    @DeleteMapping("delete/{id}")
    public ResponseDTO<TeacherDTO> delete(@PathVariable Integer id)
    {
        return teacherService.delet(id);
    }
}
