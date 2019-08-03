package net.furkanakdemir.moviesample.data;

public class Movie {

    private int id;
    private String name;
    private String releaseDate;
    private String overview;
    private String posterUrl;
    private String backdropUrl;

    public int getId() {
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
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        Movie article = (Movie) obj;
        return article.id == this.id;
    }


    public static final class MovieBuilder {
        private int id;
        private String name;
        private String releaseDate;
        private String overview;
        private String posterUrl;
        private String backdropUrl;

        private MovieBuilder() {
        }

        public static MovieBuilder movieBuilder() {
            return new MovieBuilder();
        }

        public MovieBuilder id(int id) {
            this.id = id;
            return this;
        }

        public MovieBuilder name(String name) {
            this.name = name;
            return this;
        }

        public MovieBuilder releaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public MovieBuilder overview(String overview) {
            this.overview = overview;
            return this;
        }

        public MovieBuilder posterUrl(String posterUrl) {
            this.posterUrl = posterUrl;
            return this;
        }

        public MovieBuilder backdropUrl(String backdropUrl) {
            this.backdropUrl = backdropUrl;
            return this;
        }

        public Movie build() {
            Movie movie = new Movie();
            movie.overview = this.overview;
            movie.releaseDate = this.releaseDate;
            movie.id = this.id;
            movie.name = this.name;
            movie.posterUrl = this.posterUrl;
            movie.backdropUrl = this.backdropUrl;
            return movie;
        }
    }
}
