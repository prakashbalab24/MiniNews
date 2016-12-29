package mini.news;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import mini.news.adapter.News;
import mini.news.adapter.NewsAdapter;
import mini.news.data.JsonParser;

/**
 * Created by prakash-bala on 29/12/16.
 */

public class MainFragment extends Fragment {
    private static final String URL = "URL";
    private ProgressBar progress;
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private List<News> newsList = new ArrayList<>();

    public MainFragment() {
    }

    public static MainFragment newInstance(String url) {
        MainFragment staticPagesFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString(URL, url);
        staticPagesFragment.setArguments(bundle);
        return staticPagesFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.mainfragment, container, false);
        String url = getArguments().getString(URL);
       // progress = (ProgressBar)view.findViewById(R.id.progressBar);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        newsAdapter = new NewsAdapter(getActivity(),newsList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(),1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(newsAdapter);
        new JsonParser(newsAdapter,newsList).execute();
        return view;
    }
}
