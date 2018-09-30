package com.example.liuwen.end_reader.Adapter;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.liuwen.end_reader.Base.AppInfo;
import com.example.liuwen.end_reader.Bean.Chapter;
import com.example.liuwen.end_reader.R;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/09/29 16:22
 * desc   :
 */
public class ChapterListAdapter extends BGARecyclerViewAdapter<Chapter> {
    private int mType;
    private int mSelectPosition;//是否被选中


    /**
     * @param recyclerView
     * @param flagResId
     * @param type         1 是目录，2是书签\
     */
    public ChapterListAdapter(RecyclerView recyclerView, int flagResId, int type) {
        super(recyclerView, flagResId);
        this.mType = type;
    }

    public void setSelectPosition(int selectPosition) {
        this.mSelectPosition = selectPosition;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, Chapter model) {
        if (mType == 1) {
            //是目录
            TextView tvTitle = helper.getTextView(R.id.tv_chapter_title);
            tvTitle.setText("【" + model.getTitle() + "】");
            if (position == mSelectPosition) {
                tvTitle.setTextColor(AppInfo.getAppContext().getColor(R.color.present_text_color));
            }
        }
    }
}
