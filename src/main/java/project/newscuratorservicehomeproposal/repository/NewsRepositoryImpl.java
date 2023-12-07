package project.newscuratorservicehomeproposal.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import project.newscuratorservicehomeproposal.domain.News;
import project.newscuratorservicehomeproposal.domain.QNews;

import java.util.List;
@Slf4j
@Repository
public class NewsRepositoryImpl implements NewsRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    public NewsRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    QNews news = QNews.news;

    /**
     * 최근 뉴스 생성일자 순으로 페이징하여 list 만들기
     * @param pageable
     * @return
     */
    @Override
    public List<News> newsList(Pageable pageable) {
        List<News> pagingNews = queryFactory.selectFrom(news)
                .orderBy(news.createNewsDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return pagingNews;
    }
}
