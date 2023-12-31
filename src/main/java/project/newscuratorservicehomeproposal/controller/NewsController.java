package project.newscuratorservicehomeproposal.controller;


import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import project.newscuratorservicehomeproposal.domain.News;
import project.newscuratorservicehomeproposal.dto.responseDto.NewsDto;
import project.newscuratorservicehomeproposal.dto.responseDto.NewsListDto;
import project.newscuratorservicehomeproposal.service.NewsService;
import project.newscuratorservicehomeproposal.service.NewsViewService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class NewsController {
    private final NewsService newsService;
    private final NewsViewService newsViewService;

    /**
     * 뉴스 등록하기
     * admin 권한 필요
     * @param news
     * @return
     */
    @PostMapping(value = "/news")
    public Result<NewsDto> postNews(@RequestBody @Valid News news){

        News savedNews = newsService.postNews(news);

        NewsDto newsDto = new NewsDto(savedNews);

        return new Result<NewsDto>(newsDto);
    }

    /**
     * 뉴스 list 가져오기
     * 권한 필요 없음
     * @param offset
     * @param pageSize
     * @return
     */
    @GetMapping(value="/news/list")
    public Result<List<NewsListDto>> newsList(@RequestParam(name = "offset") int offset, @RequestParam(name = "pageSize") int pageSize){

        Pageable pageable  = PageRequest.of(offset, pageSize);
        List<News> newsList = newsService.newsList(pageable);
        List<NewsListDto> newsListDto = NewsListDto.createNewsListDto(newsList);

        Result<List<NewsListDto>> result = new Result<>(newsListDto);

        return new Result<>(newsListDto);
    }

    /**
     * 뉴스 detail 보기
     * @param id
     * @return
     */
    @GetMapping(value = "/news/{id}")
    public Result<NewsDto> newsDetail(@PathVariable(name = "id") Long id){
        log.info(id.toString());
        News news = newsService.newsDetail(id);
        //조회 entity redis 에 적재
        newsViewService.addNewsView(id);

        NewsDto newsDto = new NewsDto(news);

        return new Result<>(newsDto);
    }

    @GetMapping(value = "/news/sortedSet")
    public String sortedSet(){
        newsViewService.streamToSortedSet();

        return "ok";
    }


    @Data
    static class Result <T>{
        private T data;

        public Result(T data) {
            this.data = data;
        }
    }
}

