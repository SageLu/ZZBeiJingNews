//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations 3.1.
//


package com.tiger.quicknews.adapter;

import android.content.Context;

public final class PicuterAdapter_
    extends PicuterAdapter
{

    private Context context_;

    private PicuterAdapter_(Context context) {
        context_ = context;
        init_();
    }

    public static PicuterAdapter_ getInstance_(Context context) {
        return new PicuterAdapter_(context);
    }

    private void init_() {
        context = context_;
    }

    public void rebind(Context context) {
        context_ = context;
        init_();
    }

}
