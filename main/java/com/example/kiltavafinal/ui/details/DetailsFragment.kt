package com.example.kiltavafinal.ui.details

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.kiltavafinal.databinding.FragmentDetailsBinding

class DetailsFragment() : Fragment(), Parcelable {

    private val movie: Any
        get() = TODO()
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: DetailsFragment by navArgs()

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val movie = args.movie

        binding.titleTextView.text = movie.title
        binding.overviewTextView.text = movie.overview
        binding.ratingTextView.text = movie.vote_average.toString()

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
            .into(binding.posterImageView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DetailsFragment> {
        override fun createFromParcel(parcel: Parcel): DetailsFragment {
            return DetailsFragment(parcel)
        }

        override fun newArray(size: Int): Array<DetailsFragment?> {
            return arrayOfNulls(size)
        }
    }
}

private operator fun <Args> NavArgsLazy<Args>.getValue(detailsFragment: DetailsFragment, property: KProperty<Args?>): DetailsFragment {

}
