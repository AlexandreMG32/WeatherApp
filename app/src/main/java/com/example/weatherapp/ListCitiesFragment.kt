package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.adapter.CityAdapter
import com.example.weatherapp.databinding.FragmentListCitiesBinding
import com.example.weatherapp.repository.Repository

/**
 * A simple [Fragment] subclass.
 * Use the [ListCitiesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListCitiesFragment : Fragment() {

    private var _binding : FragmentListCitiesBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel : CityViewModel by activityViewModels()
    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListCitiesBinding.inflate(inflater, container, false);
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val repository = Repository();
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getAllCities(resources.getStringArray(R.array.cities))
        viewModel.myResponse.observe(viewLifecycleOwner, Observer {
            for(card in it) {
                Log.d("Response", card.body()?.name.toString())
            }
            recyclerView = getView()?.findViewById(R.id.recycler_view) as RecyclerView;
            recyclerView.adapter = CityAdapter(sharedViewModel,requireContext(), viewModel.myResponse.value)
        })
    }
}