package project.newscuratorservicehomeproposal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.newscuratorservicehomeproposal.domain.News;
import project.newscuratorservicehomeproposal.repository.NewsRepository;

import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;

    /**
     * 뉴스 등록하는 로직
     * @param news
     * @return
     */
    public News postNews(News news) {

        News savedNews = newsRepository.save(news);

        return savedNews;
    }

    /**
     * 뉴스 리스트 가져오는 로직
     * @param pageable
     * @return
     */
    public List<News> newsList(Pageable pageable) {

        List<News> news = newsRepository.newsList(pageable);
        log.info(news.toString());

        return news;
    }
}
