package vinova.key.themoviedb.view.details

import android.content.Context
import android.widget.Toast

class DetailsPresenter(private val context : Context) : IDetailsPresenter {
    override fun handleClick(){
            Toast.makeText(context, "abccccc" ,Toast.LENGTH_LONG).show()

    }
}