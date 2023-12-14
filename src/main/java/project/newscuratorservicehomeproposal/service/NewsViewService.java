package project.newscuratorservicehomeproposal.service;

import io.lettuce.core.Consumer;
import io.lettuce.core.StreamMessage;
import io.lettuce.core.XReadArgs;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.springframework.data.redis.connection.stream.*;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class NewsViewService {
    private final RedisTemplate<String, String> redisTemplate;
    private final RedisCommands<String, String> redisCommands;

    private static final String streamKey = "newsStream";
    private static final String sortedSetKey = "newsSortedSet";
    private static final String consumerGroup = "newsGroup";
    private static final String consumerName = "spring";
    public void addNewsView(Long id) {

        Map<String, String> map = new HashMap<>();
        map.put("newsId", id.toString());

        redisCommands.xadd(streamKey, map);
    }

    //stream 에서 읽어온 후 sortedSet에 적재
    public void streamToSortedSet() {
        XReadArgs.StreamOffset<String> streamOffset = XReadArgs.StreamOffset.from(streamKey, "0");
//        List<StreamMessage<String, String>> messages = redisCommands.xread(XReadArgs.Builder.block(1000), streamOffset);
        Consumer consumer = Consumer.from(consumerGroup, consumerName);
        List<StreamMessage<String, String>> messages =  redisCommands.xreadgroup(consumer, streamOffset);


        for (StreamMessage<String, String> message : messages) {
            Map<String, String> messageList = message.getBody();
            for (Map.Entry<String, String> entry : messageList.entrySet() ) {
                String newsId = entry.getValue();

                if (newsId != null) {
                    // 기존 값이 있는지 확인
                    Double score = redisCommands.zscore(sortedSetKey, newsId);

                    if (score != null) {
                        // 이미 값이 있는 경우, 개수를 증가
                        redisCommands.zincrby(sortedSetKey, 1, newsId);
                    } else {
                        // 값이 없는 경우, 새로 추가
                        redisCommands.zadd(sortedSetKey, 1, newsId);
                    }
                }
            }
        }
    }
}