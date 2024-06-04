package com.example.course.repos;


import com.example.course.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByUuid(String uuid);
}
