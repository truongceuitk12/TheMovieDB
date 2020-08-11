package vinova.key.themoviedb.ui.details

import android.content.Context
import android.util.Log
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import vinova.key.themoviedb.data.network.MovieManager

class DetailsPresenter(val context: Context, val v: IDetailsView) : IDetailsPresenter {
    override fun handleClick() {
        Toast.makeText(context, "abccccc", Toast.LENGTH_LONG).show()
    }

    override fun getData(id: Int) {
        val apiManager: MovieManager by lazy { MovieManager() }
        val compo by lazy { CompositeDisposable() }
        compo.add(
            apiManager.getTrailerInfor(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.youtube != null) {
                        Log.d("Results", "${it.youtube}")
                       // v.showData(it.youtube)
                    }
                }, {
                    Log.d("Throw", "${it}")
                })
        )
    }


}