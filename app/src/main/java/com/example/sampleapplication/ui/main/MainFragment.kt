package com.example.sampleapplication.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sampleapplication.R
import com.example.sampleapplication.network.ProfileCard
import com.mindorks.placeholderview.SwipeDecor
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.SwipeViewBuilder
import com.mindorks.placeholderview.listeners.ItemRemovedListener

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var mContext: Context
    private val viewModel: MainViewModel by lazy {
        requireNotNull(this.activity) {
            "Activity Has to be created"
        }
        ViewModelProvider(this).get(MainViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("MainFragment", "onCreateView");
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val swipeView = view.findViewById<SwipePlaceHolderView>(R.id.swipe_place_holder)
        mContext = context?.applicationContext!!
        swipeView.getBuilder<SwipePlaceHolderView, SwipeViewBuilder<SwipePlaceHolderView>>()
            .setDisplayViewCount(3)
            .setSwipeDecor(SwipeDecor().setPaddingTop(20).setRelativeScale(0.01f))
        viewModel.getProfile()
        viewModel.profiles.observe(viewLifecycleOwner, { list ->
            for (profile in list) {
                swipeView.addView(ProfileCard(mContext, profile, swipeView, viewModel))
            }

        })

        viewModel.eventNetworkError.observe(viewLifecycleOwner, {
            if (it) {
                Toast.makeText(context, "Error in API", Toast.LENGTH_LONG).show()
            }

        })

        view.findViewById<Button>(R.id.bt_show_fav).setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, FavoritesFragment.newInstance(1))
                .addToBackStack("Favorites")
                .commit()
        }
    }


}




