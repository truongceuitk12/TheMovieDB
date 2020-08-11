package vinova.key.themoviedb.data.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import vinova.key.themoviedb.data.model.MovieList
import vinova.key.themoviedb.data.model.Trailer
import vinova.key.themoviedb.utils.API_KEY
import vinova.key.themoviedb.utils.YOUTUBE_API

interface MovieService {
    @GET("movie/now_playing")
    fun getListMovie(
        @Query("api_key") token: String = API_KEY,
        @Query("page") page: Int = 1
    ): Call<MovieList>
    @GET("movie/{id}/trailers")
    fun getMovieInforVideo(
        @Path("id") id: Int,
        @Query("api_key") token: String = API_KEY
    ): Call<Trailer>

}