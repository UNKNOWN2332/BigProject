package maktab.imthonproject.DAO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(generator = "room_id_seq")
    @SequenceGenerator(name = "room_id_seq", sequenceName = "room_id_seq", allocationSize = 1)
    private Integer id;

    private Integer roomNumber;
    private String roomName;

    private Integer room_capacity;
    private Boolean busy;
    private Integer isactive=0;

    public Room(Integer id, Integer roomNumber, String roomName) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.roomName = roomName;
    }

}
