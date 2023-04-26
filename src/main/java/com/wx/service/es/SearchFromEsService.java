package com.wx.service.es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.alibaba.fastjson2.JSONObject;
import com.wx.service.EsCommService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SearchFromEsService implements SearchFromEs{

    @Autowired
    private EsCommService esCommService;

    private ElasticsearchClient client;

    /**
     * 查询
     * @param index
     * @return
     */
    public List<JSONObject> search(String index) {

        client = esCommService.getEsClient();

        SearchResponse<JSONObject> sr = null;
        try {
            sr = client.search(s -> s.index(index), JSONObject.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Hit<JSONObject>> hits = sr.hits().hits();
        List<JSONObject> resp = new ArrayList<>();
        for (Hit<JSONObject> hit :  sr.hits().hits()) {
            resp.add(hit.source());
        }
        return resp;
    }


}
