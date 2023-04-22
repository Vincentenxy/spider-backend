package com.wx.service;

import com.alibaba.fastjson2.JSONObject;
import com.wx.entities.Article;
import com.wx.service.es.WriteEsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class KafkaService {

//    @Autowired
//    private UrlsMapper urlsMapper;
//
//    @Autowired
//    private ArticleMapper articleMapper;

    @Autowired
    private WriteEsService writeEsService;

    @KafkaListener(topics = "test", groupId = "console-consumer-90771")
    public void listen(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            String message = (String) kafkaMessage.get();
            log.info("message: {}", message);

            JSONObject pj = JSONObject.parse(message);

            Article article = new Article();
            article.setUrl(pj.getString("url"));
            article.setTitle(pj.getString("title"));
            article.setType("article");
            article.setContent(pj.getString("content"));
            System.out.println("文章》》》"+ pj.getString("content"));
//            articleMapper.insert(article);

            writeEsService.writeToEs(pj);

            // 将数据写入mysql
//            Urls urls = new Urls();
//            urls.setUrl(pj.getString("url"));
//            List<Urls> urlsList = new ArrayList<Urls>();
//            urlsList.add(urls);
//            urlsMapper.insert(urls);
//
//            List<Urls> retList = urlsMapper.list();
//            System.out.println(retList);



        }
    }




}
