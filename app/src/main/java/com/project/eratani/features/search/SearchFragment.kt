package com.project.eratani.features.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.eratani.R
import com.project.eratani.core.data.source.local.DummyData
import com.project.eratani.core.ui.adapter.FruitDataAdapter
import com.project.eratani.core.utils.SearchUtils
import com.project.eratani.core.utils.showToast
import com.project.eratani.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val dummyData = DummyData.fruitData
    private lateinit var fruitAdapter: FruitDataAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        
        handleSearch()
    }

    private fun initRecyclerView() {
        fruitAdapter = FruitDataAdapter(dummyData)
        binding.rvFruit.adapter = fruitAdapter
        binding.rvFruit.layoutManager = LinearLayoutManager(context)
    }

    private fun handleSearch() {
        binding.edSearch.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                processSearchData(p0.toString())
            }
        })
    }

    private fun processSearchData(query: String) {
        val foundData = SearchUtils.searchKeywords(query, dummyData)

        if (foundData.isNotEmpty()) {
            fruitAdapter.updateData(foundData)
        } else {
            fruitAdapter.updateData(dummyData)
            activity?.showToast("No matches found")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}