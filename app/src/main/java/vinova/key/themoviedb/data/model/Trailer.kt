package vinova.key.themoviedb.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Trailer(
    val id: Int?,

    val quicktime: List<Any>?,

    val youtube: ArrayList<Youtube>?
)

@Parcelize
data class Youtube(
    val name: String?,

    val size: String?,

    val source: String?,

    val type: String?
) : Parcelable