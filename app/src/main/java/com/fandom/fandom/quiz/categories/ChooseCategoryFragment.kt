package com.fandom.fandom.quiz.categories

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.fandom.fandom.quiz.R
import com.fandom.fandom.quiz.databinding.FragmentChooseCategoryBinding
import com.fandom.fandom.quiz.utils.MoveInsetsHandler
import com.fandom.fandom.quiz.utils.viewBinding
import org.koin.android.ext.android.inject

class ChooseCategoryFragment : Fragment(R.layout.fragment_choose_category) {

    private val moveInsetsHandler: MoveInsetsHandler by inject()

    private val binding by viewBinding(FragmentChooseCategoryBinding::bind)

    private lateinit var glide: RequestManager
    private lateinit var adapter: CategoryAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        glide = Glide.with(this)
        view.findViewById<View>(R.id.backButton).setOnClickListener {
            findNavController().popBackStack()
        }
        adapter = CategoryAdapter(glide) {
            findNavController().navigate(R.id.action_chooseCategoryFragmentNav_to_chooseOponentFragmentNav, Bundle().apply {
                putString("categoryId", it.id)
            })
        }
        binding.chooseCategoryRecyclerView.adapter = adapter
        adapter.submitList(categoryList)
    }

    override fun onResume() {
        moveInsetsHandler.handleInsetsToMoveUnderStatusBar(this)
        super.onResume()
    }

    override fun onPause() {
        moveInsetsHandler.handleInsetsToMoveToNormalPosition(this)
        super.onPause()
    }
}