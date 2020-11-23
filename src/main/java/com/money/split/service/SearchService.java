package com.money.split.service;

import com.money.split.domain.Distribution;
import com.money.split.dto.HeaderDTO;
import com.money.split.dto.ReceiveDTO;
import com.money.split.dto.SearchDTO;
import com.money.split.exception.CustomException;
import com.money.split.repository.DistributionRepository;
import com.money.split.repository.SplitRepository;
import com.money.split.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {
    private final SplitRepository splitRepository;
    private final DistributionRepository distributionRepository;

    public SearchDTO getSplitInfo(String token, HeaderDTO headerDTO) {
        return searchSplitInfo(makeSearchDTO(token, headerDTO));
    }

    private SearchDTO makeSearchDTO(String token, HeaderDTO headerDTO) {
        return SearchDTO.builder()
                .token(token)
                .user(headerDTO.getUserId())
                .room(headerDTO.getRoomId())
                .build();
    }

    private SearchDTO searchSplitInfo(SearchDTO searchDTO) {
        List<Distribution> distributionList = getDistribute(searchDTO.getToken());
        checkValidation(distributionList, searchDTO);
        return setThrowingInfo(distributionList, searchDTO.getToken());
    }

    private List<Distribution> getDistribute(String token) {
        return distributionRepository.findByToken(token);
    }

    public void checkValidation(List<Distribution> distributionList, SearchDTO searchDTO) {
        if (distributionList == null || distributionList.size() == 0) {
            throw new CustomException(ErrorCode.NOT_VALID_TOKEN);
        }
        if (!distributionList.get(0).getCreateUser().equals(searchDTO.getUser())) {
            throw new CustomException(ErrorCode.NOT_SELF_TOKEN);
        }
        if (distributionList.get(0).getCreatedAt().plusDays(7).toLocalDate().isBefore(LocalDate.now())) {
            throw new CustomException(ErrorCode.SPLIT_7DAY_OVER);
        }
    }

    private SearchDTO setThrowingInfo(List<Distribution> distributionList, String token) {
        return SearchDTO.builder()
                .createAt(distributionList.get(0).getCreatedAt())
                .user(distributionList.get(0).getCreateUser())
                .throwingMoney(splitRepository.findByToken(token).getMoney())
                .receivedMoney(getReceivedMoney(distributionList))
                .receiveDTOList(getReceiveDTOList(distributionList))
                .build();
    }

    private long getReceivedMoney(List<Distribution> distributionList) {
        return distributionList.stream()
                .filter(it -> it.getUse().equals("Y"))
                .map(Distribution::getMoney)
                .reduce(0L, Long::sum);
    }

    private List<ReceiveDTO> getReceiveDTOList(List<Distribution> distributionList) {
        return distributionList.stream()
                .filter(it -> it.getUse().equals("Y"))
                .map(it -> ReceiveDTO.builder()
                        .money(it.getMoney())
                        .user(it.getUser())
                        .build())
                .collect(Collectors.toList());
    }
}