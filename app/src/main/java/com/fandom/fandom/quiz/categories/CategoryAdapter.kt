package com.fandom.fandom.quiz.categories

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.fandom.fandom.quiz.databinding.ItemCategoryBinding
import io.ktor.utils.io.printStack

class CategoryAdapter(private val requestManager: RequestManager, private val onClick: (Category) -> Unit) :
    ListAdapter<Category, CategoryViewHolder>(object : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean = oldItem == newItem
    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false), requestManager, onClick)

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) = holder.bind(getItem(position))
}

class CategoryViewHolder(private val binding: ItemCategoryBinding, private val glide: RequestManager, onClick: (Category) -> Unit) : RecyclerView.ViewHolder(binding.root) {

    private var category: Category? = null

    init {
        binding.root.setOnClickListener {
            category?.let(onClick)
        }
    }

    fun bind(category: Category) {
        this.category = category
        glide.load(category.image)
            .listener(object:RequestListener<Drawable?>{
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable?>, isFirstResource: Boolean): Boolean {
                    e?.printStack()
                    return false
                }

                override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable?>?, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                    return false
                }
            })
            .centerCrop().into(binding.categoryImage)
        binding.categoryName.text = category.name
    }
}
