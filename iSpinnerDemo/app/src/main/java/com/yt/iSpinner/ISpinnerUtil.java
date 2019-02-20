package com.yt.iSpinner;

import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;

/**
 *@content:悬浮多级下拉列表菜单
 *@time:2018-12-19
 *@build:zhouqiang
 */

public class ISpinnerUtil {
    private static final String TAG = "ISpinnerUtil";
    private PopupWindow mPopupWindow;
    private ISpinnerContentView mISpinnerContentView;
    private ISpinnerListener mListener;
    private ISpinnerConfig mConfig;

    /**
     * 多级下拉栏初始化方法,请在操作其他方法之前先初始化这个方法
     */
    protected void init(ISpinnerConfig config){
        this.mConfig = config;
        mListener = mConfig.listener;
        mISpinnerContentView = new ISpinnerContentView(mConfig.context,mConfig);
        mPopupWindow = new PopupWindow(mISpinnerContentView,mConfig.width,mConfig.height);
        mISpinnerContentView.setPopupwindow(mPopupWindow);
        mPopupWindow.setFocusable(true);//可以聚焦点击
        if (mConfig.externalBackground == null) {
            mPopupWindow.setBackgroundDrawable(new BitmapDrawable());//设置背景,设置后点击外部可以dismiss
        }else {
            mPopupWindow.setBackgroundDrawable(mConfig.externalBackground);
        }
        mPopupWindow.setOutsideTouchable(true);//设置外部点击取消,并且按返回键是取消popup
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (mListener!=null){
                    mListener.onDismiss();
                }

            }
        });
//        mPopupWindow.setAnimationStyle(R.style.AnimTheme);//使用属性动画
    }

    /**
     * 多级下拉栏悬浮窗显示
     * @param view
     */
    protected void show(View view){
        if (mPopupWindow == null){
            Log.e(TAG, "ISpinnerUtil no init");
            return;
        }
        mPopupWindow.showAsDropDown(view);
        if (mListener!=null){
            mListener.onShow();
        }
    }

    /**
     * 多级下拉栏悬浮窗取消
     */
    protected void dismiss(){
        if (mPopupWindow == null){
            Log.e(TAG, "ISpinnerUtil no init");
            return;
        }
        mPopupWindow.dismiss();
    }

    /**
     * 销毁
     */
    protected void destroy(){
        if (mPopupWindow!=null){
            mPopupWindow.dismiss();
            mPopupWindow = null;
        }
        if (mISpinnerContentView !=null){
            mISpinnerContentView.destroy();
            mISpinnerContentView = null;
        }
        mListener = null;

    }

    public interface ISpinnerListener{
        void onChildItemClick(int parentPosition, String parentText, int childPosition, String childText);
        void onShow();
        void onDismiss();

    }


}
