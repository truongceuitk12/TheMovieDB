package vinova.key.themoviedb.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class MovieList(
    val dates: Dates?,
    val page: Int?,
    val results: MutableList<Movie>?,
    val total_pages: Int?,
    val total_results: Int?
)

data class Dates(
    val maximum: String?,
    val minimum: String?
)

@Parcelize
data class Movie(
    val id: Int?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val backdrop_path : String?,
    val vote_average: Double?,
    val vote_count: Int?
) : Parcelable