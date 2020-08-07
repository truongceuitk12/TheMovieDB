package vinova.key.themoviedb.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import vinova.key.themoviedb.R


// region Common

@BindingAdapter("android:imageUrl")
fun ImageView.bindImageFromUrl(imageUrl: String?) {
    imageUrl?.let {
        Glide.with(this.context)
            .load(imageUrl)
            .transform(RoundedCorners(8))
            .transition(DrawableTransitionOptions.withCrossFade())
            .fitCenter()
            .placeholder(R.drawable.progress_animation)
            .into(this)
    }
}

@BindingAdapter("android:src")
fun ImageView.bindImageResource(resource: Int) {
    this.setImageResource(resource)
}
