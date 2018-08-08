package com.example.liuwen.end_reader.DateLoad;

import com.example.liuwen.end_reader.Base.Config;
import com.example.liuwen.end_reader.CallBack.ResultCallback;
import com.example.liuwen.end_reader.Utils.HtmlParserUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/08/08 15:48
 * desc   :
 */
public class CommonApi extends BaseApi {


    public static void searchBook(String bookName, final ResultCallback callback) {
        Map<String, Object> params = new HashMap<>();
        params.put("s", Config.s);
        params.put("q", bookName);
        params.put("click", "1");
        getCommonReturnHtmlStringApi(Config.method_buxiu_search, params, "utf-8", new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                callback.onFinish(HtmlParserUtil.getBooksFromSearchHtml((String) o),code);
            }

            @Override
            public void onError(Exception e) {
               callback.onError(e);
            }
        });
    }

}
