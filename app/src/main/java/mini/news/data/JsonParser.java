package mini.news.data;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import mini.news.adapter.News;
import mini.news.adapter.NewsAdapter;


/**
 * Created by prakash-bala on 14/12/16.
 */

public class JsonParser extends AsyncTask<Void, Void, Void> {
    private NewsAdapter newsAdapter;
    private List<News> newsList;
    String url = "";


    public JsonParser(NewsAdapter newsAdapter,List<News> newsList)
    {
        this.newsAdapter = newsAdapter;
        this.newsList = newsList;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Void doInBackground(Void... arg0) {
        RequestHandler sh = new RequestHandler();
        String jsonStr = sh.makeServiceCall(url);
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                JSONArray lArr = jsonObj.optJSONArray("articles");

                for (int i = 0; i < lArr.length(); i++) {

                    JSONObject c = lArr.getJSONObject(i);

                        String title = c.getString("title");
                        String imageUrl = c.getString("urlToImage");
                    Log.i("newstitle",title+" "+imageUrl);
                    News news = new News(title,imageUrl);
                    newsList.add(news);


                }

            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("myinfo", "> " + e);
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
        }

        return null;
    }


    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        newsAdapter.notifyDataSetChanged();
    }
}