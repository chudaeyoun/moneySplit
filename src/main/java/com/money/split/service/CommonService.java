package com.money.split.service;

import com.money.split.domain.User;
import com.money.split.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommonService {
    private final UserRepository userRepository;

    public void addUserMoney(int user, long money) {
        User userInfo = userRepository.findByUser(user);
        userInfo.setMoney(userInfo.getMoney() + money);

        userRepository.save(userInfo);
    }
}