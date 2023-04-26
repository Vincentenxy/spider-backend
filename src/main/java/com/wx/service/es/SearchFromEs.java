package com.wx.service.es;

import com.alibaba.fastjson2.JSONObject;

import java.util.List;

public interface SearchFromEs {

    List<JSONObject> search(String index);
}
