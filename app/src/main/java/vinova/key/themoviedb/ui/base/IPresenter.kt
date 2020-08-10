package vinova.key.themoviedb.ui.base

import android.view.View

interface IPresenter{
    fun attachView(view: View)
    fun detachView()
}