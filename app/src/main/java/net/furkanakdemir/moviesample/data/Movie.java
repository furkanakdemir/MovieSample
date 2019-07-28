package net.furkanakdemir.moviesample.data;

public class Movie {

    private String id;
    private String name;
    private String releaseDate;
    private String overview;
    private String posterUrl;

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

    public static final class MovieBuilder {
        private String id;
        private String name;
        private String releaseDate;
        private String overview;
        private String posterUrl;

        private MovieBuilder() {
        }

        public static MovieBuilder movieBuilder() {
            return new MovieBuilder();
        }

        public MovieBuilder id(String id) {
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

        public Movie build() {
            Movie movie = new Movie();
            movie.posterUrl = this.posterUrl;
            movie.id = this.id;
            movie.overview = this.overview;
            movie.name = this.name;
            movie.releaseDate = this.releaseDate;
            return movie;
        }
    }
}
