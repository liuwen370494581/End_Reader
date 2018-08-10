package com.example.liuwen.end_reader;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.liuwen.end_reader.Activity.SearchBookActivity;
import com.example.liuwen.end_reader.Base.BaseActivity;
import com.example.liuwen.end_reader.EventBus.BindEventBus;
import com.example.liuwen.end_reader.EventBus.C;
import com.example.liuwen.end_reader.EventBus.Event;
import com.example.liuwen.end_reader.Listener.OnCommonBarListener;
import com.example.liuwen.end_reader.Utils.ToastUtils;
import com.example.liuwen.end_reader.View.CircleImageView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

@BindEventBus
public class MainActivity extends BaseActivity {
    private TabLayout tlTabMenu;
    private ImageView ivSearch;
    private ViewPager vpContent;
    private RelativeLayout rlCommonTitle;
    private TextView tvEditFinish;
    private RelativeLayout rlEditTitile;

    private MainPresenter mMainPresenter;

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        tlTabMenu = getView(R.id.tl_tab_menu);
        ivSearch = getView(R.id.iv_search);
        vpContent = getView(R.id.vp_content);
        rlCommonTitle = getView(R.id.rl_common_title);
        rlEditTitile = getView(R.id.rl_edit_titile);
        tvEditFinish = getView(R.id.tv_edit_finish);
        setCenterText("追书神器");
        setRightListener(new OnCommonBarListener() {
            @Override
            public void onRightChoiceListener() {
                openActivity(SearchBookActivity.class);
            }
        });
        mMainPresenter = new MainPresenter(this);
        mMainPresenter.start();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(Event event) {
        switch (event.getCode()) {
            case C.EventCode.UserURl:
                ToastUtils.showCenterToast(getActivityContext(), event.getData().toString());
                break;
        }
    }


    public TabLayout getTlTabMenu() {
        return tlTabMenu;
    }

    public ImageView getIvSearch() {
        return ivSearch;
    }

    public ViewPager getVpContent() {
        return vpContent;
    }

    public RelativeLayout getRlCommonTitle() {
        return rlCommonTitle;
    }

    public TextView getTvEditFinish() {
        return tvEditFinish;
    }

    public RelativeLayout getRlEditTitile() {
        return rlEditTitile;
    }
}
