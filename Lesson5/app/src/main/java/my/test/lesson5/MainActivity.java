package my.test.lesson5;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String PAGE = "PAGE";
    private EditText editText;
    private WebView webView;
    private TextView statusText;
    private Button searchButton;
    private RequestMaker requestMaker;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        requestMaker = new RequestMaker(new OnRequestListener() {
            @Override
            public void onStatusProgress(String updateProgress) {
                statusText.setText(updateProgress);
            }

            @Override
            public void onComplete(String result) {
                webView.loadData(result, "text/html; charset=utf-8", "utf-8");
            }
        });

        setOnClick();
    }

    private void setOnClick() {
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestMaker.makeRequest(editText.getText().toString());
            }
        });
    }

    private void init() {
        editText = findViewById(R.id.edit_text);
        webView = findViewById(R.id.web_view);
        statusText = findViewById(R.id.status_text);
        searchButton = findViewById(R.id.find_button);

        String homePage = getString(R.string.home_page);
        sharedPreferences = getSharedPreferences("Name", MODE_PRIVATE);

        editText.setText(sharedPreferences.getString(PAGE, homePage));
        editText.setNextFocusRightId(R.id.find_button);
    }

    @Override
    protected void onPause() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PAGE, editText.getText().toString());
        editor.apply();
        super.onPause();
    }
}
