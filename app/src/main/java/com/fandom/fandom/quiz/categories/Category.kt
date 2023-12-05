package com.fandom.fandom.quiz.categories

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(val name: String, val id: String, val image: String):Parcelable


val categoryList = listOf(
    Category("Marvel", "2233", "https://static.wikia.nocookie.net/marveldatabase/images/8/89/Wiki-wordmark.png/revision/latest/thumbnail/width/400/height/400?cb=20210614021054"),
    Category(
        "Star Wars",
        "147",
        "https:\\/\\/static.wikia.nocookie.net\\/starwars\\/images\\/6\\/63\\/LARGE-Wiki.png\\/revision\\/latest\\/thumbnail\\/width\\/400\\/height\\/400?cb=20210319171629"
    ),
    Category(
        "DC",
        "227",
        "https:\\/\\/static.wikia.nocookie.net\\/marvel_dc\\/images\\/0\\/09\\/Wikia-Visualization-Main%2Cendcdatabase.png\\/revision\\/latest\\/thumbnail\\/width\\/400\\/height\\/400?cb=20161102141712"
    ),
    Category("The Big Bang Theory", "4828", ""),
    Category(
        "Dragon Age",
        "10150",
        "https:\\/\\/static.wikia.nocookie.net\\/dragonage\\/images\\/1\\/10\\/Map-The_Village_of_Haven.jpg\\/revision\\/latest\\/thumbnail\\/width\\/400\\/height\\/400?cb=20091121201858"
    ),
    Category(
        "Fallout",
        "3035",
        "https:\\/\\/static.wikia.nocookie.net\\/fallout\\/images\\/7\\/79\\/Fallout76_Teaser_VaultSuit.png\\/revision\\/latest\\/thumbnail\\/width\\/400\\/height\\/400?cb=20210416153055"
    ),
    Category(
        "Game of Thrones",
        "130814",
        "https:\\/\\/static.wikia.nocookie.net\\/gameofthrones\\/images\\/3\\/32\\/Game_of_Thrones_Header_Slider.png\\/revision\\/latest\\/thumbnail\\/width\\/400\\/height\\/400?cb=20220701044328"
    ),
    Category(
        "Harry Potter",
        "509",
        "https:\\/\\/static.wikia.nocookie.net\\/harrypotter\\/images\\/9\\/9e\\/Harrypotter_launch.jpg\\/revision\\/latest\\/thumbnail\\/width\\/400\\/height\\/400?cb=20170307015852"
    ),
    Category(
        "The Witcher",
        "3443",
        "https:\\/\\/static.wikia.nocookie.net\\/witcher\\/images\\/9\\/96\\/Witcher_wikia_mobile_pic_ciri_geralt.png\\/revision\\/latest\\/thumbnail\\/width\\/400\\/height\\/400?cb=20170606194452"
    ),
    Category(
        "Cyberpunk",
        "241546",
        "https:\\/\\/static.wikia.nocookie.net\\/cyberpunk\\/images\\/b\\/b5\\/Book_cover_2013.jpg\\/revision\\/latest\\/thumbnail\\/width\\/400\\/height\\/400?cb=20180616232145"
    ),
    Category(
        "Diablo",
        "255",
        "https:\\/\\/static.wikia.nocookie.net\\/diablo\\/images\\/b\\/b2\\/Wikia-Visualization-Main%2Cdiablo.png\\/revision\\/latest\\/thumbnail\\/width\\/400\\/height\\/400?cb=20161102140905"
    ),
    Category(
        "Warhammer",
        "151693",
        "https:\\/\\/static.wikia.nocookie.net\\/warhammerfb\\/images\\/8\\/86\\/144312.jpg\\/revision\\/latest\\/thumbnail\\/width\\/400\\/height\\/400?cb=20150606013704"
    ),
    Category(
        "Baldur's Gate",
        "458381",
        "https:\\/\\/static.wikia.nocookie.net\\/baldursgategame\\/images\\/b\\/bc\\/Wikia-Visualization-Main%2Cbaldursgategame.png\\/revision\\/latest\\/thumbnail\\/width\\/400\\/height\\/400?cb=20161102154210"
    ),
    Category("The Office", "1573", ""),
    Category(
        "Rick and Morty",
        "881799",
        "https:\\/\\/static.wikia.nocookie.net\\/rickandmorty\\/images\\/f\\/fc\\/Wikia-Visualization-Main%2Crickandmorty292.png\\/revision\\/latest\\/thumbnail\\/width\\/400\\/height\\/400?cb=20161102173009"
    )

)