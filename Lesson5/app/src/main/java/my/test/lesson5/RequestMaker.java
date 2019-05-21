package my.test.lesson5;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class RequestMaker {

    private OnRequestListener listener;

    public RequestMaker(OnRequestListener listener) {
        this.listener = listener;
    }

    public void makeRequest(String uri) {
        Requester requester = new Requester(listener);
        requester.execute(uri);
    }


}
