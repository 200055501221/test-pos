package com.test3.pos_test3.repo;

import com.test3.pos_test3.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories

public interface ItemRepo extends JpaRepository<Item, Long> {
    static int countAllByActiveStateEquals(boolean activeStatus) {
        return 0;
    }

    List<Item> findAllByItemNameEqualsAndActiveStateEquals(String itemName, boolean b);

    List<Item> findAllByActiveStateEquals(boolean activeStatus);

    Page<Item> findAllByActiveStateEquals(boolean activeStatus, Pageable pageable);
}
