package com.nankiewic.serverapp.repository;

import com.nankiewic.serverapp.model.Key;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyRepository extends JpaRepository<Key, Long> {

    Key findByKeyVal(String key);
}
