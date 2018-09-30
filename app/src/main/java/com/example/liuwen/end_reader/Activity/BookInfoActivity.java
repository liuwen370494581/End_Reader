package com.example.liuwen.end_reader.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liuwen.end_reader.Base.BaseActivity;
import com.example.liuwen.end_reader.Bean.Book;
import com.example.liuwen.end_reader.R;
import com.example.liuwen.end_reader.Utils.GlideUtils;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/09/26 14:06
 * desc   :
 */
public class BookInfoActivity extends BaseActivity implements View.OnClickListener {

    private Book mCurrentBook;
    private ImageView ivBookCover;
    private TextView tvBookTitle, tvBookAuthor, tvBookDesc, tvBookNewChapter, tvBookUpdateTime;
    private TextView btnBookRead, btnBookList, btnAddBook;


    @Override
    protected int setLayoutRes() {
        return R.layout.activity_book_info;
    }

    @Override
    protected void initView() {
        showLeftView();
        setCenterText("详细信息");
        ivBookCover = getView(R.id.iv_book_cover);
        tvBookTitle = getView(R.id.tv_book_title);
        tvBookAuthor = getView(R.id.tv_book_author);
        tvBookDesc = getView(R.id.tv_book_desc);
        tvBookNewChapter = getView(R.id.tv_book_newest_chapter);
        tvBookUpdateTime = getView(R.id.tv_book_update_time);
        btnBookRead = getView(R.id.id_click_read);
        btnBookList = getView(R.id.id_book_list);
        btnAddBook = getView(R.id.id_click_add_book);
    }

    @Override
    protected void initData() {
        mCurrentBook = (Book) getIntent().getSerializableExtra("bookInfo");
        if (mCurrentBook != null) {
            GlideUtils.loadImage(ivBookCover, mCurrentBook.getImgUrl(), R.mipmap.ic_default_cover, R.mipmap.ic_default_cover);
            tvBookTitle.setText(mCurrentBook.getName());
            tvBookAuthor.setText(mCurrentBook.getAuthor());
            tvBookDesc.setText(mCurrentBook.getDesc());
            tvBookUpdateTime.setText("最后更新：" + mCurrentBook.getUpdateDate());
            tvBookNewChapter.setText("最新章节：" + mCurrentBook.getNewestChapterTitle());
        }
    }

    @Override
    protected void setListener() {
        btnAddBook.setOnClickListener(this);
        btnBookList.setOnClickListener(this);
        btnBookRead.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("Book", mCurrentBook);
        if (v == btnBookRead) {
            openActivity(ReadBookActivity.class, bundle);
        } else if (v == btnBookList) {
            openActivity(ChapterListActivity.class, bundle);
        } else if (v == btnAddBook) {

        }
    }
}
