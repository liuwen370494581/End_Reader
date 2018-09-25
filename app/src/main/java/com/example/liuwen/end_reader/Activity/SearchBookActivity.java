package com.example.liuwen.end_reader.Activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.liuwen.end_reader.Action.SearchBookAction;
import com.example.liuwen.end_reader.Base.BaseActivity;
import com.example.liuwen.end_reader.Bean.Book;
import com.example.liuwen.end_reader.Bean.Dish;
import com.example.liuwen.end_reader.CallBack.ResultCallback;
import com.example.liuwen.end_reader.DateLoad.CommonApi;
import com.example.liuwen.end_reader.R;
import com.example.liuwen.end_reader.Utils.KeyboardUtil;
import com.example.liuwen.end_reader.Utils.ToastUtils;
import com.example.liuwen.end_reader.View.TipDialog;
import com.liaoinstan.springview.widget.SpringView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/08/09 17:29
 * desc   :
 */
public class SearchBookActivity extends BaseActivity implements View.OnClickListener {

    private EditText mEditName;
    private TextView tvCancel, tvReFlash, tvClean;
    private RelativeLayout reReFlash;
    private RecyclerView mRecyclerReFlash, mRecyclerClean, mRecyclerHistory;
    private SpringView mSpringView;
    private ReFlashAdapter mReFlashAdapter;
    private int reFlash = 2;
    private String bookName = "";
    private List<Book> mSearchBooks = new ArrayList<>();

    private MyHandler mHandler = new MyHandler(this);

    private static class MyHandler extends Handler {
        private WeakReference<Context> reference;

        public MyHandler(Context context) {
            reference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            final SearchBookActivity activity = (SearchBookActivity) reference.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        //获取成功
                        ToastUtils.showCenterToast(activity.getActivityContext(), activity.mSearchBooks.toString());
                        break;
                    case 1:
                        activity.showTipDialog(new TipDialog.ITipDialogListener() {
                            @Override
                            public void clickLeft() {

                            }

                            @Override
                            public void clickRight() {
                             activity.searchBookForName();
                            }
                        });//获取失败
                        break;
                    default:
                }
            }
        }
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_search_book;
    }


    @Override
    protected void initView() {
        mEditName = getView(R.id.activity_search_book_name);
        tvCancel = getView(R.id.activity_search_book_cancel);
        reReFlash = getView(R.id.activity_search_book_re_reflash);
        tvReFlash = getView(R.id.activity_search_book_reflash);
        tvClean = getView(R.id.activity_search_book_clean);
        mRecyclerReFlash = getView(R.id.activity_search_book_reflash_recycler_view);
        mRecyclerClean = getView(R.id.activity_search_book_history_recycler_view);
        mRecyclerHistory = getView(R.id.history_recycler_view);
        mSpringView = getView(R.id.history_spring_view);

    }

    @Override
    protected void initData() {
        //书籍刷新模块
        reFlashModel();
        //书籍历史记录查询模块
        searchBookHistory();
        //书籍查询结果模块
        searchBookDetail();
    }


    private void reFlashModel() {
        GridLayoutManager manager = new GridLayoutManager(getActivityContext(), 3, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerReFlash.setLayoutManager(manager);
        mReFlashAdapter = new ReFlashAdapter(mRecyclerReFlash);
        mReFlashAdapter.setData(SearchBookAction.getReflashData());
        mRecyclerReFlash.setAdapter(mReFlashAdapter);
        tvReFlash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (reFlash) {
                    case 1:
                        mReFlashAdapter.setData(SearchBookAction.getReflashData());
                        reFlash = 2;
                        break;
                    case 2:
                        mReFlashAdapter.setData(SearchBookAction.getReflashData_2());
                        reFlash = 3;
                        break;
                    case 3:
                        mReFlashAdapter.setData(SearchBookAction.getReflashData_3());
                        reFlash = 1;
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void searchBookHistory() {

    }

    private void searchBookDetail() {

    }

    @Override
    protected void setListener() {
        tvCancel.setOnClickListener(this);
        mEditName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    bookName = mEditName.getText().toString();
                    if (TextUtils.isEmpty(bookName)) {
                        ToastUtils.showCenterToast(getActivityContext(), getString(R.string.book_is_not_empty));
                        return false;
                    } else {
                        searchBookForName();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void searchBookForName() {
        showLoadingDialog(getString(R.string.on_loading), true, null);
        CommonApi.searchBook(bookName, new ResultCallback() {
            @Override
            public void onFinish(Object o, int code) {
                hideLoadingDialog();
                mSearchBooks = (List<Book>) o;
                mHandler.sendEmptyMessage(0);
                Log.e("MainActivity", o.toString());

            }

            @Override
            public void onError(Exception e) {
                hideLoadingDialog();
                mHandler.sendEmptyMessage(1);
            }
        });
    }



    @Override
    public void onClick(View v) {
        if (v == tvCancel) {
            closeActivity();
        }
    }


    private class ReFlashAdapter extends BGARecyclerViewAdapter<Dish> {

        private ReFlashAdapter(RecyclerView recyclerView) {
            super(recyclerView, R.layout.item_search_book_reflash);
        }

        @Override
        protected void fillData(BGAViewHolderHelper helper, int position, Dish model) {
            helper.setText(R.id.item_name, model.getTitle());
            GradientDrawable p = (GradientDrawable) helper.getView(R.id.item_name).getBackground();
            p.setColor(Color.parseColor(SearchBookAction.getRandomColor()));
        }
    }


}
