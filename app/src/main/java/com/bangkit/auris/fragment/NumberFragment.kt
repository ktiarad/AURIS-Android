package com.bangkit.auris.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.bangkit.auris.adapter.DataAdapter
import com.bangkit.auris.databinding.FragmentNumberBinding
import com.bangkit.auris.utils.DataDictionaries

class NumberFragment : Fragment() {

    private lateinit var fragmentNumberBinding: FragmentNumberBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentNumberBinding = FragmentNumberBinding.inflate(layoutInflater, container, false)
        return fragmentNumberBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            val library = DataDictionaries.generateDictNumber()
            val dataAdapter = DataAdapter()
//            dataAdapter.setDataLibrary(library)

            with(fragmentNumberBinding.rvNumber){
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = dataAdapter
            }
        }
    }
}