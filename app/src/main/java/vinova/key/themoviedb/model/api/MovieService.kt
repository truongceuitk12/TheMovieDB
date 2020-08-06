package vinova.key.themoviedb.model.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import vinova.key.themoviedb.model.data.Movie
import vinova.key.themoviedb.model.data.MovieList

interface MovieService{
    @GET("movie/now_playing")
    fun getNowPlayingList(@Query("page") page: Int = 1): Call<MovieList>
}