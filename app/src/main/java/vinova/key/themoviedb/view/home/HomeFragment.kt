package vinova.key.themoviedb.view.home

import android.annotation.SuppressLint
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
import vinova.key.themoviedb.databinding.FragmentHomeBinding
import vinova.key.themoviedb.data.model.api.MovieManager
import vinova.key.themoviedb.data.model.data.Movie
import vinova.key.themoviedb.view.adapter.MovieAdapter


class HomeFragment : Fragment(),  IHomeContact.IHomeView {

    private var listMovie = mutableListOf<Movie>()
    private var page : Int = 1
    private val apiManager: MovieManager by lazy { MovieManager() }
    private val compo by lazy { CompositeDisposable() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.listener = this@HomeFragment
        binding.lifecycleOwner = this@HomeFragment
        return binding.root
    }


    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        }
    }



}