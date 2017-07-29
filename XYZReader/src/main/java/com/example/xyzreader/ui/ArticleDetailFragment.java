package com.example.xyzreader.ui;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ShareCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xyzreader.R;
import com.example.xyzreader.data.Article;

import java.util.Arrays;
import java.util.List;

/**
 * A fragment representing a single Article detail screen. This fragment is
 * either contained in a {@link ArticleListActivity} in two-pane mode (on
 * tablets) or a {@link ArticleDetailActivity} on handsets.
 */
public class ArticleDetailFragment extends Fragment
         {
    private static final String TAG = "ArticleDetailFragment";

    public static final String ARG_ITEM_ID = "item_id";
    public static final String ARG_ARTICLE = "article";

    private View mRootView;
    private RecyclerView mRecycleView;
    private BodyTextAdapter mAdapter;
    private ImageView mPhotoView;
    private Article mArticle;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ArticleDetailFragment() {
    }

    public static ArticleDetailFragment newInstance(long itemId) {
        Bundle arguments = new Bundle();
        arguments.putLong(ARG_ITEM_ID, itemId);
        ArticleDetailFragment fragment = new ArticleDetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    public static ArticleDetailFragment newInstance(Article article) {
        Bundle arguments = new Bundle();
        arguments.putParcelable(ARG_ARTICLE, article);
        ArticleDetailFragment fragment = new ArticleDetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ARTICLE)){
            mArticle = getArguments().getParcelable(ARG_ARTICLE);
        }

        setHasOptionsMenu(true);
    }

    public ArticleDetailActivity getActivityCast() {
        return (ArticleDetailActivity) getActivity();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_article_detail, container, false);

        mPhotoView = (ImageView) mRootView.findViewById(R.id.photo);
        mRootView.findViewById(R.id.share_fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(Intent.createChooser(ShareCompat.IntentBuilder.from(getActivity())
                        .setType("text/plain")
                        .setText("Some sample text")
                        .getIntent(), getString(R.string.action_share)));
            }
        });

        bindViews();
        return mRootView;
    }

    private void bindViews() {
        if (mRootView == null) {
            return;
        }
        mRecycleView = (RecyclerView) mRootView.findViewById(R.id.rv_body_text);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        TextView bodyView = (TextView) mRootView.findViewById(R.id.article_body);
        CollapsingToolbarLayout ctb = (CollapsingToolbarLayout) mRootView.findViewById(R.id.collapsing_tool_bar);

        bodyView.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "Rosario-Regular.ttf"));

        if (mArticle != null) {

            ctb.setTitle(mArticle.getTitle());

            //bodyView.setText(mArticle.getBody().substring(0, 10000));
            parseHTMLTestAsyncAndSetText(bodyView);

            /*ImageLoaderHelper.getInstance(getActivity()).getImageLoader()
                    .get(mArticle.getPhotoUrl(), new ImageLoader.ImageListener() {
                        @Override
                        public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                            Bitmap bitmap = imageContainer.getBitmap();
                            if (bitmap != null) {
                                mPhotoView.setImageBitmap(bitmap);
                            }
                        }

                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    });*/
        } else {
            mRootView.setVisibility(View.GONE);
            bodyView.setText("Loading. Please wait...");
        }
    }

    private void parseHTMLTestAsyncAndSetText(final TextView view){
        new AsyncTask<Void, Void, List<String>>(){

            @Override
            protected List<String> doInBackground(Void... params) {
                //Log.d("body", "body length: "+mArticle.getBody().length());
                //return Html.fromHtml(mCursor.getString(ArticleLoader.Query.BODY).replaceAll("(\r\n|\n)", "<br />"));
                //Spanned spanned = Html.fromHtml(mArticle.getBody().replaceAll("(\r\n|\n)", "|"));
                //String[] paras = mArticle.getBody().toString().split("\n\n");
                //Log.d("paras", "paras length: "+paras.length);
                String[] paras = mArticle.getBody().toString().split("\r\n");
                return Arrays.asList(paras);
            }

            @Override
            protected void onPostExecute(List<String> bodyTextList) {
                super.onPostExecute(bodyTextList);
                if (mAdapter == null) {
                    mAdapter = new BodyTextAdapter(bodyTextList);
                    mRecycleView.setAdapter(mAdapter);
                } else {
                    mAdapter.updateList(bodyTextList);
                }
            }
        }.execute();
    }
}
