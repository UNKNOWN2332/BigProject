package maktab.imthonproject.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParentDTO {
    private Integer id;
    private String firstname;
    private String lastname;
    private Date birthdate;
    private String phonenumbers;

    public ParentDTO(String firstname, String lastname, Date birthdate, String phone_numbers) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.phonenumbers = phone_numbers;
    }
}
