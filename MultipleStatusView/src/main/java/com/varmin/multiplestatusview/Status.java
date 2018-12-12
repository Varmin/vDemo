package com.varmin.multiplestatusview;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by HuangYang
 * on 2018/12/12  16:00.
 * 文件描述：
 */
//@StringDef
@Retention(RetentionPolicy.SOURCE)
public @interface Status {
    String LOADING = "loading";

    String ERR_NET = "err_net";
    String EMPTY = "empty";

    String ERROR = "error";
    String SUCCESS = "success";

}
