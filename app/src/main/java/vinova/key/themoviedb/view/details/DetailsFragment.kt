package vinova.key.themoviedb.view.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_details.*
import vinova.key.themoviedb.R


@Suppress("UNREACHABLE_CODE")
class DetailsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val presenter  = DetailsPresenter(activity!!)
        button.setOnClickListener {
            presenter.handleClick()
            findNavController().navigate(R.id.action_detailsFragment_to_homeFragment)
        }


    }


}