package com.codingwithmitch.mviexample.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.codingwithmitch.mviexample.R
import com.codingwithmitch.mviexample.ui.main.state.MainStateEvent

class MainFragment : Fragment() {
    lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        this.subscribeObservers()
    }
    fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { it ->
            println("DEBUG: new viewState: $it")
            it.blogPosts?.let {
                viewModel.setBlogListData(it)
            }
            it.user?.let {
                viewModel.setUser(it)
            }
        })
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            it.blogPosts?.let {

            }
            it.user?.let {

            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_get_user -> {
                triggerGetUserEvent()
            }
            R.id.action_get_blogs -> {
                triggerGetBlogsEvent()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun triggerGetBlogsEvent() {
        viewModel.setStateEvent(MainStateEvent.GetBlogPostsEvent)
    }

    private fun triggerGetUserEvent() {
        viewModel.setStateEvent(MainStateEvent.GetUserEvent("1"))
    }

}