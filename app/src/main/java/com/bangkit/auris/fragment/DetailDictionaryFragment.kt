package com.bangkit.auris.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.bangkit.auris.adapter.DataAdapter
import com.bangkit.auris.adapter.DictionaryAdapter
import com.bangkit.auris.databinding.FragmentDetailDictionaryBinding
import com.bangkit.auris.utils.DataDictionaries

class DetailDictionaryFragment : Fragment() {
    private lateinit var fragmentDetailDictionary: FragmentDetailDictionaryBinding
    private var position: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentDetailDictionary = FragmentDetailDictionaryBinding.inflate(layoutInflater, container, false)

        val dictionaryAdapter = this.activity?.let {
            DictionaryAdapter(
                this.requireContext(),
                it.supportFragmentManager
            )
        }

        return fragmentDetailDictionary.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val library = DataDictionaries.generateDictAlphabet()
            val dataAdapter = DataAdapter()

            // Change R.string <Int> to <String>


//            dataAdapter.setDataLibrary(library)

            with(fragmentDetailDictionary.rvItemdict) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = dataAdapter
            }
        }
    }

}