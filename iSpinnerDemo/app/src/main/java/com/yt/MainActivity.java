package com.yt;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.yt.iSpinner.ISpinnerConfig;
import com.yt.iSpinner.ISpinnerUtil;
import com.yt.ispinnerdemo.ispinnerdemo.R;

public class MainActivity extends AppCompatActivity {
    private DisplayMetrics mDisplayMetrics;
    private TextView mText;
    private ISpinnerConfig mISpinnerConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDisplayMetrics = getResources().getDisplayMetrics();
        mText = (TextView)findViewById(R.id.text);
        mText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mISpinnerConfig.show(mText);

            }
        });
        initiSpinner();
    }

    public void initiSpinner(){
        String[] parentContent = {"父级一","父级二","父级三"};
        String[][] childContent = {{"子级1_1","子级1_2","子级1_3"},{"子级2_1","子级2_2","子级2_3"},{"子级3_1","子级3_2","子级3_3"}};
        mISpinnerConfig = new ISpinnerConfig(this,mDisplayMetrics.widthPixels,500)
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
    }
}
