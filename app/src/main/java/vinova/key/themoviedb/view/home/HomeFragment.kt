package vinova.key.themoviedb.view.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.HORIZONTAL
import android.widget.LinearLayout.VERTICAL
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import vinova.key.themoviedb.databinding.FragmentHomeBinding
import vinova.key.themoviedb.model.data.Movie
import vinova.key.themoviedb.presenter.base.IHome
import vinova.key.themoviedb.view.adapter.MovieAdapter


class HomeFragment : Fragment(), IHome.HomeView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater,container,false)
        binding.listener = this@HomeFragment
        return binding.root
    }


    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listFake = mutableListOf<Movie>()
        listFake.apply {
            add(Movie(2,4.9,"Anroid 123","Hay vclllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll"))
            add(Movie(2,4.9,"Anroid 123","Hay vclllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll"))
            add(Movie(2,9.0,"Anroid 123","Hay vclllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll"))
            add(Movie(2,9.1,"Anroid 123","Hay vclllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll"))
            add(Movie(2,9.2,"Anroid 123","Hay vclllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll"))
            add(Movie(2,4.9,"Anroid 123","Hay vclllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll"))
        }
        home_recycler_view.run {
            Log.d("getList", "${listFake.toString()}")
            adapter = MovieAdapter(activity!!,listFake)
            layoutManager = LinearLayoutManager(activity!!, VERTICAL, false)
        }

    }


}