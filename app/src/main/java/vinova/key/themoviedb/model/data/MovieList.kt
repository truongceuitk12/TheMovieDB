package vinova.key.themoviedb.model.data


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class MovieList(
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val movies: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)