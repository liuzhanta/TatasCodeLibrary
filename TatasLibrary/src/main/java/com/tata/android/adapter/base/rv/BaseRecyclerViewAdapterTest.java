package com.tata.android.adapter.base.rv;

import android.content.Context;


/**
 * Desc:
 * Author: Terry
 * Date:2016-10-13
 */
public class BaseRecyclerViewAdapterTest extends BaseRecyclerViewAdapter<String> {

    public BaseRecyclerViewAdapterTest(Context mContext) {
        super(mContext);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void onBindViewData(RecyclerViewHolder holder, String s, int position) {

    }
}
