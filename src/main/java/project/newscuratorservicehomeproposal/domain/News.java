package project.newscuratorservicehomeproposal.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class News {

    @Id
    @GeneratedValue
    @Column(name = "news_id")
    private Long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String main;

    @NotEmpty
    private String reporter;
    private LocalDateTime createNewsDate;

    private LocalDateTime updateNewsDate;

    private LocalDateTime deleteNewsDate;

    //임의 생성 방지
    protected News() {
        this.createNewsDate = LocalDateTime.now();
    }

}
