package vinova.key.themoviedb.view.home

import android.widget.Toast
import vinova.key.themoviedb.data.model.data.Movie

interface IHomePresenter {
    fun getData() : MutableList<Movie>
}