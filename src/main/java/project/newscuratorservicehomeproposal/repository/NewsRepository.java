package project.newscuratorservicehomeproposal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.newscuratorservicehomeproposal.domain.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> , NewsRepositoryCustom{


}
