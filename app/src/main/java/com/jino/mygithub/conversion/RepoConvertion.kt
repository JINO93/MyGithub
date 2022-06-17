package com.jino.mygithub.conversion

import com.jino.mygithub.bean.ui.ReposUIModel
import com.jino.mygithub.util.DateUtil
import com.shuyu.github.kotlin.model.bean.Repository

object RepoConversion {

    fun reposToReposUIModel(repository: Repository?): ReposUIModel {
        val reposUIModel = ReposUIModel()
        reposUIModel.hideWatchIcon = true
        reposUIModel.ownerName = repository?.owner?.login ?: ""
        reposUIModel.ownerPic = repository?.owner?.avatarUrl ?: ""
        reposUIModel.repositoryDes = repository?.description ?: ""
        reposUIModel.repositoryName = repository?.name ?: ""
        reposUIModel.repositoryFork = repository?.forksCount?.toString() ?: ""
        reposUIModel.repositoryStar = repository?.stargazersCount?.toString() ?: ""
        reposUIModel.repositoryWatch = repository?.subscribersCount?.toString() ?: ""
        reposUIModel.repositoryType = repository?.language ?: ""
        reposUIModel.repositorySize = (((repository?.size
            ?: 0) / 1024.0)).toString().substring(0, 3) + "M"
        reposUIModel.repositoryLicense = repository?.license?.name ?: ""


        val createStr = if (repository != null && repository.fork)
            "Fork from ${repository.parent?.name ?: ""}\n"
        else
            "创建于$DateUtil.getDateStr(repository?.createdAt)\n"

        reposUIModel.repositoryAction = createStr
        reposUIModel.repositoryIssue = repository?.openIssuesCount?.toString() ?: ""
        return reposUIModel
    }
}