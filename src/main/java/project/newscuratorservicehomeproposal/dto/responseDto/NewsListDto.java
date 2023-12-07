package project.newscuratorservicehomeproposal.dto.responseDto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import project.newscuratorservicehomeproposal.domain.News;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class NewsListDto {

    private Long id;
    private String title;
    private String main;
    private String reporter;
    private LocalDateTime createNewsDate;
    private LocalDateTime updateNewsDate;

    // 생성자: News 엔터티를 NewsDto로 변환
    public NewsListDto(News news) {
        this.id = news.getId();
        this.title = news.getTitle();
        this.main = news.getMain();
        this.reporter = news.getReporter();
        this.createNewsDate = news.getCreateNewsDate();
        this.updateNewsDate = news.getUpdateNewsDate();
    }

    /**
     * static 으로 만들어서 객체를 생성하지 않고, 메서드를 사용.
     * 해당 메서드에서 객체를 만들어내는 방식
     * @param newsList
     * @return
     */
    public static List<NewsListDto> createNewsListDto (List<News> newsList){
        return newsList.stream()
                .map(news ->new NewsListDto(news))  // NewsListDto 생성자를 이용하여 News를 NewsListDto로 변환
                .collect(Collectors.toList());
    }

}
