package com.example.liuwen.end_reader.Adapter;

import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.example.liuwen.end_reader.Bean.Book;
import com.example.liuwen.end_reader.R;
import com.example.liuwen.end_reader.Utils.GlideUtils;
import com.example.liuwen.end_reader.View.CornerImageView;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/09/25 16:23
 * desc   :
 */
public class SearchResultAdapter extends BGARecyclerViewAdapter<Book> {

    private String title;

    public SearchResultAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_search_result);
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, Book model) {
        CornerImageView imageView = (CornerImageView) helper.getImageView(R.id.iv_search_item_cover);
        TextView tvTitle = helper.getTextView(R.id.tv_search_item_title);
        TextView tvAuthor = helper.getTextView(R.id.tv_search_item_author);
        TextView tvDesc = helper.getTextView(R.id.tv_search_item_desc);

        tvTitle.setText(changeTxtColor(model.getName(), title, 0xFFF08080));
        tvAuthor.setText(changeTxtColor(model.getAuthor(), title, 0xFFF08080));
        tvDesc.setText(changeTxtColor(model.getDesc(), title, 0xFFF08080));
        GlideUtils.loadImage(imageView, model.getImgUrl(), R.mipmap.ic_default_cover, R.mipmap.ic_default_cover);
    }

    private SpannableString changeTxtColor(String content, String splitText, int color) {
        int start = 0, end;
        SpannableString result = new SpannableString(content = (content == null ? "" : content));
        if (TextUtils.isEmpty(splitText)) {
            return result;
        }
        if (!TextUtils.isEmpty(splitText) && (content.length() >= splitText.length())) {
            while ((start = content.indexOf(splitText, start)) >= 0) {
                end = start + splitText.length();
                result.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                start = end;
            }
        }
        return result;
    }
}
