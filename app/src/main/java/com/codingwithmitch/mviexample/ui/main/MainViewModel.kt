package com.codingwithmitch.mviexample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.codingwithmitch.mviexample.model.BlogPost
import com.codingwithmitch.mviexample.model.User
import com.codingwithmitch.mviexample.ui.main.state.MainStateEvent
import com.codingwithmitch.mviexample.ui.main.state.MainViewState
import com.codingwithmitch.mviexample.util.AbsentLiveData

class MainViewModel : ViewModel() {
    private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData()
    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()

    val viewState: LiveData<MainViewState>
        get() = _viewState

    val dataState: LiveData<MainViewState> = Transformations
        .switchMap(_stateEvent) { stateEvent ->
            stateEvent?.let {
                handleStateEvent(it)
            }
        }


    private fun handleStateEvent(stateEvent: MainStateEvent): LiveData<MainViewState> {
        when (stateEvent) {

            is MainStateEvent.GetBlogPostsEvent -> {

                return object : LiveData<MainViewState>() {
                    override fun onActive() {
                        super.onActive()
                        val blogList = ArrayList<BlogPost>()
                        blogList.add(
                            BlogPost(
                                0,
                                "Description 1",
                                "Username 1",
                                "https://images.unsplash.com/photo-1519125323398-675f0ddb6308?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60"
                            )
                        )
                        blogList.add(
                            BlogPost(
                                1,
                                "Description 2",
                                "Username 2",
                                "https://images.unsplash.com/photo-1519125323398-675f0ddb6308?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60"
                            )
                        )
                    }
                }
            }

            is MainStateEvent.GetUserEvent -> {
                return object : LiveData<MainViewState>() {
                    override fun onActive() {
                        super.onActive()
                        val user = User(
                            email = "ehsan kolivand",
                            username = "ehsan.kolivand",
                            image = "https://images.unsplash.com/photo-1519125323398-675f0ddb6308?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60"
                        )
                        value = MainViewState(user = user)
                    }
                }
            }
            is MainStateEvent.None -> {
                return AbsentLiveData.create()
            }
        }
    }

    fun setBlogListData(blogList: List<BlogPost>) {
        val update = getCurrentViewStateOrNew()
        update?.blogPosts = blogList
        _viewState.value = update
    }

    fun setUser(user: User) {
        val update = getCurrentViewStateOrNew()
        update?.user = user
        _viewState.value = update
    }


    private fun getCurrentViewStateOrNew(): MainViewState? {
        val value = viewState.value?.let {
            it
        } ?: MainViewState()
        return value
    }

    fun setStateEvent(event: MainStateEvent) {
        _stateEvent.value = event
    }
}


