package com.varmin.vdemo.fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.varmin.vdemo.R;
import com.varmin.vdemo.base.BaseFragment;
import butterknife.BindView;

public class CoverAddHidenFragment extends BaseFragment {
    @BindView(R.id.tv_frag_name)
    TextView tvName;
    @BindView(R.id.tv_frag_info)
    TextView tvInfo;
    private int index = -1;

    public static CoverAddHidenFragment getInstance(int index){
        CoverAddHidenFragment fragment = new CoverAddHidenFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void parseArguments(Bundle args) {
         index = args.getInt("index");
        switch (index) {
            case 0:
                tvInfo.setBackgroundResource(R.color.base_app_alert_bg);
                break;
            case 1:
                tvInfo.setBackgroundResource(R.color.base_gray_translate_9999);
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_cover_add_hiden;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvName.setText(hashCode()+"");
        int num = 100 + (int)(Math.random() * (800));
        tvInfo.animate().translationY(num).start();
        tvInfo.setText("transY="+num+", hasCode="+hashCode());
    }
}
