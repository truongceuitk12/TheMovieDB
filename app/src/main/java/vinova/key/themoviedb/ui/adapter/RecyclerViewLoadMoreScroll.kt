package vinova.key.themoviedb.ui.adapter

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager


class RecyclerViewLoadMoreScroll : RecyclerView.OnScrollListener{

    private var visibleThreshold = 20
    private lateinit var mOnLoadMoreListener: IOnLoadMore
    private var isLoading: Boolean = false
    private var lastVisibleItem: Int = 0
    private var totalItemCount:Int = 0
    private var mLayoutManager: RecyclerView.LayoutManager

    fun setLoaded() {
        isLoading = false
    }

    fun getLoaded(): Boolean {
        return isLoading
    }

    fun setOnLoadMoreListener(mOnLoadMoreListener: IOnLoadMore) {
        this.mOnLoadMoreListener = mOnLoadMoreListener
    }

    constructor(layoutManager:LinearLayoutManager) {
        this.mLayoutManager = layoutManager
    }

    constructor(layoutManager:GridLayoutManager) {
        this.mLayoutManager = layoutManager
        visibleThreshold *= layoutManager.spanCount
    }

    constructor(layoutManager:StaggeredGridLayoutManager) {
        this.mLayoutManager = layoutManager
        visibleThreshold *= layoutManager.spanCount
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy <= 0) return

        totalItemCount = mLayoutManager.itemCount

        when (mLayoutManager) {
            is StaggeredGridLayoutManager -> {
                val lastVisibleItemPositions =
                    (mLayoutManager as StaggeredGridLayoutManager).findLastVisibleItemPositions(null)
                // get maximum element within the list
                lastVisibleItem = getLastVisibleItem(lastVisibleItemPositions)
            }
            is GridLayoutManager -> lastVisibleItem = (mLayoutManager as GridLayoutManager).findLastVisibleItemPosition()
            is LinearLayoutManager -> lastVisibleItem = (mLayoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
        }

        if (lastVisibleItem == totalItemCount - 1) {
            mOnLoadMoreListener.onLoadMoreListener()
            isLoading = true
        }
    }

    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }
}