package com.yt.iSpinner;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

public class ISpinnerConfig {
    private static final String TAG = "ISpinnerConfig";
    protected Context context;
    protected String[] parentContent;
    protected String[][] childContent;
    protected int parentLayout;
    protected int childLayout;
    protected Drawable selector;
    protected Drawable notSelector;
    protected int selectorTextColor;
    protected int notSelectorTextColor;
    protected int width;
    protected int height;
    protected Drawable externalBackground;//外部背景
    protected ISpinnerUtil.ISpinnerListener listener;
    private ISpinnerUtil mUtil;

    /**
     * 实例多级下拉配置类
     * @param context activity上下文
     * @param width 宽度
     * @param height 高度
     */
    public ISpinnerConfig(@NonNull Context context, @NonNull int width, @NonNull int height){
        this.context = context;
        this.width = width;
        this.height = height;

    }

    /**
     * 添加一级菜单的内容
     * @param parentContent
     * @return
     */
    public ISpinnerConfig addParentContent(@NonNull String[] parentContent){
        this.parentContent = parentContent;
        return this;
    }

    /**
     * 添加二级菜单的内容（注意！二级菜单一维长度应该与一级菜单一致）
     * @param childContent
     * @return
     */
    public ISpinnerConfig addChildContent(@NonNull String[][] childContent){
        this.childContent = childContent;
        return this;
    }

    /**
     * 添加一级菜单的布局（使用TextView作为根布局的Layout,另外id名称应该为text1）
     * 注意！目前不支持添加多个View或者多层嵌套的自定义布局
     * @param parentLayout 布局
     * @return
     */
    public ISpinnerConfig addParentLayout(@NonNull int parentLayout) {
        this.parentLayout = parentLayout;
        return this;
    }
    /**
     * 添加二级菜单的布局（使用TextView作为根布局的Layout,另外id名称应该为text1）
     * @param childLayout
     * @return
     */
    public ISpinnerConfig addChildLayout(@NonNull int childLayout) {
        this.childLayout = childLayout;
        return this;
    }

    /**
     * 添加选中后的背景
     * @param selectorBg
     */
    public ISpinnerConfig addSelectorBg(Drawable selectorBg) {
        this.selector = selectorBg;
        return this;
    }

    /**
     * 添加未选中的背景
     * @param notSelectorBg
     * @return
     */
    public ISpinnerConfig addNotSelectorBg(Drawable notSelectorBg) {
        this.notSelector = notSelectorBg;
        return this;
    }

    /**
     * 添加选中的文字颜色
     * @param selectorTextColor
     * @return
     */
    public ISpinnerConfig addSelectorTextColor(int selectorTextColor) {
        this.selectorTextColor = selectorTextColor;
        return this;
    }

    /**
     * 添加未选中的文字颜色
     * @param notSelectorTextColor
     */
    public ISpinnerConfig addNotSelectorTextColor(int notSelectorTextColor) {
        this.notSelectorTextColor = notSelectorTextColor;
        return this;
    }

    /**
     * 外部背景颜色
     * @param externalBackground
     * @return
     */
    public ISpinnerConfig addExternalBackground(Drawable externalBackground) {
        this.externalBackground = externalBackground;
        return this;
    }

    public ISpinnerConfig addListener(ISpinnerUtil.ISpinnerListener listener) {
        this.listener = listener;
        return this;
    }

    public ISpinnerConfig build(){
        if (parentContent.length == 0){
            Log.e(TAG, "error: parentContent length is 0");
            return this;
        }
        if (parentContent.length!=childContent.length){
            Log.e(TAG, "error: parentContent length != childContent length");
            return this;
        }
        if (parentLayout == 0 || childLayout == 0){
            Log.e(TAG, "error: parentLayout and childLayout is null");
            return this;

        }
        mUtil = new ISpinnerUtil();
        mUtil.init(this);
        return this;
    }

    public void show(View view){
        if (mUtil == null){
            Log.e(TAG, "error:not build");
            return;
        }
        mUtil.show(view);
    }

    public void dismiss(){
        if (mUtil == null){
            Log.e(TAG, "error:not build");
            return;
        }
        mUtil.dismiss();

    }

    public void destroy(){
        if (mUtil == null){
            Log.e(TAG, "error:not build");
            return;
        }
        mUtil.destroy();
    }

}
