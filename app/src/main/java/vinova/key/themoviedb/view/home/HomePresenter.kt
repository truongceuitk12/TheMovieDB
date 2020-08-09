package vinova.key.themoviedb.view.home

import android.content.Context
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import vinova.key.themoviedb.data.model.api.MovieManager
import vinova.key.themoviedb.data.model.data.Movie
import vinova.key.themoviedb.view.adapter.MovieAdapter

class HomePresenter(val context: Context) : IHomePresenter {

    private var listMovie = mutableListOf<Movie>()
    private var page: Int = 1
    private val apiManager: MovieManager by lazy { MovieManager() }
    private val compo by lazy { CompositeDisposable() }

    override fun getData(): MovieAdapter {
        val movieAdapter = MovieAdapter(context,listMovie )
        compo.add(
            apiManager.getListMovie(page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    movieAdapter.updateData(it.results!!)

                }, {
                })
        )
        return movieAdapter
    }



}