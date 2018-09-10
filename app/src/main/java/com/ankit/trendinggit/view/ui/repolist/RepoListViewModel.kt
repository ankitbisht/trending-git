package com.ankit.trendinggit.view.ui.repolist

import androidx.lifecycle.MutableLiveData
import com.ankit.trendinggit.model.Item
import com.ankit.trendinggit.model.RepoRepository
import com.ankit.trendinggit.view.base.BaseViewModel

class RepoListViewModel : BaseViewModel() {
    val repoListLive = MutableLiveData<List<Item>>()

    fun fetchRepoList() {
        dataLoading.value = true
        RepoRepository.getInstance().getRepoList { isSuccess, response ->
            dataLoading.value = false
            if (isSuccess) {
                repoListLive.value = response?.items
                empty.value = false
            } else {
                empty.value = true
            }
        }
    }
}