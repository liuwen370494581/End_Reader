package com.example.liuwen.end_reader.DateLoad;

import com.example.liuwen.end_reader.CallBack.ResultCallback;
import com.example.liuwen.end_reader.Utils.HtmlParserUtil;

import java.util.Map;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/08/08 15:28
 * desc   :
 */
public class BaseApi {


    protected static void getCommonReturnHtmlStringApi(final String url, Map<String, Object> params, String charsetName, final ResultCallback callback) {
        HttpDataSource.HttpGet_html(HtmlParserUtil.makeURL(url, params), charsetName, new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                callback.onFinish(o, code);
            }

            @Override
            public void onError(Exception e) {
                callback.onError(e);
            }
        });
    }

}
