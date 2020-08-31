package sun.net.www.protocol.x;

import com.bat.spring.resource.nativeexpand.method1.XURLConnection;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * {@link URLStreamHandler} 自定义实现
 *
 * @author ZhengYu
 * @version 1.0 2020/9/1 1:30
 **/
public class Handler extends URLStreamHandler {


    @Override
    protected URLConnection openConnection(URL u) throws IOException {
        return new XURLConnection(u);
    }
}
