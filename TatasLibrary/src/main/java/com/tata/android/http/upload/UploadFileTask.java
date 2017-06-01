package com.tata.android.http.upload;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Description:文件上传任务
 * Creator:Terry
 * Date:2017/5/15 下午4:48
 */
public class UploadFileTask implements Runnable, IUploadFile {

    private static final String TAG = "UploadFileTask";

    private static final String FILE_SERVER_URL = "xxxxx";
    public static final String ATTACHMENT_TYPE = "attachment";

    private String filePath;
    private String fileName;
    private UploadFileProgressListener uploadFileProgressListener;

    public UploadFileTask(String filePath, UploadFileProgressListener uploadFileProgressListener) {
        this.filePath = filePath;
        fileName = splitFilePath(filePath);
        this.uploadFileProgressListener = uploadFileProgressListener;
    }

    public static String splitFilePath(String filePath) {
        String[] str = filePath.split("/");
        if (str != null && str.length > 0) {
            return str[str.length - 1];
        }
        return null;
    }

    @Override
    public void run() {
        try {
            File uploadFile = new File(filePath);
            final StringBuilder sb = new StringBuilder();
            /**
             * -------------------- 写表单数据 --------------------
             */
            appendForm(sb, "type", ATTACHMENT_TYPE);
            appendForm(sb, "name", fileName);

            /**
             * -------------------- 写文件 --------------------
             */
            sb.append(TWO_HYPHENS + BOUNDARY + END);
            sb.append("Content-Disposition: form-data; name=\"source\"; filename=\"" + fileName
                    + "\"" + END);
            sb.append(END);


            byte[] headerInfo = sb.toString().getBytes("UTF-8");
            byte[] endInfo = (END + TWO_HYPHENS + BOUNDARY + TWO_HYPHENS + END).getBytes("UTF-8");

            /**
             * -------------------- 开始post请求 --------------------
             */
            URL url = new URL(FILE_SERVER_URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            // 允许输入输出流
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);

            // 使用POST方法
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.setRequestProperty("Content-Type",
                    "multipart/form-data;boundary=" + BOUNDARY);
//            String cookieStr = AuthorizationManager.getInstance().getCookie().trim()
//                    + "; hb_dev_host=dev"; //
//            httpURLConnection.setRequestProperty("Cookie", cookieStr);
//            httpURLConnection.setRequestProperty("Authorization", "Bearer "
//                    + AuthorizationManager.getInstance().getAccessToken());


            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(headerInfo.length
                    + endInfo.length + uploadFile.length()));
            DataOutputStream dos = new DataOutputStream(httpURLConnection.getOutputStream());

            //写入头信息
            dos.write(headerInfo);

            //写入二进制文件流信息
            FileInputStream fis = new FileInputStream(uploadFile);
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = fis.read(buf)) != -1) {
                if (uploadFileProgressListener != null) {
                    uploadFileProgressListener.onProgress(len / uploadFile.length() * 100);
                }
                dos.write(buf, 0, len);
            }
            //写入文件尾
            dos.write(endInfo);

            //上传成功
            if (httpURLConnection.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        httpURLConnection.getInputStream()));
                String inputLine;
                StringBuilder result = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    result.append(inputLine);
                }
//                LogUtil.d(TAG, "run: sb  = " + result.toString());
//                FileResult fileResult = JsonParser.fromJson(result
//                        .toString(), FileResult.class);

                if (uploadFileProgressListener != null) {
                    uploadFileProgressListener.onSucceed(null);
                }
            } else {
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        httpURLConnection.getErrorStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    sb.append(inputLine);
                }
                in.close();

//                FileUpdateError error = JsonParser.fromJson(sb.toString(), FileUpdateError.class);
                if (uploadFileProgressListener != null) {
//                    uploadFileProgressListener.onError(error.getMessage());
                }
            }
            dos.flush();
            dos.close();
            fis.close();
        } catch (IOException e) {
            if (uploadFileProgressListener != null) {
                uploadFileProgressListener.onError(e.getMessage());
            }
        }
    }

    private void appendForm(StringBuilder sb, String key, String value) {
        sb.append(TWO_HYPHENS + BOUNDARY + END);
        sb.append("Content-Disposition: form-data; name=\"" + key + "\"" + END);
        sb.append(END);
        sb.append(value + END);
    }

    @Override
    public void upload() {
//        UploadQueue.getInstance().execute(this);
    }
}
