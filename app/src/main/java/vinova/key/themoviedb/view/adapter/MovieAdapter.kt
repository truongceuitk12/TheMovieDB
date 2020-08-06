package vinova.key.themoviedb.view.adapter

import android.content.Context
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_now_playing.view.*
import vinova.key.themoviedb.R
import vinova.key.themoviedb.model.data.Movie
import vinova.key.themoviedb.presenter.base.IYoutube
import vinova.key.themoviedb.utils.POPULARITY_THRESHOLD
import vinova.key.themoviedb.utils.REGULAR
import vinova.key.themoviedb.utils.TRENDING

enum class TYPE { MOVIE, TRAILER }
class MovieAdapter(private val context: Context, private var movies: MutableList<Movie>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var youtubeClickedListener: IYoutube? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == TRENDING) {
            MovieViewHoler(
                LayoutInflater.from(context).inflate(R.layout.item_now_playing, parent, false),
                parent.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
            )
        } else {
            TrendingViewHoler(
                LayoutInflater.from(context).inflate(
                    R.layout.item_now_playing,
                    parent,
                    false
                )
            )

        }


    fun replaceMovieSet(newMovies: ArrayList<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = movies.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TRENDING) {
            (holder as MovieViewHoler).bind(movies[position])
        } else {
            (holder as TrendingViewHoler).bind(movies[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (movies[position].voteAverage >= POPULARITY_THRESHOLD) TRENDING else REGULAR
    }

    inner class TrendingViewHoler(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie) {
            itemView.apply {
                item_title.text = movie.title
                item_desc.text = movie.overview
            }
        }
    }

    inner class MovieViewHoler(view: View, isPortrait: Boolean) : RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie) {
            itemView.apply {
                item_title.text = movie.title
                item_desc.text = movie.overview
            }
        }
    }


}