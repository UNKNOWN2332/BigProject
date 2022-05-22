package maktab.imthonproject.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Parent {
        @Id
        @GeneratedValue(generator = "parent_id_seq")
        @SequenceGenerator(name = "parent_id_seq",sequenceName = "parent_id_seq",allocationSize = 1)
        private Integer id;
        private String firstname;
        private String lastname;
        private Date birthdate;
        @Column(name = "phonenumber")
        private String phonenumbers;
        private Integer isactive=0;

    public Parent(Integer id, String firstname, String lastname, Date birthdate, String phone_numbers) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.phonenumbers = phone_numbers;
    }
}
