package com.bat.spring.resource.nativeexpand.method1;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * {@link URLConnection}
 *
 * @author ZhengYu
 * @version 1.0 2020/9/1 1:32
 **/
public class XURLConnection extends URLConnection {

    private ClassPathResource classPathResource;

    public XURLConnection(URL url) {
        super(url);
        this.classPathResource = new ClassPathResource(url.getPath());
    }

    @Override
    public void connect() throws IOException {

    }

    @Override
    public InputStream getInputStream() throws IOException {
        return classPathResource.getInputStream();
    }
}
