package com.bangkit.auris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.bangkit.auris.adapter.DataAdapter
import com.bangkit.auris.adapter.DataGifAdapter
import com.bangkit.auris.databinding.ActivityDetailDictionaryBinding
import com.bangkit.auris.utils.DataDictionaries
import com.bangkit.auris.utils.DataDictionary

class DetailDictionaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailDictionaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_dictionary)

        this.binding = ActivityDetailDictionaryBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        val dictionary = intent.getStringExtra("dictionary")

//        val library = DataDictionaries.generateDictAlphabet()
        val library = dictionary?.let { getDictionary(it) }
//        val dataAdapter = DataAdapter()
        val viewType = dictionary?.let { getViewType(it) }

        // Change library which has R.string <Int> to <String>
        val libraryStr: MutableList<DataDictionary> = mutableListOf()
        library?.forEach {
            libraryStr.add(
                DataDictionary(
                    getString(it.title.toInt()), it.image
                )
            )
        }

        libraryStr.forEach {
            Log.d("menu lib", it.title)
        }

//        viewType?.let { dataAdapter.setDataLibrary(libraryStr) }



        if (viewType.equals("img")) {
            val dataAdapter = DataAdapter()
            dataAdapter.setDataLibrary(libraryStr)
            with(binding.rvItemdict) {
                Log.d("menu", "binding here for grid 2")
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = dataAdapter
            }
        }
        else if (viewType.equals("gif")) {
            val dataAdapter = DataGifAdapter()
            viewType?.let { dataAdapter.setDataLibrary(libraryStr) }
            Log.d("menu gif", "this is gif menu")
            with(binding.rvItemdict) {
                Log.d("menu", "binding here for grid 2")
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = dataAdapter
            }
        }
        this.setTitle(dictionary)
//        Toast.makeText(this, "Menu $dictionary", Toast.LENGTH_SHORT).show()

    }

    fun getDictionary(dictionary: String): List<DataDictionary> {
        when (dictionary) {
            getString(R.string.alphabet) -> return DataDictionaries.generateDictAlphabet()
            getString(R.string.numbers) -> return DataDictionaries.generateDictNumber()
            getString(R.string.pronoun) -> return DataDictionaries.generateDictPronoun()
            getString(R.string.interrogative) -> return DataDictionaries.generateDictInterrogativeWord()
            getString(R.string.family_title) -> return DataDictionaries.generateDictFamily()
            getString(R.string.expression) -> return DataDictionaries.generateDictExpression()
            getString(R.string.day) -> return DataDictionaries.generateDictDay()
            getString(R.string.month) -> return DataDictionaries.generateDictMonth()
            getString(R.string.verb) -> return DataDictionaries.generateDictVerb()
            getString(R.string.noun) -> return DataDictionaries.generateDictNoun()

            else -> return DataDictionaries.generateDictAlphabet()
        }
    }

    fun getViewType(dictionary: String): String {
        when (dictionary) {
            getString(R.string.alphabet) -> return "img"
            getString(R.string.numbers) -> return "img"
            getString(R.string.pronoun) -> return "gif"
            getString(R.string.interrogative) -> return "gif"
            getString(R.string.family_title) -> return "gif"
            getString(R.string.expression) -> return "gif"
            getString(R.string.day) -> return "gif"
            getString(R.string.month) -> return "gif"
            getString(R.string.verb) -> return "gif"
            getString(R.string.noun) -> return "gif"

            else -> return "img"
        }
    }

}