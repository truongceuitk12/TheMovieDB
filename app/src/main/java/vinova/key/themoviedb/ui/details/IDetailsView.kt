package vinova.key.themoviedb.ui.details

import vinova.key.themoviedb.data.model.Movie

interface IDetailsView {
    fun setPresenter(presenter: IDetailsPresenter)
    fun showData(movie: MutableList<Movie>)
}