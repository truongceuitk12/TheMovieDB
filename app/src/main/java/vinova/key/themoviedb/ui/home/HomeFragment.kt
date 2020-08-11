package vinova.key.themoviedb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.asus.week1.utils.EndlessScrollListener
import kotlinx.android.synthetic.main.fragment_home.*
import vinova.key.themoviedb.R
import vinova.key.themoviedb.data.model.Movie
import vinova.key.themoviedb.ui.base.adapter.MovieAdapter
import vinova.key.themoviedb.ui.base.onItemClickListener
import vinova.key.themoviedb.utils.LOAD
import vinova.key.themoviedb.utils.LOAD_MORE
import vinova.key.themoviedb.utils.PAGE_DEFAULT


class HomeFragment : Fragment(), IHomeView {
    var position = 0
    var mLayoutManager: LinearLayoutManager? = null
    private lateinit var movieAdapter: MovieAdapter
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
        setUpRecyclerView(PAGE_DEFAULT, LOAD)
        onLoadMore()
        onRefresh()

    }

    override fun onRefresh() {
        home_lo_is_refresh.setOnRefreshListener {
            position = 0
            home_lo_is_refresh.isRefreshing = true
            setUpRecyclerView(PAGE_DEFAULT, LOAD)
            home_lo_is_refresh.isRefreshing = false
        }
    }

    override fun setUpRecyclerView(page: Int, type: Int) {
        mLayoutManager = LinearLayoutManager(activity!!)
        movieAdapter = homePresenter.getData(activity!!, page, type)
        home_recycler_view.run {
            adapter = movieAdapter
            layoutManager = mLayoutManager
            setHasFixedSize(true)
        }

        movieAdapter.setItemClickListener(object : onItemClickListener {
            override fun onItemClick(item: Movie) {
                val bundle = Bundle()
                bundle.putParcelable("movie", item)
                findNavController().navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
            }
        })

    }


    override fun onLoadMore() {
        mLayoutManager = LinearLayoutManager(activity!!)
        home_recycler_view.addOnScrollListener(object : EndlessScrollListener(mLayoutManager!!, 1) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                setUpRecyclerView(page, LOAD_MORE)
            }
        })
    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("POSITION", mLayoutManager!!.findFirstCompletelyVisibleItemPosition())
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}