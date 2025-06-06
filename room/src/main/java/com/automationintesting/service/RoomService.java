package com.automationintesting.service;

import com.automationintesting.db.RoomDB;
import com.automationintesting.model.db.Room;
import com.automationintesting.model.db.Rooms;
import com.automationintesting.model.request.UnavailableRoom;
import com.automationintesting.model.service.RoomResult;
import com.automationintesting.requests.AuthRequests;
import com.automationintesting.requests.BookingRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RoomService {

    @Autowired
    private RoomDB roomDB;

    private AuthRequests authRequests;

    private BookingRequests bookingRequests;

    public RoomService() {
        authRequests = new AuthRequests();
        bookingRequests = new BookingRequests();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void beginDbScheduler() {
        DatabaseScheduler databaseScheduler = new DatabaseScheduler();
        databaseScheduler.startScheduler(roomDB, TimeUnit.MINUTES);
    }

    public Rooms getRooms() throws SQLException {
        return new Rooms(roomDB.queryRooms());
    }

    public RoomResult getSpecificRoom(int roomId) throws SQLException {
        Room queriedRoom = roomDB.query(roomId);

        if(queriedRoom != null){
            return new RoomResult(HttpStatus.OK, queriedRoom);
        } else {
            return new RoomResult(HttpStatus.NOT_FOUND);
        }
    }

    public RoomResult createRoom(Room roomToCreate, String token) throws SQLException {
        if(authRequests.postCheckAuth(token)){
            Room createdRoom = roomDB.create(roomToCreate);

            return new RoomResult(HttpStatus.CREATED, createdRoom);
        } else {
            return new RoomResult(HttpStatus.FORBIDDEN);
        }
    }

    public RoomResult deleteRoom(int roomId, String token) throws SQLException {
        if(authRequests.postCheckAuth(token)){
            if(roomDB.delete(roomId)){
                return new RoomResult(HttpStatus.ACCEPTED);
            } else {
                return new RoomResult(HttpStatus.NOT_FOUND);
            }
        } else {
            return new RoomResult(HttpStatus.FORBIDDEN);
        }
    }

    public RoomResult updateRoom(int roomId, Room roomToUpdate, String token) throws SQLException {
        if(authRequests.postCheckAuth(token)){
            Room updatedRoom = roomDB.update(roomId, roomToUpdate);

            if(updatedRoom != null){
                return new RoomResult(HttpStatus.ACCEPTED, updatedRoom);
            } else {
                return new RoomResult(HttpStatus.NOT_FOUND);
            }
        } else {
            return new RoomResult(HttpStatus.FORBIDDEN);
        }
    }

    public Rooms getUnavailableRooms(String checkin, String checkout) throws SQLException {
        List<UnavailableRoom> unavailableRooms = bookingRequests.getUnavailableRooms(checkin, checkout);
        List<Room> roomsList = roomDB.queryRooms();

        for(UnavailableRoom unavailableRoom : unavailableRooms){
            // If unavailable room is matched rooms list, remove it
            for(Room room : roomsList){
                if(room.getRoomid() == unavailableRoom.getRoomid()){
                    roomsList.remove(room);
                    break;
                }
            }
        }

        return new Rooms(roomsList);
    }
}
