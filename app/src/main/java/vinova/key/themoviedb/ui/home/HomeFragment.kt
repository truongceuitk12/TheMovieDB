package vinova.key.themoviedb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.asus.week1.utils.EndlessScrollListener
import kotlinx.android.synthetic.main.fragment_home.*
import vinova.key.themoviedb.R
import vinova.key.themoviedb.utils.LOAD


class HomeFragment : Fragment(), IHomeView {
    var position = 0
    private var mLayoutManager: LinearLayoutManager? = null
    private val page = 1
    val homePresenter = HomePresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        savedInstanceState?.let { position = savedInstanceState.getInt("POSITION") }
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView(LOAD)
        onRefresh()
    }

    override fun onRefresh() {
        home_lo_is_refresh.setOnRefreshListener {
            position = 0
            home_lo_is_refresh.isRefreshing = true
            setUpRecyclerView(LOAD)
            home_lo_is_refresh.isRefreshing = false
        }
    }

    override fun setUpRecyclerView(type: Int) {
        mLayoutManager = LinearLayoutManager(activity!!)
        home_recycler_view.run {
            adapter = homePresenter.getData(activity!!, page, LOAD)
            layoutManager = mLayoutManager
            setHasFixedSize(true)
        }
    }

    override fun onLoadMore() {
        page.plus(1)
        var scrollListener = object : EndlessScrollListener(mLayoutManager!!,1) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
               // homePresenter.getData(page)
            }
        }
        home_recycler_view.apply {
            addOnScrollListener(scrollListener)

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("POSITION", mLayoutManager!!.findFirstCompletelyVisibleItemPosition())
        super.onSaveInstanceState(outState)
    }




}