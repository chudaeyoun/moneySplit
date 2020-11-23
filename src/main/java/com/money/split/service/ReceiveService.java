package com.money.split.service;

import com.money.split.domain.Distribution;
import com.money.split.dto.HeaderDTO;
import com.money.split.dto.ReceiveDTO;
import com.money.split.exception.CustomException;
import com.money.split.repository.DistributionRepository;
import com.money.split.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiveService {
    private final CommonService commonService;

    private final DistributionRepository distributionRepository;

    @Transactional
    public ReceiveDTO getMoney(String token, HeaderDTO headerDTO) {
        ReceiveDTO receiveDTO = makeReceiveDTO(token, headerDTO);

        updateDistribute(receiveDTO);
        commonService.addUserMoney(receiveDTO.getUser(), receiveDTO.getMoney());

        return receiveDTO;
    }

    private ReceiveDTO makeReceiveDTO(String token, HeaderDTO headerDTO) {
        return ReceiveDTO.builder()
                .token(token)
                .user(headerDTO.getUserId())
                .room(headerDTO.getRoomId())
                .build();
    }

    private void updateDistribute(ReceiveDTO receiveDTO) {
        checkValidation(getDistribute(receiveDTO.getToken()), receiveDTO);

        Distribution distribution = getOneDistribute(receiveDTO.getToken());
        distribution.setUser(receiveDTO.getUser());
        distribution.setUse("Y");

        receiveDTO.setMoney(distribution.getMoney());

        distributionRepository.save(distribution);
    }

    private Distribution getOneDistribute(String token) {
        return distributionRepository.findFirstByTokenAndUse(token, "N");
    }

    private List<Distribution> getDistribute(String token) {
        return distributionRepository.findByToken(token);
    }

    public void checkValidation(List<Distribution> distributionList, ReceiveDTO receiveDTO) {
        if (distributionList == null || distributionList.size() == 0) {
            throw new CustomException(ErrorCode.NOT_VALID_TOKEN);
        }

        if (!distributionList.get(0).getRoom().equals(receiveDTO.getRoom())) {
            throw new CustomException(ErrorCode.OTHER_ROOM_SPLIT);
        }

        if (distributionList.get(0).getCreateUser() == receiveDTO.getUser()) {
            throw new CustomException(ErrorCode.DONT_SELF_SPLIT);
        }

        if (distributionList.stream().anyMatch(it -> it.getUser() == receiveDTO.getUser())) {
            throw new CustomException(ErrorCode.DONT_TWICE_RECEIVE);
        }

        if (distributionList.stream().noneMatch(it -> it.getUse().equals("N"))) {
            throw new CustomException(ErrorCode.ALL_SPLIT);
        }

        if (distributionList.get(0).getCreatedAt().plusMinutes(10).isBefore(LocalDateTime.now())) {
            throw new CustomException(ErrorCode.SPLIT_TIME_OVER);
        }
    }
}