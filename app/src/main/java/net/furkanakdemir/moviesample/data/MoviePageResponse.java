package net.furkanakdemir.moviesample.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviePageResponse {


    @SerializedName("page")
    public int page;

    @SerializedName("total_results")
    public int totalResults;

    @SerializedName("total_pages")
    public int totalPages;

    @SerializedName("results")
    public List<MovieRaw> movieRawList;

    public class MovieRaw {


        @SerializedName("id")
        public int id;

        @SerializedName("title")
        public String title;

        @SerializedName("vote_average")
        public float votes;

        @SerializedName("poster_path")
        public String posterPath;

        @SerializedName("release_date")
        public String releaseDate;

        @SerializedName("overview")
        public String overview;

    }
}
