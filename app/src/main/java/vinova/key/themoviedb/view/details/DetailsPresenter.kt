package vinova.key.themoviedb.view.details

import android.content.Context
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import vinova.key.themoviedb.data.model.api.MovieManager

class DetailsPresenter(val context: Context, val v: IDetailsView) : IDetailsPresenter {
    override fun handleClick() {
        Toast.makeText(context, "abccccc", Toast.LENGTH_LONG).show()
    }

    override fun getData(page: Int) {
        val apiManager: MovieManager by lazy { MovieManager() }
        val compo by lazy { CompositeDisposable() }
        compo.add(
            apiManager.getListMovie(page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    v.showData(it.results!!)
                }, {
                })
        )
    }


}