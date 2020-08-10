package vinova.key.themoviedb.ui.base.adapter

import android.content.Context
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_now_playing.view.*
import kotlinx.android.synthetic.main.item_trending.view.*
import vinova.key.themoviedb.R
import vinova.key.themoviedb.data.model.Movie
import vinova.key.themoviedb.ui.base.onItemClickListener
import vinova.key.themoviedb.utils.*

class MovieAdapter(
    private val context: Context,
    private var movies: MutableList<Movie>

) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var itemClick: onItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == TRENDING) MovieViewHoler(LayoutInflater.from(context)
            .inflate(R.layout.item_now_playing, parent, false),
            parent.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
        else TrendingViewHoler( LayoutInflater.from(context)
            .inflate(R.layout.item_trending, parent, false))


    fun updateData(newMovies: MutableList<Movie>) {
        movies.clear()
        movies = newMovies
        notifyDataSetChanged()
    }

    fun getMoreData(newPage: MutableList<Movie>) {
        val size = newPage.size
        movies.addAll(newPage)
        notifyItemRangeInserted(size, newPage.size)
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
        return if (movies[position].vote_average!! >= POPULARITY_THRESHOLD) TRENDING else REGULAR
    }

    inner class TrendingViewHoler(private val view: View) :
        RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie) {
            view.apply {
                item_tv_trending_title.text = movie.title
                item_tv_trending_desc.text = movie.overview
                item_imv_trending.bindImageFromUrl(IMAGE_URL + movie.backdrop_path)
                setOnClickListener {
                    itemClick?.onItemClick(movie)
                }
            }
        }
    }

    inner class MovieViewHoler(private val view: View, private val isPortrait: Boolean) :
        RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie) {
            view.apply {
                item_tv_title.text = movie.title
                item_tv_desc.text = movie.overview
                item_imv_image.bindImageFromUrl(IMAGE_URL + movie.poster_path)
                setOnClickListener {
                    itemClick?.onItemClick(movie)
                }
            }
        }
    }
    fun setItemClickListener(clickListener: onItemClickListener ) {
        itemClick = clickListener
    }
}
