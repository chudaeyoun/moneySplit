package com.money.split;

import com.google.common.collect.Lists;
import com.money.split.domain.Room;
import com.money.split.domain.User;
import com.money.split.repository.RoomRepository;
import com.money.split.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@Slf4j
@EnableJpaAuditing
@RequiredArgsConstructor
@SpringBootApplication
public class SplitApplication implements ApplicationRunner {
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public static void main(String[] args) {
        SpringApplication.run(SplitApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        initData();
    }

    private void initData() {
        userRepository.saveAll(makeUserList());
        roomRepository.saveAll(makeRoomList());
    }

    private List<User> makeUserList() {
        List<User> userList = Lists.newArrayList();
        userList.add(User.builder().user(1).money(100000L).build());
        userList.add(User.builder().user(2).money(100000L).build());
        userList.add(User.builder().user(3).money(100000L).build());
        userList.add(User.builder().user(4).money(100000L).build());
        userList.add(User.builder().user(5).money(100000L).build());
        return userList;
    }

    private List<Room> makeRoomList() {
        List<Room> roomList = Lists.newArrayList();
        roomList.add(Room.builder().room("A").user(1).build());
        roomList.add(Room.builder().room("A").user(2).build());
        roomList.add(Room.builder().room("A").user(3).build());
        roomList.add(Room.builder().room("A").user(4).build());

        roomList.add(Room.builder().room("B").user(3).build());
        roomList.add(Room.builder().room("B").user(4).build());
        roomList.add(Room.builder().room("B").user(5).build());
        return roomList;
    }
}