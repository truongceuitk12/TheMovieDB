package vinova.key.themoviedb.ui.details

import vinova.key.themoviedb.data.model.Movie

interface IDetailsView {
    fun setDetailsMovie(movie : Movie?)
    fun setUpYoutubeView(id : String?)

    //fun showData(movie: MutableList<Movie>)
}