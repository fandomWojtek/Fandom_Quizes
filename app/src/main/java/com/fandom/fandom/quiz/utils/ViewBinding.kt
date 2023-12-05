package com.fandom.fandom.quiz.utils

import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentViewBindingDelegate<T : ViewBinding>(
    val fragment: Fragment,
    val viewBindingFactory: (View) -> T,
    val justBeforeClear: (T) -> Unit
) : ReadOnlyProperty<Fragment, T> {
    private var binding: T? = null

    init {
        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleOwner ->
                    viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                        override fun onDestroy(owner: LifecycleOwner) {
                            whenNotNull(binding) {
                                justBeforeClear(it)
                            }
                            binding = null
                        }
                    })
                }
            }
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val binding = binding
        if (binding != null) {
            return binding
        }

        val lifecycle = fragment.viewLifecycleOwner.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            throw IllegalStateException("Should not attempt to get bindings when Fragment views are destroyed.")
        }

        return viewBindingFactory(thisRef.requireView()).also { this.binding = it }
    }
}

fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T, justBeforeClear: (T) -> Unit = {}) =
    FragmentViewBindingDelegate(this, viewBindingFactory, justBeforeClear)


class ActivityViewBindingDelegate<T : ViewBinding>(
    private val activity: AppCompatActivity,
    private val initializer: (LayoutInflater) -> T,
    private val justBeforeClear: (T) -> Unit
) : ReadOnlyProperty<AppCompatActivity, T>, LifecycleObserver {

    private var binding: T? = null

    init {
        activity.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                if (binding == null) {
                    val initBinding = initializer(activity.layoutInflater)
                    binding = initBinding
                    activity.setContentView(initBinding.root)
                    activity.lifecycle.removeObserver(this)
                }
            }

            override fun onDestroy(owner: LifecycleOwner) {
                whenNotNull(binding) {
                    justBeforeClear(it)
                }
                binding = null
            }
        })

    }

    override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
        if (binding == null) {
            // This must be on the main thread only
            if (Looper.myLooper() != Looper.getMainLooper()) {
                throw IllegalThreadStateException("This cannot be called from other threads. It should be on the main thread only.")
            }
            binding = initializer(thisRef.layoutInflater)
        }
        return binding!!
    }
}

fun <T : ViewBinding> AppCompatActivity.viewBinding(initializer: (LayoutInflater) -> T, justBeforeClear: (T) -> Unit = {}) =
    ActivityViewBindingDelegate(this, initializer, justBeforeClear)
