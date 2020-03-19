package com.example.dt.linktest.View.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.example.dt.linktest.Model.articles;
import com.example.dt.linktest.R;
import com.example.dt.linktest.ViewModel.MasterViewModel;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.imageview)
    ImageView imageview;
    @BindView(R.id.Title)
    TextView Title;
    @BindView(R.id.author)
    TextView author;
    @BindView(R.id.description)
    TextView description;

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.PublishedAt)
    TextView PublishedAt;
    articles articles = new articles();
    MasterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        viewModel = ViewModelProviders.of(this).get(MasterViewModel.class);


        articles = (com.example.dt.linktest.Model.articles) getIntent().getSerializableExtra("selecteditem");
        if (!articles.getUrlToImage().isEmpty()) {
            imageview.setVisibility(View.VISIBLE);
            Picasso.with(DetailsActivity.this).
                    load(articles.getUrlToImage()).into(imageview);
        }else {
            imageview.setVisibility(View.GONE);
        }
        Title.setText(articles.getTitle());
        author.setText(articles.getAuthor());
        description.setText(articles.getDescription());
        PublishedAt.setText(articles.getPublishedAt());
    }

    @OnClick(R.id.button)
    public void onClick() {
        viewModel.OpenURL(Uri.parse(articles.getUrl()),DetailsActivity.this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
