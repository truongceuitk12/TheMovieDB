package vinova.key.themoviedb.model.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    val movieService: MovieService

    init {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.themoviedb.org")
            .build()

        movieService = retrofit.create(MovieService::class.java)
    }
}