# ISpinner
一个二级下拉菜单栏


这是一个封装完整的二级菜单栏View


String[] parentContent = {"父级一","父级二","父级三"};
        String[][] childContent = {{"子级1_1","子级1_2","子级1_3"},{"子级2_1","子级2_2","子级2_3"},{"子级3_1","子级3_2","子级3_3"}};
        ISpinnerConfig mISpinnerConfig = new ISpinnerConfig(this,mDisplayMetrics.widthPixels,500)
                .addParentContent(parentContent)
                .addChildContent(childContent)
                .addParentLayout(R.layout.text_health_spinner_item)
                .addChildLayout(R.layout.text_health_spinner_item)
                .addSelectorBg(getDrawable(R.drawable.ispinner_item_selector_bg))
                .addSelectorTextColor(R.color.colorLightOrange)
                .addNotSelectorTextColor(R.color.colorBlack1)
                .addNotSelectorBg(getDrawable(R.drawable.ispinner_item_no_selector_bg))
                .addExternalBackground(getDrawable(R.drawable.ispinner_bg))
                .addListener(new ISpinnerUtil.ISpinnerListener() {
                    @Override
                    public void onChildItemClick(int parentPosition, String parentText, int childPosition, String childText) {

                    }

                    @Override
                    public void onShow() {

                    }

                    @Override
                    public void onDismiss() {

                    }
                })
                .build();
        mISpinnerConfig.show(mText);
        //mISpinnerConfig.dismiss();
