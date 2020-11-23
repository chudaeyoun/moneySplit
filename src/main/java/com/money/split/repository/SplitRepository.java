package com.money.split.repository;

import com.money.split.domain.Split;
import org.springframework.data.repository.CrudRepository;

public interface SplitRepository extends CrudRepository<Split, Long> {
    boolean existsByToken(String token);

    Split findByToken(String token);
}
