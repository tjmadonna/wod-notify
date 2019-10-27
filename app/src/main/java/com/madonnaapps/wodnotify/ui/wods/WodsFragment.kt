package com.madonnaapps.wodnotify.ui.wods

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.madonnaapps.wodnotify.R
import com.madonnaapps.wodnotify.common.extensions.buildViewModel
import com.madonnaapps.wodnotify.data.local.entities.WodEntity
import com.madonnaapps.wodnotify.di.WodsFragmentComponent
import com.madonnaapps.wodnotify.di.WodsFragmentComponentImpl
import com.madonnaapps.wodnotify.ui.wods.adapters.WodsListAdapter
import kotlinx.android.synthetic.main.fragment_wods.*

/**
 * A simple [Fragment] for displaying wods in a list.
 */
class WodsFragment : Fragment() {

    private val component: WodsFragmentComponent by lazy {
        WodsFragmentComponentImpl(this)
    }

    private val viewModel by buildViewModel { component.viewModel }

    private val listAdapter = WodsListAdapter(object : WodsListAdapter.Interaction {
        override fun onItemSelected(position: Int, item: WodEntity) {
            Log.d("WodsFragment", "${item.title} selected")
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_wods, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        val recyclerView = recycler_wods
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = listAdapter
    }

    private fun setupObservers() {
        viewModel.wods.observe(viewLifecycleOwner, Observer { wods ->
            listAdapter.submitList(wods)
        })
    }

}
