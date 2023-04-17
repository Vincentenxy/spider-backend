package com.wx.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.wx.entities.Article;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EsService {

    RestClient restClient = RestClient.builder(
            new HttpHost("192.168.68.111", 9200)).build();

    ElasticsearchTransport transport = new RestClientTransport(
            restClient, new JacksonJsonpMapper());

    ElasticsearchClient client = new ElasticsearchClient(transport);


    public void es() throws IOException {
        SearchResponse<Article> search = client.search(s ->
                        s.index("articles"),
                Article.class);

        System.out.println("------>"+search.hits());
        for (Hit<Article> hit : search.hits().hits()) {
            System.out.println("====>"+hit);
        }

    }


    public static void main(String[] args) throws IOException {
        EsService esService = new EsService();

        esService.es();

    }


}
