package vinova.key.themoviedb.ui.base

import vinova.key.themoviedb.data.model.Movie

interface onItemClickListener{
    fun onItemClick(item: Movie)
}