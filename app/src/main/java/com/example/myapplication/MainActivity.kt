package com.example.myapplication

import android.os.Bundle
import android.util.TypedValue
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs


class MainActivity : AppCompatActivity() {
    var bottomPaddingDefault = 0;
    private lateinit var binding: ActivityMainBinding
    private var languageList = ArrayList<Language>()
    private lateinit var rvAdapter: RvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        // define layout manager for the Recycler view
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // attach adapter to the recycler view and also handle item click

        // attach adapter to the recycler view
        rvAdapter = RvAdapter(languageList)

        // add adapter to the recycler view
        binding.recyclerView.adapter = rvAdapter

        // create objects of Language
        // create some row data
        val language1 = Language("Java", "3 Year exp")
        val language2 = Language("Kotlin", "2 Year exp")
        val language3 = Language("Python", "1 Year exp")
        val language4 = Language("CPP", "5 Year exp")
        val language5 = Language("PHP", "No exp")

        // pass raw data t rhe list
        languageList.add(language1)
        languageList.add(language2)
        languageList.add(language3)
        languageList.add(language4)
        languageList.add(language5)
        languageList.add(language3)
        languageList.add(language4)
        languageList.add(language5)
        languageList.add(language3)
        languageList.add(language4)
        languageList.add(language5)

        rvAdapter.notifyDataSetChanged()

        // Implement
        // convert 53dp -> px
        val dip = 53f
        val px = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dip, resources.displayMetrics
        ).toInt()
        // count padding bottom default when collapsing tool bar is not scroll
        binding.lnGroupView.viewTreeObserver.addOnGlobalLayoutListener(object :
            OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.lnGroupView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                bottomPaddingDefault =
                    binding.lnGroupView.height + binding.appBarLayout.totalScrollRange + px
                binding.recyclerView.setPadding(0, 0, 0, bottomPaddingDefault)
            }
        })
        // Update padding bottom default when collapsing is not scrolling
        val onOffsetChangedListener = AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            binding.recyclerView.setPadding(0, 0, 0, abs(bottomPaddingDefault + verticalOffset))
        }
        binding.appBarLayout.addOnOffsetChangedListener(onOffsetChangedListener)
    }
}