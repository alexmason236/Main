//package com.example.main;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.io.UnsupportedEncodingException;
//import java.net.*;
//import java.util.Map;
//
//public class HttpUtils {
//    private static String PATH = "http://106.13.184.91:8080/product/getTestMsg";
//    private static URL url;
//    public HttpUtils() {
//        // TODO Auto-generated constructor stub
//    }
//
//    static{
//        try {
//            url = new URL(PATH);
//        } catch (MalformedURLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//    /**
//     * @param params 填写的url的参数
//     * @param encode 字节编码
//     */
//    public static void sendPostMessage(Map<String, String> params, String encode){
//        StringBuffer buffer = new StringBuffer();
//        try {//把请求的主体写入正文！！
//            if(params != null&&!params.isEmpty()){
//                //迭代器
//                for(Map.Entry<String, String> entry : params.entrySet()){
//                    buffer.append(entry.getKey()).append("=").
//                            append(URLEncoder.encode(entry.getValue(),encode)).
//                            append("&");
//                }
//            }
////            System.out.println(buffer.toString());
//            //删除最后一个字符&，多了一个;主体设置完毕
//            buffer.deleteCharAt(buffer.length()-1);
//            byte[] mydata = buffer.toString().getBytes();
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setConnectTimeout(3000);
//            connection.setDoOutput(true);//表示向服务器写数据
//            //获得上传信息的字节大小以及长度
//
//            connection.setRequestMethod("POST");
//            //是否使用缓存
//            connection.setUseCaches(false);
//            //表示设置请求体的类型是文本类型
//            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//
//            connection.setRequestProperty("Content-Length", String.valueOf(mydata.length));
//            connection.connect();   //连接，不写也可以。。？？有待了解
//
//            //获得输出流，向服务器输出数据
//            OutputStream outputStream = connection.getOutputStream();
//            outputStream.write(mydata,0,mydata.length);
//            outputStream.flush();
//        } catch (UnsupportedEncodingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//    }
//}
