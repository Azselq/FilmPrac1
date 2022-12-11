package com.example.filmprac.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.filmprac.R
import com.example.filmprac.databinding.FragmentNextBinding
import com.example.filmprac.model.Films
import com.example.filmprac.viewModel.NextViewModel

class NextFragment : Fragment() {
    lateinit var binding: FragmentNextBinding
    lateinit var currentItem: Films
    companion object {
        fun newInstance() = NextFragment()
    }

    private lateinit var viewModel: NextViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNextBinding.inflate(layoutInflater,container,false)
        currentItem = arguments?.getSerializable("films") as Films
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvTitle.text = currentItem.localized_name.toString()
        binding.tvYear.text = "Год: ${currentItem.year.toString()}"
        binding.tvRaiting.text = "Рейтинг: ${currentItem.rating.toString()}"
        binding.tvDescription.text = currentItem.description.toString()
        val requestOptions = RequestOptions()
        requestOptions.error(R.drawable.pig)
        Glide.with(binding.imPoster).setDefaultRequestOptions(requestOptions)
            .load(currentItem.image_url).into(binding.imPoster)
        binding.imButton.setOnClickListener{
            findNavController().navigate(R.id.action_nextFragment_to_baseFragment)
        }
    }

}