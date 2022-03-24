package com.bangkit.auris.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.bangkit.auris.R
import com.bangkit.auris.fragment.AlphabetFragment
import com.bangkit.auris.fragment.DetailDictionaryFragment
import com.bangkit.auris.fragment.NumberFragment

class DictionaryAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.alphabet, R.string.numbers)
    }

    override fun getItem(position: Int): Fragment = DetailDictionaryFragment()

    override fun getPageTitle(position: Int): CharSequence? = mContext.resources.getString(
        TAB_TITLES[position])

    override fun getCount(): Int = 28
}