package vinova.key.themoviedb.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import vinova.key.themoviedb.data.model.Movie
import vinova.key.themoviedb.data.network.MovieManager

import vinova.key.themoviedb.ui.base.adapter.MovieAdapter
import vinova.key.themoviedb.utils.LOAD
import vinova.key.themoviedb.utils.LOAD_MORE

class HomePresenter(val view: IHomeView) : IHomePresenter {
    private val listMovie = mutableListOf<Movie>()
    private val apiManager: MovieManager by lazy { MovieManager() }
    private val compo by lazy { CompositeDisposable() }


    @SuppressLint("LogNotTimber")
    override fun getData(context: Context, page: Int, type: Int): MovieAdapter {
        val movieAdapter = MovieAdapter(context, listMovie)
        compo.add(
            apiManager.getListMovie(page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    when (type) {
                        LOAD ->  it?.let{movieAdapter.updateData(it.results!!) }
                        LOAD_MORE -> it?.let { movieAdapter.getMoreData(it.results!!) }
                    }
                }, {
                    Log.d("Throw", "${it}")
                })
        )
        return movieAdapter
    }



}