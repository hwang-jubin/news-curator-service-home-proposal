package project.newscuratorservicehomeproposal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.redis.connection.stream.*;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class NewsViewService {
    private final RedisTemplate<String, String> redisTemplate;

    public RecordId addNewsView(Long id) {
        //Map에 여러가지 field를 넣을 수 있음
        Map<String, String> map = new HashMap<>();
        map.put("newsId", id.toString());

        //RecordId = Redis에서 저장되는 UNIX 타임스탬프
        RecordId redisId = redisTemplate.opsForStream().add("news_stream", map);
        return redisId;
    }

}