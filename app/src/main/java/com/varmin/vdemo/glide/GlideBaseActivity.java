package com.varmin.vdemo.glide;

import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseActivity;
import butterknife.BindView;

public class GlideBaseActivity extends BaseActivity {

    @BindView(R.id.img_1)
    ImageView img1;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_glide_base;
    }

    @Override
    protected void initData() {
        String url = "https://ws1.sinaimg.cn/large/0065oQSqgy1fze94uew3jj30qo10cdka.jpg";
        /*Glide.with(this).load(url)
                .placeholder(R.drawable.black)
                .error(R.color.base_red)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(img1);*/
        Glide.with(this)
                .load(url)
                .into(img1);
    }


//    java.lang.Throwable: Assertion failed: Project view pane not found: ProjectPane; subId:null; project: Project '/Users/varmin/code/yang/android/project/VDemo' VDemo


}
