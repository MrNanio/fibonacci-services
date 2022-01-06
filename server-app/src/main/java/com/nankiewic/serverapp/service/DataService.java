package com.nankiewic.serverapp.service;

import com.nankiewic.serverapp.dto.KeyValueDTO;
import com.nankiewic.serverapp.model.Key;
import com.nankiewic.serverapp.repository.KeyRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DataService {

    private final Integer REDIS_PORT = 6379;
    private final String REDIS_HOST = "redis";
    private final String REDIS_CHANNEL = "channel";
    private final String REDIS_KEY = "value";

    private final KeyRepository keyRepository;

    public void saveValueToCalc(KeyValueDTO keyValueDTO) {

        if (Objects.isNull(keyValueDTO.getValue())) {
            keyValueDTO.setValue("brak");
        }

        if (Objects.isNull(keyRepository.findByKeyVal(keyValueDTO.getKey()))) {
            Key key = Key.builder()
                    .keyVal(keyValueDTO.getKey())
                    .build();
            keyRepository.save(key);
        }

        jStore(keyValueDTO);
        jPublisher(keyValueDTO.getKey());
    }

    public List<KeyValueDTO> getAllIndex() {
        List<Key> keyList = keyRepository.findAll();
        return keyList.stream()
                .map(k -> mapValues(k.getKeyVal(), "hej"))
                .collect(Collectors.toList());
    }

    public List<KeyValueDTO> getTenLastData() {

        Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT);

        return jedis.hgetAll(REDIS_KEY).entrySet().stream()
                .map(s -> mapValues(s.getKey(), s.getValue()))
                .collect(Collectors.toList());
    }

    private KeyValueDTO mapValues(String key, String value) {
        return KeyValueDTO.builder()
                .key(key)
                .value(value)
                .build();
    }

    private void jStore(KeyValueDTO keyValueDTO) {
        Jedis jedis = new Jedis(REDIS_HOST, REDIS_PORT);
        jedis.hset(REDIS_KEY, keyValueDTO.getKey(), keyValueDTO.getValue());
    }

    private void jPublisher(String message) {
        Jedis jPublisher = new Jedis(REDIS_HOST, REDIS_PORT);
        jPublisher.publish(REDIS_CHANNEL, message);
    }
}
