package com.example.liuwen.end_reader.Adapter;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.liuwen.end_reader.Base.Config;
import com.example.liuwen.end_reader.Bean.Chapter;
import com.example.liuwen.end_reader.CallBack.ResultCallback;
import com.example.liuwen.end_reader.DateLoad.CommonApi;
import com.example.liuwen.end_reader.R;
import com.example.liuwen.end_reader.Utils.StringHelper;

import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/09/27 15:57
 * desc   :
 */
public class ReadAdapter extends BGARecyclerViewAdapter<Chapter> {
    private Handler myHandler;

    public ReadAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_read);
        myHandler = new Handler();
    }


    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, final Chapter model) {
        final TextView tvTitle = helper.getTextView(R.id.tv_title);
        TextView tvContent = helper.getTextView(R.id.tv_content);
        final TextView tvError = helper.getTextView(R.id.tv_loading_error_tips);
        tvTitle.setText(model.getTitle());
        tvContent.setText(model.getContent());
        preLoading(position, tvContent, tvError);
        lastLoading(position, tvContent, tvError);
        tvError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getChapterContent(model, tvTitle, tvError);
            }
        });
    }


    /**
     * 加載下一章
     *
     * @param position
     * @param tvContent
     * @param tvError
     */
    private void preLoading(int position, TextView tvContent, TextView tvError) {
        if (position + 1 < getItemCount()) {
            Chapter chapter = getItem(position + 1);
            if (StringHelper.isEmpty(chapter.getContent())) {
                getChapterContent(chapter, null, null);
            }
        }
    }

    /**
     * 预加载上一张
     *
     * @param position
     */
    private void lastLoading(int position, TextView tvContent, TextView tvError) {
        if (position > 0) {
            Chapter chapter = getItem(position - 1);
            if (StringHelper.isEmpty(chapter.getContent())) {
                getChapterContent(chapter, null, null);
            }
        }
    }

    private void getChapterContent(final Chapter chapter, final TextView tvTitle, final TextView tvError) {
        CommonApi.getChapterContent(chapter.getUrl(), new ResultCallback() {
            @Override
            public void onFinish(final Object object, int code) {
                chapter.setContent((String) object);
                myHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (tvTitle != null && tvError != null) {
                            tvTitle.setText((String) object);
                            tvError.setVisibility(View.GONE);
                        }
                    }
                });
            }

            @Override
            public void onError(Exception e) {
                myHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (tvError != null) {
                            tvError.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });
    }
}
