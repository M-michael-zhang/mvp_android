package com.example.huqiang.course.retrofit;

import com.example.huqiang.course.data.movie.Movie;
import com.example.huqiang.course.data.book.Book;
import com.example.huqiang.course.data.music.Music;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET(Api.HOT_MOVIE)
    Observable<Movie> getHotMovie();

    @GET(Api.TOP_MOVIE)
    Observable<Movie> getTopMovie();

    @GET(Api.Suspense_Book)
    Observable<Book> getSuspenseBook();

    @GET(Api.Life_Book)
    Observable<Book> getLifeBook();

    @GET(Api.Young_Music)
    Observable<Music> getYoungMusic();

    @GET(Api.Jay_Music)
    Observable<Music> getJayMusic();



//    @GET(Api.HOT_MOVIE)
//    Observable<Movie> getTop(@Query("start") String start, @Query("count") String count);
//
//    @GET(Api.HOT_MOVIE)
//    Observable<Movie> get(@Path("id") String id);
}
