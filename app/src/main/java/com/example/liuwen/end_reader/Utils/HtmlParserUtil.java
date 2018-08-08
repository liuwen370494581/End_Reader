package com.example.liuwen.end_reader.Utils;

import android.text.Html;
import android.util.Base64;
import android.util.Log;

import com.example.liuwen.end_reader.Base.Config;
import com.example.liuwen.end_reader.Bean.Book;
import com.example.liuwen.end_reader.Bean.Chapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.valueOf;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/08/08 14:30
 * desc   :  解析html工具
 */
public class HtmlParserUtil {

    /**
     * 从html中获取章节正文
     *
     * @param html
     * @return
     */
    public static String getContentFormHtml(String html) {
        Pattern pattern = Pattern.compile("<div id=\"content\">[\\s\\S]*?</div>");
        Matcher matcher = pattern.matcher(html);
        if (matcher.find()) {
            String content = Html.fromHtml(matcher.group(0)).toString();
            char c = 160;
            String spaec = "" + c;
            content = content.replace(spaec, "  ");
            return content;
        } else {
            return "";
        }
    }

    /**
     * 获取章节列表
     *
     * @param html
     * @return
     */
    public static ArrayList<Chapter> getChaptersFromHtml(String html) {
        ArrayList<Chapter> chapters = new ArrayList<>();
        Pattern pattern = Pattern.compile("<dd><a href=\"([\\s\\S]*?)</a>");
//        Pattern pattern = Pattern.compile("<dd><a href=\"([\\s\\S]*?)\"");
        Matcher matcher = pattern.matcher(html);
        String lastTile = null;
        int i = 0;
        while (matcher.find()) {
            String content = matcher.group();
            String title = content.substring(content.indexOf("\">") + 2, content.lastIndexOf("<"));
            if (!StringHelper.isEmpty(lastTile) && title.equals(lastTile)) {
                continue;
            }
            Chapter chapter = new Chapter();
            chapter.setNumber(i++);
            chapter.setTitle(title);
            chapter.setUrl(content.substring(content.indexOf("\"") + 1, content.lastIndexOf("l\"") + 1));
            chapters.add(chapter);
            lastTile = title;
        }
        return chapters;
    }


    /**
     * 从html中得到列表
     *
     * @param html
     * @return
     */
    public static ArrayList<Book> getBooksFromSearchHtml(String html) {
        ArrayList<Book> books = new ArrayList<>();
        Document doc = Jsoup.parse(html);
        Element node = doc.getElementById("results");
        for (Element div : node.children()) {
            if (!StringHelper.isEmpty(div.className()) && div.className().equals("result-list")) {
                for (Element element : div.children()) {
                    Book book = new Book();
                    Element img = element.child(0).child(0).child(0);
                    book.setImgUrl(img.attr("src"));
                    Element title = element.getElementsByClass("result-item-title result-game-item-title").get(0);
                    book.setName(title.child(0).attr("title"));
                    book.setChapterUrl(title.child(0).attr("href"));
                    Element desc = element.getElementsByClass("result-game-item-desc").get(0);
                    book.setDesc(desc.text());
                    Element info = element.getElementsByClass("result-game-item-info").get(0);
                    for (Element element1 : info.children()) {
                        String infoStr = element1.text();
                        if (infoStr.contains("作者：")) {
                            book.setAuthor(infoStr.replace("作者：", "").replace(" ", ""));
                        } else if (infoStr.contains("类型：")) {
                            book.setType(infoStr.replace("类型：", "").replace(" ", ""));
                        } else if (infoStr.contains("更新时间：")) {
                            book.setUpdateDate(infoStr.replace("更新时间：", "").replace(" ", ""));
                        } else {
                            Element newChapter = element1.child(1);
                            book.setNewestChapterUrl(newChapter.attr("href"));
                            book.setNewestChapterTitle(newChapter.text());
                        }
                    }
                    books.add(book);
                }
            }
        }
        Log.e("MainActivity", books.toString());
        return books;
    }



    public static String makeURL(String p_url, Map<String, Object> params) {
        if (params == null) return p_url;
        StringBuilder url = new StringBuilder(p_url);
        Log.d("http", p_url);
        if (url.indexOf("?") < 0)
            url.append('?');
        for (String name : params.keySet()) {
            Log.d("http", name + "=" + params.get(name));
            url.append('&');
            url.append(name);
            url.append('=');
            try {
                if (Config.isRSA) {
                    if (name.equals("token")) {
                        url.append(valueOf(params.get(name)));
                    } else {
                        url.append(StringHelper.encode(Base64.encodeToString(RSAUtilV2.encryptByPublicKey(valueOf(params.get(name)).getBytes(), Config.publicKey), Base64.DEFAULT).replace("\n", "")));
                    }
                } else {
                    url.append(valueOf(params.get(name)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return url.toString().replace("?&", "?");
    }

}
