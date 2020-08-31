package com.bat.spring.resource.resourceloader;

import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

/**
 * {@link org.springframework.core.io.FileSystemResourceLoader} 使用
 *
 * @author ZhengYu
 * @version 1.0 2020/9/1 0:44
 **/
public class FileSystemResourceLoaderTest {
    public static void main(String[] args) throws IOException {
        // 新建一个 FileSystemResourceLoader 对象
        FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();

        // 加载资源
        Resource resource = fileSystemResourceLoader.getResource("D:\\temp\\customConfig.properties");

        // 编码为字符流
        EncodedResource encodedResource = new EncodedResource(resource, Charset.forName("UTF-8"));
        try (Reader reader = encodedResource.getReader()) {
            BufferedReader bf = new BufferedReader(reader);
            System.out.println(bf.readLine());
        }
    }
}
