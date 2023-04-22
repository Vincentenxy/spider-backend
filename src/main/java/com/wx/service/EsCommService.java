package com.wx.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.wx.Utils.SpringBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EsCommService {

    @Autowired
    private SpringBeanUtils springBeanUtils;

    public ElasticsearchClient getEsClient() {
        return springBeanUtils.getBean("elasticsearchClient");
    }

}
