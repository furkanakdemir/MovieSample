package net.furkanakdemir.moviesample.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import net.furkanakdemir.moviesample.R;

import javax.inject.Inject;

public class GlideImageLoader implements ImageLoader {


    private Context context;

    @Inject
    public GlideImageLoader(Context context) {

        this.context = context;
    }

    @Override
    public void load(ImageView imageView, String imageUrl) {
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_movie)
                .error(R.drawable.ic_movie)
                .into(imageView);
    }
}
