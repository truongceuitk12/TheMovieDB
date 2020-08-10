package vinova.key.themoviedb.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import vinova.key.themoviedb.data.model.MovieList
import vinova.key.themoviedb.utils.API_KEY

interface MovieService {
    @GET("movie/now_playing")
    fun getListMovie(
        @Query("api_key") token: String = API_KEY,
        @Query("page") page: Int = 1
    ): Call<MovieList>

}