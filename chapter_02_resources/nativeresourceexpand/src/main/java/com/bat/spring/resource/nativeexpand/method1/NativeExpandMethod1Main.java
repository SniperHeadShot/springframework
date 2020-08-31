package com.bat.spring.resource.nativeexpand.method1;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * 自定义资源协议
 *
 * @author ZhengYu
 * @version 1.0 2020/9/1 1:26
 **/
public class NativeExpandMethod1Main {
    public static void main(String[] args) throws IOException {
        // 使用自定义的 X 协议
        URL url = new URL("x:///customConfig.properties");

        // 读取流并打印结果
        InputStream inputStream = url.openConnection().getInputStream();
        byte[] result = new byte[inputStream.available()];
        int read = inputStream.read(result);
        System.out.println(String.format("读取 %d 个字节, 结果为: %s", read, new String(result, Charset.forName("UTF-8"))));
    }
}
