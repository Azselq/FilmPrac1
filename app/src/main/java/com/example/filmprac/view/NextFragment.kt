package com.example.filmprac.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.filmprac.R
import com.example.filmprac.databinding.FragmentNextBinding
import com.example.filmprac.model.Films
import com.example.filmprac.viewModel.NextViewModel

class NextFragment : Fragment() {
    lateinit var binding: FragmentNextBinding
    private val args: NextFragmentArgs by navArgs()
        //get() = args.film

    companion object {
        fun newInstance() = NextFragment()
    }

    private lateinit var viewModel: NextViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNextBinding.inflate(layoutInflater, container, false)
        //currentItem = arguments?.getSerializable("films") as Films
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvTitle.text = args.film.localized_name.toString()
        binding.tvYear.text = "Год: ${args.film.year}"
        binding.tvRaiting.text = "Рейтинг: ${args.film.rating}"
        binding.tvDescription.text = args.film.description.toString()
        val requestOptions = RequestOptions()
        requestOptions.error(R.drawable.pig)
        Glide.with(binding.imPoster).setDefaultRequestOptions(requestOptions)
            .load(args.film.image_url).error(R.drawable.pig).into(binding.imPoster)
        binding.imButton.setOnClickListener {
            findNavController().navigate(R.id.action_nextFragment_to_baseFragment)
            //activity?.onBackPressed()
        }
    }

}