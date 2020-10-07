package com.example.sampleapplication.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.sampleapplication.R
import com.example.sampleapplication.database.getProfileDatabase
import com.example.sampleapplication.ui.main.dummy.DummyContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

/**
 * A fragment representing a list of Items.
 */
class FavoritesFragment : Fragment() {

    private var columnCount = 1

    val favViewModel: FavoritesViewModel by lazy {
        requireNotNull(this.activity) {
            "Activity has to be created"
        }
        ViewModelProvider(this).get(FavoritesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favViewModel.getFavoritesFromDb()
        favViewModel.profiles.observe(viewLifecycleOwner, {
            if (it.size == 0) {
                Toast.makeText(
                    view.context,
                    "No Profile has been added to favorites",
                    Toast.LENGTH_LONG
                ).show()
                parentFragmentManager.popBackStack()
            }
            if (view is RecyclerView) {
                with(view) {
                    layoutManager = when {
                        columnCount <= 1 -> LinearLayoutManager(context)
                        else -> GridLayoutManager(context, columnCount)
                    }
                    adapter = MyItemRecyclerViewAdapter(it)
                }
            }
        })
    }

    companion object {


        const val ARG_COLUMN_COUNT = "column-count"


        @JvmStatic
        fun newInstance(columnCount: Int) =
            FavoritesFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}