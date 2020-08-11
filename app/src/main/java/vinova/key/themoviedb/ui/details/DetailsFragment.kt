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
import kotlinx.android.synthetic.main.fragment_details.*
import vinova.key.themoviedb.R
import vinova.key.themoviedb.data.model.Movie


class DetailsFragment : Fragment() {
    private var movie: Movie? = null
    var isDropDown = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity!!.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        movie = arguments?.getParcelable<Movie>("movie")
        setDetailsMovie(movie)
        onChangeOverViewState()
        //val youTubePlayerView = activity!!.findViewById<YouTubePlayer>()
        //YouTubePlayer.loadVideo(String videoId, float startTime)
    }

    private fun onChangeOverViewState() {
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
    fun setDetailsMovie(movie: Movie?) {
        movie?.let {
            detail_tv_movie_title.text = movie.title
            detail_rating_bar.numStars = 10
            detail_rating_bar.rating = movie.vote_average?.toFloat() ?: 0.toFloat()
            detail_tv_date_release.text =
                getString(R.string.release_date) + " " + movie.release_date
            detail_over_view_desc.text = movie.overview
        }
    }

}