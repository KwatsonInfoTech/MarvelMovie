package com.example.marvelmovie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.marvelmovie.data.entities.FollowingProduction
import com.example.marvelmovie.data.entities.Movie
import com.example.marvelmovie.databinding.FragmentSecondBinding
import com.example.marvelmovie.movieViewModle.MovieVM
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class SecondFragment : Fragment() {
    private val viewModel: MovieVM by viewModels()
    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchMovies()
        viewModel.movieLiveData.observe(viewLifecycleOwner){
            Log.i("Data", ""+ (it.title))
            bindDetails(it.following_production)
        }


        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)




        }

    }

    fun bindDetails(movie: FollowingProduction){
        binding.DescriptiontextView.text = movie.overview
        binding.TitletextView.text = movie.title
        binding.ReleaseDatetextView.text = movie.release_date
        binding.daysTillReleasetextView.text = movie.days_until.toString()

        Glide.with(binding.root)
            .load(movie.poster_url)
            .transform()
            .into(binding.imageView)



    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}