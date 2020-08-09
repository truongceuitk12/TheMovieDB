package vinova.key.themoviedb.view.home

import vinova.key.themoviedb.data.model.data.Movie

interface IHomeView{
   // fun setPresenter(presenter: IHomePresenter)
    fun setData(movie : MutableList<Movie>)
}