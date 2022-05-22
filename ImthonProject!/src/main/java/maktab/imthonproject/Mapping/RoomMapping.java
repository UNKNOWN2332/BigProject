package maktab.imthonproject.Mapping;

import maktab.imthonproject.DAO.Room;
import maktab.imthonproject.DTO.RoomDTO;

import java.util.List;

public class RoomMapping {
    public static RoomDTO toDto(Room room)
    {
        return room==null ? null : new RoomDTO(
                room.getId(),
                room.getRoomNumber(),
                room.getRoomName(),
                room.getRoom_capacity()
                );
    }
    public static RoomDTO toDtos(Room room)
    {
        return room==null ? null : new RoomDTO(
                room.getId(),
                room.getRoomNumber(),
                room.getRoomName(),
                room.getRoom_capacity()
        );
    }
    public static Room toEntity(RoomDTO roomDTO)
    {
        return roomDTO==null ? null : new Room(
                roomDTO.getId(),
                roomDTO.getRoomNumber(),
                roomDTO.getRoomName()
        );
    }
}
