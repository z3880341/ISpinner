package com.yt.iSpinner;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.yt.ispinnerdemo.ispinnerdemo.R;

public class ISpinnerContentView extends LinearLayout {
    private static final String TAG = "ISpinnerView";
    private ListView mParentListView,mChildListView;
    private PopupWindow mPopupWindow;
    private int mParentPosition;
    private ISpinnerUtil.ISpinnerListener mListener;
    private ArrayAdapter<String> mParentAdapter;
    private ArrayAdapter<String> mChildAdapter;
    private TextView mBufferParentView,mBufferChildView;
    private ISpinnerConfig mConfig;

    /**
     * 多级下拉栏View
     * @param context 外部上下文
     */
    protected ISpinnerContentView(Context context, ISpinnerConfig config){
        super(context);
        this.mConfig = config;
        mListener = mConfig.listener;
        setOrientation(HORIZONTAL);
        mParentListView = new ListView(getContext());
        mChildListView = new ListView(getContext());
        mParentListView.setDivider(null);//去除自带分割线
        mChildListView.setDivider(null);
        if (mConfig.parentLayout == 0) {//判断是否传入了父级布局,如果为0就使用系统自带默认的
            mParentAdapter = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item, mConfig.parentContent);
        }else {
            mParentAdapter = new ArrayAdapter<String>(getContext(),mConfig.parentLayout,mConfig.parentContent);
        }
        mParentListView.setAdapter(mParentAdapter);
        addView(mParentListView);
        addView(mChildListView);
        setClick();

    }

    protected void setPopupwindow(PopupWindow popupwindow){
        mPopupWindow = popupwindow;

    }

    /**
     * 设置点击
     */
    private void setClick(){
        mParentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;
                ArrayAdapter<String> childAdapter;
                if (mConfig.childLayout == 0) {//判断是否传入了子级布局,如果为0就使用系统自带默认的
                    mChildAdapter = new ArrayAdapter<String>(parent.getContext(), R.layout.support_simple_spinner_dropdown_item, mConfig.childContent[position]);
                }else {
                    mChildAdapter = new ArrayAdapter<String>(parent.getContext(), mConfig.childLayout, mConfig.childContent[position]);
                }
                mChildListView.setAdapter(mChildAdapter);
                mChildAdapter.notifyDataSetChanged();
                mChildListView.setVisibility(View.VISIBLE);
                mParentPosition = position;
                if (mBufferParentView == null) {//首次点击设 置背景
                    if (mConfig.selector !=null){
                        textView.setBackground(mConfig.selector);
                    }
                    if (mConfig.selectorTextColor!=0) {
                        textView.setTextColor(getResources().getColor(mConfig.selectorTextColor));
                    }
                    mBufferParentView = textView;

                }else {//第二次点击 设置背景并且将上一次选中的View恢复为未选中
                    if (mConfig.notSelector!=null) {
                        mBufferParentView.setBackground(mConfig.notSelector);
                    }
                    if (mConfig.notSelectorTextColor!=0) {
                        mBufferParentView.setTextColor(getResources().getColor(mConfig.notSelectorTextColor));
                    }
                    if (mConfig.selector !=null){
                        textView.setBackground(mConfig.selector);
                    }
                    if (mConfig.selectorTextColor!=0) {
                        textView.setTextColor(getResources().getColor(mConfig.selectorTextColor));
                    }
                    mBufferParentView = textView;
                }
            }
        });

        mChildListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //第二级菜单的点击监听
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView textView = (TextView)view;
                if (mBufferChildView == null) {//首次点击设 置背景
                    if (mConfig.selector !=null){
                        textView.setBackground(mConfig.selector);
                    }
                    if (mConfig.selectorTextColor!=0) {
                        textView.setTextColor(getResources().getColor(mConfig.selectorTextColor));
                    }
                    mBufferChildView = textView;

                }else {//第二次点击 设置背景并且将上一次选中的View恢复为未选中
                    if (mConfig.notSelector!=null) {
                        mBufferChildView.setBackground(mConfig.notSelector);
                    }
                    if (mConfig.notSelectorTextColor!=0) {
                        mBufferChildView.setTextColor(getResources().getColor(mConfig.notSelectorTextColor));
                    }
                    if (mConfig.selector !=null){
                        textView.setBackground(mConfig.selector);
                    }
                    if (mConfig.selectorTextColor!=0) {
                        textView.setTextColor(getResources().getColor(mConfig.selectorTextColor));
                    }
                    mBufferChildView = textView;
                }
                mChildListView.setVisibility(GONE);
                mPopupWindow.dismiss();
                if (mListener!=null){
                    mListener.onChildItemClick(mParentPosition,mConfig.parentContent[mParentPosition],position,mConfig.childContent[mParentPosition][position]);
                }


            }
        });
    }



    protected void destroy(){
        mChildListView.removeAllViews();
        mChildListView.removeAllViewsInLayout();
        mParentListView.removeAllViews();
        mParentListView = null;
        mChildListView = null;
        mListener = null;
        mPopupWindow = null;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mParentListView.setLayoutParams(new LayoutParams(mConfig.width/2,ViewGroup.LayoutParams.MATCH_PARENT));
        mChildListView.setLayoutParams(new LayoutParams(mConfig.width/2,ViewGroup.LayoutParams.MATCH_PARENT));

    }

}
