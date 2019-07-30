package net.furkanakdemir.moviesample.ui;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieViewItem implements Parcelable {

    private String id;
    private String name;
    private String releaseDate;
    private String overview;
    private String posterUrl;
    private String backdropUrl;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getBackdropUrl() {
        return backdropUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.releaseDate);
        dest.writeString(this.overview);
        dest.writeString(this.posterUrl);
        dest.writeString(this.backdropUrl);
    }

    public MovieViewItem() {
    }

    protected MovieViewItem(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.releaseDate = in.readString();
        this.overview = in.readString();
        this.posterUrl = in.readString();
        this.backdropUrl = in.readString();
    }

    public static final Creator<MovieViewItem> CREATOR = new Creator<MovieViewItem>() {
        @Override
        public MovieViewItem createFromParcel(Parcel source) {
            return new MovieViewItem(source);
        }

        @Override
        public MovieViewItem[] newArray(int size) {
            return new MovieViewItem[size];
        }
    };

    public static final class MovieViewItemBuilder {
        private String id;
        private String name;
        private String releaseDate;
        private String overview;
        private String posterUrl;
        private String backdropUrl;

        private MovieViewItemBuilder() {
        }

        public static MovieViewItemBuilder movieViewItemBuilder() {
            return new MovieViewItemBuilder();
        }

        public MovieViewItemBuilder id(String id) {
            this.id = id;
            return this;
        }

        public MovieViewItemBuilder name(String name) {
            this.name = name;
            return this;
        }

        public MovieViewItemBuilder releaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public MovieViewItemBuilder overview(String overview) {
            this.overview = overview;
            return this;
        }

        public MovieViewItemBuilder posterUrl(String posterUrl) {
            this.posterUrl = posterUrl;
            return this;
        }

        public MovieViewItemBuilder backdropUrl(String backdropUrl) {
            this.backdropUrl = backdropUrl;
            return this;
        }

        public MovieViewItem build() {
            MovieViewItem movieViewItem = new MovieViewItem();
            movieViewItem.releaseDate = this.releaseDate;
            movieViewItem.backdropUrl = this.backdropUrl;
            movieViewItem.id = this.id;
            movieViewItem.posterUrl = this.posterUrl;
            movieViewItem.name = this.name;
            movieViewItem.overview = this.overview;
            return movieViewItem;
        }
    }
}
