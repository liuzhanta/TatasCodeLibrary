package com.tata.android.http;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpTest implements HttpApi {
    
    @Override
    public String doHttpGet(String url) {
        HttpGet httpget = new HttpGet(url);
        HttpClient httpClient = new DefaultHttpClient();
        try {
            HttpResponse httpResponse = httpClient.execute(httpget);
            final int resposeCode = httpResponse.getStatusLine().getStatusCode();
            if (resposeCode == HTTP_200) {
                return EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
            }else{
                
            }
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public String doHttpResponse(String url) {
        return null;
    }
}
