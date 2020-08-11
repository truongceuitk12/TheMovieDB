package vinova.key.themoviedb.ui.details

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_details.*
import vinova.key.themoviedb.R
import vinova.key.themoviedb.data.model.Movie
import vinova.key.themoviedb.data.model.Youtube
import vinova.key.themoviedb.data.network.MovieManager


class DetailsFragment : Fragment(), IDetailsView {
    private val apiManager: MovieManager by lazy { MovieManager() }
    private val compo by lazy { CompositeDisposable() }
    private var movie: Movie? = null
    var isDropDown = true
    private var trailerList = mutableListOf<Youtube>()
    val detailsPresenter = DetailsPresenter(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }


    @SuppressLint("LogNotTimber")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity!!.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        movie = arguments?.getParcelable<Movie>("movie")
        setDetailsMovie(movie)
        onChangeOverViewState()
        detailsPresenter.getData(movie?.id!!)

    }


    override fun setUpYoutubeView(id: String) {
        val youTubePlayerView =
            activity!!.findViewById<YouTubePlayerView>(R.id.detail_view_movie_player)
        lifecycle.addObserver(youTubePlayerView)
        trailerList?.let {
            youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    //youTubePlayer.loadVideo(trailerList[0].source!!, 0f)
                    youTubePlayer.loadVideo(id, 0f)
                }
            })
        }
    }

    override fun onChangeOverViewState() {
        detail_imv_drop.setOnClickListener {
            isDropDown = !isDropDown
            if (isDropDown) {
                detail_imv_drop.setImageResource(R.drawable.ic_drop_down)
                detail_over_view_desc.visibility = View.VISIBLE
            } else {
                detail_imv_drop.setImageResource(R.drawable.ic_drop_up)
                detail_over_view_desc.visibility = View.GONE
            }
            Toast.makeText(activity, isDropDown.toString(), Toast.LENGTH_LONG).show()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun setDetailsMovie(movie: Movie?) {
        movie?.let {
            detail_tv_movie_title.text = movie.title
            detail_rating_bar.numStars = 10
            detail_rating_bar.rating = movie.vote_average?.toFloat() ?: 0f
            detail_tv_date_release.text =
                getString(R.string.release_date) + " " + movie.release_date
            detail_over_view_desc.text = movie.overview
        }
    }


    override fun showData(youtube: MutableList<Youtube>) {
        trailerList.clear()
        trailerList.addAll(youtube)
        setUpYoutubeView(trailerList[0]?.source!!)
    }

}