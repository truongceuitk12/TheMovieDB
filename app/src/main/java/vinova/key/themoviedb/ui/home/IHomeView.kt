package vinova.key.themoviedb.ui.home

interface IHomeView{
    fun onLoadMore()
    fun onRefresh()
    fun setUpRecyclerView(type : Int)
}