package maktab.imthonproject.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDTO<T> {

    private boolean access;
    private Integer code;
    private String message;
    private T data;
    private List<ValidDTO> validDTOList;

}
