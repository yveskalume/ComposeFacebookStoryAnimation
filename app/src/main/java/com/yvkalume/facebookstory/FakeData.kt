package com.yvkalume.facebookstory

import androidx.annotation.DrawableRes

data class Story(
    val username: String,
    @DrawableRes
    val profile: Int,
    @DrawableRes
    val image: Int

)

fun getStoriesData(): List<Story> {
    return listOf(
        Story("Yves Kalume",R.drawable.img1,R.drawable.img6),
        Story("John Doe",R.drawable.img5,R.drawable.img3),
        Story("Janne Doe",R.drawable.img4,R.drawable.img1),
        Story("Allan Walker",R.drawable.img1,R.drawable.img6),
        Story("Uncle Bob",R.drawable.img2,R.drawable.img5),
    )
}