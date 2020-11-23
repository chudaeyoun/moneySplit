package com.money.split.service

import com.money.split.domain.Split
import com.money.split.repository.SplitRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class SplitServiceTest extends Specification {
    @Autowired
    SplitService splitService
    @Autowired
    SplitRepository splitRepository

    def setup() {
        splitRepository.deleteAll()
    }

    def "getUniqueToken test"() {
        expect:
        for (int i = 0; i < LOOP; i++) {
            splitRepository.save(
                    Split.builder()
                            .token(splitService.getUniqueToken())
                            .build())
        }

        splitRepository.count() == SIZE

        where:
        LOOP | SIZE
        10   | 10
        30   | 30
    }
}