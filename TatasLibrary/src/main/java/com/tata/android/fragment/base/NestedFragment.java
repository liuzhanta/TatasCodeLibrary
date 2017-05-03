package com.tata.android.fragment.base;

import android.content.Intent;

public abstract class NestedFragment extends BaseTitleFragment {
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
    
    class ActivityResult {
        int requestCode;
        int resultCode;
        Intent data;
    }
}
