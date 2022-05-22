package maktab.imthonproject.Mapping;

import maktab.imthonproject.DAO.Parent;
import maktab.imthonproject.DTO.ParentDTO;

public class ParentMapping {
    public static ParentDTO toDto(Parent parent)
    {
        return parent==null?null:new ParentDTO(
                parent.getId(),
                parent.getFirstname(),
                parent.getLastname(),
                parent.getBirthdate(),
                parent.getLastname()
        );
    }
    public static Parent toEntity(ParentDTO parentDTO)
    {
        return parentDTO==null?null:new Parent(
                parentDTO.getId(),
                parentDTO.getFirstname(),
                parentDTO.getLastname(),
                parentDTO.getBirthdate(),
                parentDTO.getPhonenumbers()

        );
    }
}
