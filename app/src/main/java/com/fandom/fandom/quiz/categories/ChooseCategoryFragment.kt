package com.fandom.fandom.quiz.categories

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.fandom.fandom.quiz.R
import com.fandom.fandom.quiz.databinding.FragmentChooseCategoryBinding
import com.fandom.fandom.quiz.utils.viewBinding

class ChooseCategoryFragment : Fragment(R.layout.fragment_choose_category) {

    private val binding by viewBinding(FragmentChooseCategoryBinding::bind)

    private lateinit var glide: RequestManager
    private lateinit var adapter: CategoryAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        glide = Glide.with(this)
        adapter = CategoryAdapter(glide) {
            Toast.makeText(requireContext(), it.name, Toast.LENGTH_SHORT).show()
        }
        binding.chooseCategoryRecyclerView.adapter = adapter
        adapter.submitList(categoryList)
    }
}