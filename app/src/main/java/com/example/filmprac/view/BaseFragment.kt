package com.example.filmprac.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.filmprac.adapter.FilmAdapter
import com.example.filmprac.databinding.FragmentBaseBinding
import com.example.filmprac.viewModel.BaseViewModel
import com.example.filmprac.viewModel.OpenDetailFilm
import kotlinx.coroutines.launch

class BaseFragment : Fragment() {
    lateinit var binding: FragmentBaseBinding

    private lateinit var viewModel: BaseViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BaseViewModel::class.java)
        viewModel.printInformation()
        val recyclerView = binding.rcView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.filmListLiveData.observe(viewLifecycleOwner) {
            recyclerView.adapter = FilmAdapter(it)
        }

        lifecycleScope.launch {
            viewModel.openDetailFilm.collect {
                when (it) {
                    is OpenDetailFilm.OpenNewFragment -> {
                        findNavController().navigate(BaseFragmentDirections.actionBaseFragmentToNextFragment2(
                            it.films
                        ))
                    }
                }
            }
        }

    }
}

