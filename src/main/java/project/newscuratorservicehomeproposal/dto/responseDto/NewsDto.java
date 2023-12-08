package project.newscuratorservicehomeproposal.dto.responseDto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import project.newscuratorservicehomeproposal.domain.News;

import java.time.LocalDateTime;

@Data
public class NewsDto {

    private Long id;
    private String title;
    private String main;
    private String reporter;
    private LocalDateTime createNewsDate;
    private LocalDateTime updateNewsDate;

    public NewsDto(News news){
        this.id = news.getId();
        this.title = news.getTitle();
        this.main = news.getMain();
        this.reporter = news.getReporter();
        this.createNewsDate = news.getCreateNewsDate();
        this.updateNewsDate = news.getUpdateNewsDate();
    }

}
