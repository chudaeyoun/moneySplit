package com.money.split.service;

import com.money.split.common.SplitUtils;
import com.money.split.domain.Distribution;
import com.money.split.dto.HeaderDTO;
import com.money.split.dto.RequestSplitDTO;
import com.money.split.dto.SplitDTO;
import com.money.split.repository.DistributionRepository;
import com.money.split.repository.SplitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SplitService {
    private final CommonService commonService;

    private final SplitRepository splitRepository;
    private final DistributionRepository distributionRepository;

    private static final int tokenLength = 3;

    @Transactional
    public String getSplit(RequestSplitDTO requestSplitDTO, HeaderDTO headerDTO) {
        String token = getUniqueToken();
        SplitDTO splitDTO = makeSplitDTO(requestSplitDTO, headerDTO, token);

        saveSplit(splitDTO);
        saveDistribute(splitDTO);
        commonService.addUserMoney(splitDTO.getUser(), splitDTO.getMoney() * -1);

        return token;
    }

    private SplitDTO makeSplitDTO(RequestSplitDTO splitDTO, HeaderDTO headerDTO, String token) {
        return SplitDTO.builder()
                .token(token)
                .user(headerDTO.getUserId())
                .room(headerDTO.getRoomId())
                .money(splitDTO.getMoney())
                .count(splitDTO.getCount())
                .build();
    }

    public String getUniqueToken() {
        String token = "";

        do {
            token = SplitUtils.makeToken(tokenLength);
        } while (isExistToken(token));

        return token;
    }

    private void saveSplit(SplitDTO splitDTO) {
        splitRepository.save(SplitDTO.convertToEntity(splitDTO));
    }

    private void saveDistribute(SplitDTO splitDTO) {
        SplitUtils.distributeMoney(splitDTO.getMoney(), splitDTO.getCount())
                .stream()
                .map(it -> makeDistribution(splitDTO, it))
                .forEach(distributionRepository::save);
    }

    private Distribution makeDistribution(SplitDTO splitDTO, Long money) {
        return Distribution.builder()
                .token(splitDTO.getToken())
                .money(money)
                .room(splitDTO.getRoom())
                .createUser(splitDTO.getUser())
                .use("N")
                .build();
    }

    private boolean isExistToken(String token) {
        return splitRepository.existsByToken(token);
    }
}