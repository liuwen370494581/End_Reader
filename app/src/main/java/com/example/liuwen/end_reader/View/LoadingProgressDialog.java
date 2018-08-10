package com.example.liuwen.end_reader.View;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.liuwen.end_reader.R;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/08/10 11:00
 * desc   :
 */
public class LoadingProgressDialog extends Dialog {
    private TextView mTxtT;
    private LinearLayout mLinearLayout;
    private ILoadingDialogListener mListener;
    private Context mContext;

    public LoadingProgressDialog(Context context, String toastContent) {
        super(context, R.style.CustomDialogStyle);
        setContentView(R.layout.layout_loading_progress);
        mContext = context;
        mLinearLayout = (LinearLayout) findViewById(R.id.dialog_toast_bg);
        mLinearLayout.getBackground().setAlpha(150);
        mTxtT = (TextView) this.findViewById(R.id.txt);
        mTxtT.setText(toastContent);
        setCanceledOnTouchOutside(true);
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if(null != mListener)
                    mListener.onCancelEvent();
            }
        });
    }
    public void setListener(ILoadingDialogListener listener){
        mListener = listener;
    }
    /**
     * 设置为不可取消
     */
    public void setCannotCancel(){
        setCancelable(false);
    }
    public void closeDialog(){
        try{
            if(isShowing())
                dismiss();
        }catch (Exception e){

        }
    }
    public void showDialog(){
        try{
            if(!((Activity)mContext).isFinishing())
                show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     *
     * @Title: setTextMsg
     * @Description: TODO(设置提示问题)
     * @param @param toastContent 设定文件
     * @return void 返回类型
     * @throws
     */
    public void setTextMsg(String toastContent) {
        mTxtT.setVisibility(View.VISIBLE);
        mTxtT.setText(toastContent);
    }

    public interface ILoadingDialogListener{
        void onCancelEvent();
    }
}
