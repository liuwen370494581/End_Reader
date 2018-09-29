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
    /**
     * 搜索小说名
     *
     * @param bookName
     * @param callback
     */
    public static void searchBook(String bookName, final ResultCallback callback) {
        Map<String, Object> params = new HashMap<>();
        params.put("s", Config.s);
        params.put("q", bookName);
        params.put("click", "1");
        getCommonReturnHtmlStringApi(Config.method_buxiu_search, params, "utf-8", new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                callback.onFinish(HtmlParserUtil.getBooksFromSearchHtml((String) o), code);
            }

            @Override
            public void onError(Exception e) {
                callback.onError(e);
            }
        });
    }



     /**
     * 获取章节列表
     * @param url
     * @param callback
     */
    public static void getBookChapters(String url, final ResultCallback callback){
        getCommonReturnHtmlStringApi(url, null, "GBK", new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                callback.onFinish(HtmlParserUtil.getChaptersFromHtml((String) o),0);
            }

            @Override
            public void onError(Exception e) {
                callback.onError(e);

            }
        });
    }

    /**
     * 获取章节正文
     * @param url
     * @param callback
     */
    public static void getChapterContent(String url, final ResultCallback callback){
        int tem = url.indexOf("\"");
        if (tem != -1){
            url = url.substring(0,tem);
        }
        getCommonReturnHtmlStringApi(Config.nameSpace_tianlai + url, null, "GBK", new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                callback.onFinish(HtmlParserUtil.getContentFormHtml((String)o),0);

            }

            @Override
            public void onError(Exception e) {
                callback.onError(e);
            }
        });
    }





}
