package jp.techacademy.kotaro.nohagi.apiapp

import android.location.Address

interface FragmentCallback {
    // Itemを押したときの処理
    fun onClickItem(id: String,name:String,image:String,url:String,address: String)
    // お気に入り追加時の処理
    fun onAddFavorite(shop: Shop)
    // お気に入り削除時の処理
    fun onDeleteFavorite(id: String)
}