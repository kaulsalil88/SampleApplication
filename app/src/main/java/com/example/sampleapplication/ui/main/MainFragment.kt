package com.example.sampleapplication.ui.main

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sampleapplication.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

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

        viewModel.getProfile()
        viewModel.profiles.observe(viewLifecycleOwner, {
            Log.d("MainFragment -------->", it.toString())
            Log.d("MainFragment", it.size.toString())
        })

        viewModel.eventNetworkError.observe(viewLifecycleOwner, {
            if (it) {
                Toast.makeText(context, "Error in API", Toast.LENGTH_LONG).show()
            }

        })

    }


}




