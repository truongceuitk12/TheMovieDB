package vinova.key.themoviedb.ui.details

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import vinova.key.themoviedb.data.network.MovieManager

class DetailsPresenter(val v: IDetailsView) : IDetailsPresenter {
    val apiManager: MovieManager by lazy { MovieManager() }
    val compo by lazy { CompositeDisposable() }

    override fun getData(id: Int) {
        compo.add(
            apiManager.getTrailerInfor(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it?.let { v.showData(it.youtube!!) }
                }, {
                    Log.d("Throw", "${it}")
                })
        )
    }


}