package com.tata.android.http;

public interface HttpApi {
    int HTTP_200 = 200;
    
    String doHttpGet(String url);
    
    String doHttpResponse(String url);
}
