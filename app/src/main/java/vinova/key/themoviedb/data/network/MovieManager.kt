package vinova.key.themoviedb.data.network

import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vinova.key.themoviedb.data.model.MovieList
import vinova.key.themoviedb.data.model.Trailer
import vinova.key.themoviedb.utils.BASE_URL

class MovieManager {
    private val _apiRestFull: MovieService by lazy {
        getHelperRestFull()!!.create(MovieService::class.java)
    }

    companion object {

        private var sRestFull: Retrofit? = null


        fun getHelperRestFull(): Retrofit? {
            if (sRestFull == null) {
                sRestFull = Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return sRestFull
        }
    }

    private fun <T : Any> buildRequest(call: Call<T>): Single<T> {
        return Single.create {
            call.enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    try {
                        it.onSuccess(response.body()!!)
                    } catch (ex: Exception) {

                    }
                }

                override fun onFailure(p0: Call<T>, response: Throwable) {
                    it.onError(response)
                }
            })
        }
    }

    fun getListMovie(page: Int= 1): Single<MovieList> {
        return buildRequest(_apiRestFull.getListMovie(page = page))
    }
    fun getTrailerInfor(id: Int): Single<Trailer> {
        return buildRequest(_apiRestFull.getMovieInforVideo(id))
    }
}