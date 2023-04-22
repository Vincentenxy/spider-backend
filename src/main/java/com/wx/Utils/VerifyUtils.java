package com.wx.Utils;

import com.alibaba.fastjson2.JSONObject;
import com.mysql.cj.util.StringUtils;

public class VerifyUtils {



    public static boolean verifyParam(String... params) {
        for (String param : params) {
            if (StringUtils.isNullOrEmpty(param)) {
                return false;
            }
        }
        return true;
    }

    public static boolean verifyObj(JSONObject obj, String... params){
        for (String param : params) {
            if (StringUtils.isNullOrEmpty(obj.getString(param))) {
                return false;
            }
        }
        return true;
    }
}
