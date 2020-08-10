package vinova.key.themoviedb.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*
import vinova.key.themoviedb.R
import vinova.key.themoviedb.data.model.api.MovieManager
import vinova.key.themoviedb.data.model.data.Movie
import vinova.key.themoviedb.view.adapter.MovieAdapter


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        home_recycler_view.run {val homePresenter = HomePresenter()
            adapter = homePresenter.getData(activity!!)
            layoutManager = LinearLayoutManager(activity!!)
        }

    }
    /*
    private var listMovie = mutableListOf<Movie>()
    private var page: Int = 1
    private val apiManager: MovieManager by lazy { MovieManager() }
    private val compo by lazy { CompositeDisposable() }
    val homePresenter = HomePresenter(activity!!, this)
        // homePresenter.getData()
        val movieAdapter = MovieAdapter(activity!!, listMovie)
        compo.add(
            apiManager.getListMovie(page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    movieAdapter.updateData(it.results!!)
                }, {
                })
        )
        home_recycler_view.run {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(activity!!)
        }*/


}