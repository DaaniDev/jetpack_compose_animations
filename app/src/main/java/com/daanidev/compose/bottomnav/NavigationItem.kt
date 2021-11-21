package com.daanidev.compose.bottomnav

import com.daanidev.compose.R

sealed class NavigationItem(var title:String,var icon:Int,var screen_route:String){

    object Home : NavigationItem("Home", R.drawable.ic_home,"home")
    object MyNetwork: NavigationItem("My Network",R.drawable.ic_my_network,"my_network")
    object AddPost: NavigationItem("Post",R.drawable.ic_post,"add_post")
    object Notification: NavigationItem("Notification",R.drawable.ic_notification,"notification")
    object Jobs: NavigationItem("Jobs",R.drawable.ic_job,"jobs")
}
