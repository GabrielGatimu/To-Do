package com.example.to_do.data

data class TodolistData (
    val id : String,
    val username : String,
    val name : String,
    val isFollow : Boolean,
    val bio : String,
    val profilePic : String,
    val post : Int,
    val noOfFollowers : Int,
    val noOfFollowing : Int,
    val imagePost : List<String>
    )
{
 fun getUsers() : List<TodolistData>{
     return listOf(
         TodolistData(
             id = "1",
             username = "gabu", name = "Gabriel",
             bio = "On God",

             isFollow = false,
             post = 12,
             noOfFollowers = 30,
             noOfFollowing = 30,

             profilePic = "https://i.picsum.photos/id/1005/5760/3840.jpg?hmac=2acSJCOwz9q_dKtDZdSB-OIK1HUcwBeXco_RMMTUgfY",
             imagePost = listOf(
                 "https://i.picsum.photos/id/1005/5760/3840.jpg?hmac=2acSJCOwz9q_dKtDZdSB-OIK1HUcwBeXco_RMMTUgfY",
                 "https://i.picsum.photos/id/1024/1920/1280.jpg?hmac=-PIpG7j_fRwN8Qtfnsc3M8-kC3yb0XYOBfVzlPSuVII",
                 "https://i.picsum.photos/id/1015/6000/4000.jpg?hmac=aHjb0fRa1t14DTIEBcoC12c5rAXOSwnVlaA5ujxPQ0I"
             ),

         )
     )
 }


}