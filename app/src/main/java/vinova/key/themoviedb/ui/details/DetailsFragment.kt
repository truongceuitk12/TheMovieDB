package vinova.key.themoviedb.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_details.*
import vinova.key.themoviedb.R
import vinova.key.themoviedb.data.model.Movie
import vinova.key.themoviedb.ui.base.adapter.MovieAdapter


class DetailsFragment() : Fragment(), IDetailsView {

    private var movieList = mutableListOf<Movie>()
    private lateinit var movieAdapter : MovieAdapter

    private var presenter: IDetailsPresenter? = null


    override fun setPresenter(presenter: IDetailsPresenter) {
        this.presenter = presenter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_details, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = DetailsPresenter(activity!!, this)
        presenter?.getData(1)



    }

    override fun showData(movies: MutableList<Movie>) {
        if(movies.isNullOrEmpty()) return
        if(movieList.isNullOrEmpty()) movieList.addAll(movies)
        Log.d("Results1" , "${movieList}")
        movieAdapter = MovieAdapter(activity!!,movieList)
        detail_recycler_view.run {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(activity!!)
        }


    }


}