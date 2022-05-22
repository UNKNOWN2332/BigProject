package maktab.imthonproject.Resource;

import lombok.RequiredArgsConstructor;
import maktab.imthonproject.DTO.ParentDTO;
import maktab.imthonproject.DTO.ResponseDTO;
import maktab.imthonproject.Repository.ParentRepository;
import maktab.imthonproject.Service.ParentService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("parent")
public class ParentResource {
    public final ParentService parentService;

    @GetMapping("get")
    public ResponseDTO<Page<ParentDTO>> getAll(@RequestParam(defaultValue = "0") Integer page , @RequestParam(defaultValue = "5") Integer size)
    {
        return parentService.getAll(page,size);
    }

    @PostMapping("add")
    public ResponseDTO<ParentDTO> add(@RequestBody ParentDTO parentDTO)
    {

        return parentService.add(parentDTO);
    }

    @PutMapping("update")
    public ResponseDTO<ParentDTO> update(@RequestBody ParentDTO parentDTO)
    {
        return parentService.update(parentDTO);
    }
    @DeleteMapping("delete/{id}")
    public ResponseDTO<ParentDTO> delete(@PathVariable Integer id)
    {
        return parentService.delet(id);
    }
}
