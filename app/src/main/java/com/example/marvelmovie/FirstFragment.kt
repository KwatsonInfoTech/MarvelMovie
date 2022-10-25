package com.example.marvelmovie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.marvelmovie.data.entities.Movie
import com.example.marvelmovie.databinding.FragmentFirstBinding
import com.example.marvelmovie.movieViewModle.MovieVM
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FirstFragment : Fragment() {
    private val viewModel: MovieVM by viewModels()
    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)


        }

        viewModel.fetchMovies()
        viewModel.movieLiveData.observe(viewLifecycleOwner){
            Log.i("Data", ""+ (it.title))
            bindDetails(it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun bindDetails(movie: Movie){
        binding.DescriptiontextView.text = movie.overview
        binding.TitletextView.text = movie.title
        binding.ReleaseDatetextView.text = movie.release_date
        binding.daysTillReleasetextView.text = movie.days_until.toString()

        Glide.with(binding.root)
            .load(movie.poster_url)
            .transform()
            .into(binding.imageView)



    }
}