package project.newscuratorservicehomeproposal.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@SequenceGenerator(
        name = "USER_PK_GENERATOR",
        sequenceName = "USER_PK_SEQ",
        initialValue= 1,
        allocationSize = 1
)
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="USER_PK_GENERATOR")
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
        //조회수를 처음엔 0으로 초기화
    }
}
