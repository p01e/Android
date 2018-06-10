package tops.com.barberapp.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import tops.com.barberapp.R;
import tops.com.barberapp.model.Article;
import tops.com.barberapp.other.MyAsyncTask;

public class ApiTestActivity extends AppCompatActivity implements MyAsyncTask.OnResponseListener {

    private ListView listView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_test);
        context=this;
        listView=findViewById(R.id.listView);
        MyAsyncTask asyncTask=new MyAsyncTask(context,this);
        String url="https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=0c2367225d2440619d16b78342b56ab6";
        asyncTask.execute(url);
    }


    @Override
    public void onResponse(String result) {
        try {
            JSONObject jsonObject=new JSONObject(result);
            String status=jsonObject.getString("status");
            if(status.equals("ok")){
                int totalResults=jsonObject.getInt("totalResults");
                JSONArray articlesArray=jsonObject.getJSONArray("articles");
                ArrayList<Article> articles=new ArrayList<>();
                for(int i=0;i<articlesArray.length();i++)
                {
                    JSONObject articleObject=articlesArray.getJSONObject(i);
                    Article article=new Article();
                    article.setAuthor(articleObject.getString("author"));
                    article.setTitle(articleObject.getString("title"));
                    article.setDescription(articleObject.getString("description"));
                    article.setUrl(articleObject.getString("url"));
                    article.setUrlToImage(articleObject.getString("urlToImage"));
                    article.setPublishedAt(articleObject.getString("publishedAt"));
                    articles.add(article);
                }
                ArrayAdapter<Article> adapter=new ArrayAdapter<Article>(context,android.R.layout.simple_list_item_1,articles);
                listView.setAdapter(adapter);
            }else{
                String message=jsonObject.getString("message");
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
