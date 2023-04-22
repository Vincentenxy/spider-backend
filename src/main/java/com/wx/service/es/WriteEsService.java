package com.wx.service.es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.Refresh;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.alibaba.fastjson2.JSONObject;
import com.mysql.cj.util.StringUtils;
import com.wx.Utils.RequestUtils;
import com.wx.service.EsCommService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class WriteEsService implements WriteToEs{



    @Autowired
    private RequestUtils requestUtils;

    @Autowired
    private EsCommService esCommService;

    private ElasticsearchClient client;

    /**
     * 将数据写入到es
     * @param article
     * @return
     */
    public JSONObject index(JSONObject article, String index, String id) {
        if (StringUtils.isNullOrEmpty(index))

        client = esCommService.getEsClient();
        System.out.println("插入数据: " + article);
        try {
            String articleId = "";
            if (StringUtils.isNullOrEmpty(id)) {
                articleId = client.index(b-> b
                        .index(index)
                        .document(article)
                        .refresh(Refresh.True)
                ).id();
            } else {
                articleId = client.index(b-> b
                        .index(index)
                        .document(article)
                        .refresh(Refresh.True)
                ).id();
            }
            System.out.println("插入数据id=" + articleId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return requestUtils.successResp();
    }


    public void es() throws IOException {
        RestClient restClient = RestClient.builder(
                new HttpHost("192.168.68.111", 9200)).build();
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());
        ElasticsearchClient client = new ElasticsearchClient(transport);

        SearchResponse<JSONObject> search = client.search(s ->
                        s.index("articles"),
                JSONObject.class);

        for (Hit<JSONObject> hit : search.hits().hits()) {
            System.out.println("====>"+hit);
        }

    }


    public static void main(String[] args) throws IOException {
        WriteEsService esService = new WriteEsService();

        esService.es();

    }


}
