package vinova.key.themoviedb.ui.details

import android.content.Context
import android.util.Log
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import vinova.key.themoviedb.data.api.MovieManager

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
                    if (it.results != null) {
                        Log.d("Results", "${it.results}")
                        v.showData(it.results)
                    }
                }, {
                    Log.d("Throw", "${it}")
                })
        )
    }


}