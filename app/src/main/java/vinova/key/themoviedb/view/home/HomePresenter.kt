package vinova.key.themoviedb.view.home

import android.content.Context
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import vinova.key.themoviedb.data.model.api.MovieManager
import vinova.key.themoviedb.data.model.data.Movie
import vinova.key.themoviedb.view.adapter.MovieAdapter

class HomePresenter() {


    fun getData( context: Context): MovieAdapter {
        val listMovie = mutableListOf<Movie>()
        val movieAdapter = MovieAdapter(context, listMovie)
        val page: Int = 1
        val apiManager: MovieManager by lazy { MovieManager() }
        val compo by lazy { CompositeDisposable() }
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
    fun onLoadMore(){
        
    }


}