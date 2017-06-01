package com.tata.android.http.upload;

/**
 * Description:
 * Creator:Terry
 * Date:2017/5/17 下午6:23
 */

public interface IUploadFile {

    String BOUNDARY = "--------WebKitFormBoundaryijSBNZTd7Vg0UGvk";
    String END = "\r\n";
    String TWO_HYPHENS = "--";

    void upload();
}
