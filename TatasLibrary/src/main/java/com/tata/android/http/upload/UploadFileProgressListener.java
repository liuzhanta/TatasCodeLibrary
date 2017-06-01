package com.tata.android.http.upload;


/**
 * Description:
 * Creator:Terry
 * Date:2017/5/15 下午5:46
 */

public interface UploadFileProgressListener {
    void onProgress(long progress);

    void onSucceed(FileResult object);

    void onError(String errorMessage);
}
