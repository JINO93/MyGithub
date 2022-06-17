package com.jino.mygithub.Repository

import com.jino.mygithub.base.LoadStatus
import com.jino.mygithub.base.transformResponseToLoadStatus
import com.jino.mygithub.bean.ui.ReposUIModel
import com.jino.mygithub.bean.ui.TrendingRepoModel
import com.jino.mygithub.common.RetrofitManager
import com.jino.mygithub.conversion.RepoConversion
import com.jino.mygithub.service.RepoService
import com.jino.mygithub.util.LogUtils
import com.shuyu.github.kotlin.model.bean.Repository
import com.shuyu.github.kotlin.model.bean.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import org.jsoup.nodes.Element
import javax.inject.Inject

class RepoRepository @Inject constructor(private val retrofitManager: RetrofitManager) {

    companion object{
        const val TAG = "RepoRepository"
    }
    private var mRepoService: RepoService = retrofitManager.createService(RepoService::class.java)

//    suspend fun getTrendRepo(language: String, since: String): Flow<LoadStatus<List<TrendingRepoModel>>>{
//        return flow {
//            emit(mRepoService.getTrendData(true,language,since))
//        }.flowOn(Dispatchers.IO)
//            .map {
//                transformResponseToLoadStatus(it)
//            }.map {
//                parseTrendingRepositoryData(it.data)
//            }.map {
//
//            }
//    }

    suspend fun searchRepo(q: String, sort: String = "", order: String = "", page: Int):LoadStatus<List<ReposUIModel>>{
        LogUtils.d(TAG,"searchRepo q:$q, page:$page")

        val searchRepos = mRepoService.searchRepos(q, sort, order, page)
        LogUtils.d(TAG,"search rsp:$searchRepos")
        val loadStatus = transformResponseToLoadStatus(searchRepos)
        if(loadStatus.errorCode?:0 != 0){
            return LoadStatus.DataError(loadStatus.errorCode?:-1)
        }
        val retList = mutableListOf<ReposUIModel>()
        loadStatus.data?.items?.forEach { repo->
            retList.add(RepoConversion.reposToReposUIModel(repo))
        }
        return LoadStatus.Success(retList)
    }


    @Throws(Exception::class)
    private fun parseTrendingRepositoryData(element: Element): Repository? {
        var fullName = element.select("h1 > a").attr("href")
        fullName = fullName.substring(1)
        val owner = fullName.substring(0, fullName.lastIndexOf("/"))
        val repoName = fullName.substring(fullName.lastIndexOf("/") + 1)
        val descElement = element.getElementsByClass("col-9 color-text-secondary my-1 pr-4").first()
        val numElement = element.getElementsByClass("f6 color-text-secondary mt-2").first()
        var desc = StringBuilder("")
        var language = "unknown"
        var starNumStr = "0"
        var forkNumStr = "0"
        var periodNumStr = "0"
        try {
            if (null != descElement) {
                for (textNode in descElement.textNodes()) {
                    desc.append(textNode.wholeText)
                }
            }
            if (null != numElement) {
                val languageElements = numElement.select("span > span")
                if (null != languageElements && languageElements.size > 0) {
                    language = numElement.select("span > span")[1].textNodes()[0].toString()
                        .trim { it <= ' ' }
                }
                starNumStr = numElement.select("a")[0].textNodes()[1].toString()
                    .replace(" ".toRegex(), "").replace(",".toRegex(), "")
                forkNumStr = numElement.select("a")[1].textNodes()[1].toString()
                    .replace(" ".toRegex(), "").replace(",".toRegex(), "")
                val periodElement =
                    numElement.getElementsByClass("d-inline-block float-sm-right").first()
                if (periodElement != null) {
                    periodNumStr = periodElement.childNodes()[2].toString().trim { it <= ' ' }
                    periodNumStr = periodNumStr.substring(0, periodNumStr.indexOf(" "))
                        .replace(",".toRegex(), "")
                }
            }
        } catch (e: Exception) {
            desc = StringBuilder("desc parse error.")
        }
        val repo = Repository()
        repo.fullName= fullName
        repo.name = repoName
        val user = User()
        user.login = owner
        repo.owner =user
        repo.description = desc.toString().trim { it <= ' ' }
            .replace("\n".toRegex(), "")
        repo.stargazersCount = starNumStr.toInt()
        repo.forksCount = forkNumStr.toInt()
//        repo.setSinceStargazersCount(periodNumStr.toInt())
        repo.language = language
        return repo
    }
}