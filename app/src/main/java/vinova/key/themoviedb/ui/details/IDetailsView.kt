package vinova.key.themoviedb.ui.details

import vinova.key.themoviedb.data.model.Movie
import vinova.key.themoviedb.data.model.Youtube

interface IDetailsView {
    fun setDetailsMovie(movie: Movie?)
    fun setUpYoutubeView(id: String)
    fun onChangeOverViewState()
    fun showData(youtube: MutableList<Youtube>)
}