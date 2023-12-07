package project.newscuratorservicehomeproposal.repository;

import org.springframework.data.domain.Pageable;
import project.newscuratorservicehomeproposal.domain.News;

import java.util.List;

public interface NewsRepositoryCustom {

    List<News> newsList(Pageable pageable);

}
