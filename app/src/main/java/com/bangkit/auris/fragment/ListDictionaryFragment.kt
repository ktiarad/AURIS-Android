package com.bangkit.auris

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.bangkit.auris.adapter.DataAdapter
import com.bangkit.auris.adapter.DictionaryAdapter
import com.bangkit.auris.adapter.SectionsPagerAdapter
import com.bangkit.auris.databinding.FragmentListDictionaryBinding
import com.bangkit.auris.fragment.DetailDictionaryFragment
import com.bangkit.auris.utils.DataDictionaries

class ListDictionaryFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentListDictionaryBinding

    companion object {
        fun newInstance(): ListDictionaryFragment {
            return ListDictionaryFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Instantiate ViewBinding
        this.binding = FragmentListDictionaryBinding.inflate(inflater, container, false)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val menu = DataDictionaries.generateListMenu()
            val menuStr: MutableList<String> = mutableListOf()

            menu.forEach { it ->
                menuStr.add(getString(it))
                Log.d("menu", getString(it))
            }
            this.binding.lvDictionary.adapter = ArrayAdapter(this.requireActivity(), android.R.layout.simple_list_item_1, menuStr)
            this.binding.lvDictionary.setOnItemClickListener { parent, view, position, id ->
//                Toast.makeText(this.requireActivity(), "Anda memilih ${menu[position]}", Toast.LENGTH_SHORT).show()

                val intent = Intent(activity, DetailDictionaryActivity::class.java)
                intent.putExtra("dictionary", menuStr[position])
                startActivity(intent)
                
            }
        }
    }

}