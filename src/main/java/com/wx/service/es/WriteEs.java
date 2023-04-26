package com.wx.service.es;

import com.alibaba.fastjson2.JSONObject;

public interface WriteEs {

    JSONObject index(JSONObject data, String index,  String id);


}
