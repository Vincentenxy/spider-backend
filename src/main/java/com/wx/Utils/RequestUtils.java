package com.wx.Utils;

import com.alibaba.fastjson2.JSONObject;
import com.wx.comm.Constants;
import org.springframework.stereotype.Component;

@Component
public class RequestUtils {

    public static JSONObject successResp() {
        return createJson(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG);
    }

    public static JSONObject validParam() {
        return createJson(Constants.ERR_VALID_PARAM, Constants.ERR_VALID_PARAM_MAG);
    }

    public static JSONObject validParam(String msg) {
        return createJson(Constants.ERR_VALID_PARAM, msg);
    }

    public static JSONObject createJson(String code, String msg) {
        JSONObject resp = new JSONObject();
        resp.put("code", code);
        resp.put("msg", msg);
        return resp;
    }

}
