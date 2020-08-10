package vinova.key.themoviedb.view.details

import vinova.key.themoviedb.data.model.data.Movie

interface IDetailsView {
    fun setPresenter(presenter: IDetailsPresenter)
    fun showData(movie: MutableList<Movie>)
}