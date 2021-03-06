package vinova.key.themoviedb.ui.home

import android.content.Context
import vinova.key.themoviedb.ui.base.adapter.MovieAdapter


interface IHomePresenter {
    fun getData(context: Context, page : Int, type : Int): MovieAdapter
}