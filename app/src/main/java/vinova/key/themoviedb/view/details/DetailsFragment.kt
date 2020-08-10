package vinova.key.themoviedb.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_details.*
import vinova.key.themoviedb.R
import vinova.key.themoviedb.data.model.data.Movie
import vinova.key.themoviedb.view.adapter.MovieAdapter


class DetailsFragment() : Fragment(), IDetailsView {
    val listMovie = mutableListOf<Movie>()
    val movieAdapter = MovieAdapter(context!!, listMovie)
    private val movies = mutableListOf<Movie>()
//    private var presenter: IDetailsPresenter = DetailsPresenter(activity!!, this)
    private var presenter: IDetailsPresenter? = null

//    init {
//
//    }

    override fun setPresenter(presenter: IDetailsPresenter) {
        this.presenter = presenter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = DetailsPresenter(activity!!, this)
        return inflater.inflate(R.layout.fragment_details, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.getData(1)

        dt.run {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(activity!!)
        }


    }

    override fun showData(movies: MutableList<Movie>) {
        movieAdapter.updateData(movies)
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}