package my.test.lesson5;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Requester extends AsyncTask<String ,String ,String> {
    private OnRequestListener listener;

    public Requester(OnRequestListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPostExecute(String content) {
        listener.onComplete(content);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        listener.onStatusProgress(values[0]);
    }

    @Override
    protected String doInBackground(String... strings) {
        return getResourceURL(strings[0]);
    }
    private String getResourceURL(String uri) {
        HttpsURLConnection urlConnection = null;
        try {
            URL url = new URL(uri);
            urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            publishProgress("Prepare data");
            urlConnection.connect();
            publishProgress("Connection");
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder buf = new StringBuilder();
            publishProgress("Get data");
            String line = null;
            int numLine = 0;
            while ((line = in.readLine()) != null) {
                numLine++;
                publishProgress(String.format("Line %d", numLine));
                buf.append(line);
                buf.append(System.getProperty("line.separator"));
            }
            return buf.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            publishProgress("Url exception");

        } catch (IOException e) {
            e.printStackTrace();
            publishProgress("IO exception");
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return "Error....sorry";

    }
}
