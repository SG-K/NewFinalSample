package com.m.hostapp22

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.m.samplelibb.FragmentSearch
import com.m.samplelibb.search.fragments.FragmentCategorySearch
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FragmentCategorySearch.newInstance(supportFragmentManager, R.id.frame_container)


        tv_hw?.setOnClickListener {
//            SearchActivity.startActivity(WeakReference(this))
        }

    }
}
