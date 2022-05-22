package maktab.imthonproject.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    private Integer id;

    private Integer roomNumber;
    private String roomName;
    private Integer room_capacity;

    private Boolean busy;

    public RoomDTO(Integer id, Integer roomNumber, String roomName, Integer room_capacity) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.roomName = roomName;
        this.room_capacity = room_capacity;
    }

}
