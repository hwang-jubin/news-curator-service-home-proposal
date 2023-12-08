package project.newscuratorservicehomeproposal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.newscuratorservicehomeproposal.domain.News;

import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> , NewsRepositoryCustom{
    Optional<News> findById(Long id);
}
