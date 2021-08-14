package jp.techacademy.kotaro.nohagi.apiapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {
    // 一覧画面から登録するときのコールバック（FavoriteFragmentへ通知するメソッド)
    var onClickAddFavorite: ((String,String,String,String) -> Unit)? = null
    // 一覧画面から削除するときのコールバック（ApiFragmentへ通知するメソッド)
    var onClickDeleteFavorite: ((String) -> Unit)? = null

    var isFav:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        val id:String=intent.getStringExtra(KEY_ID).toString()
        val name:String=intent.getStringExtra(KEY_NAME).toString()
        val image:String=intent.getStringExtra(KEY_IMAGE).toString()
        val url:String=intent.getStringExtra(KEY_URL).toString()
        val address:String=intent.getStringExtra(KEY_ADDRESS).toString()
        isFav= (FavoriteShop.findBy(id) != null)
        webView.loadUrl(intent.getStringExtra(KEY_URL).toString())
        setStar(isFav)

        fab.setOnClickListener {
            if (isFav) {
                deleteFavorite(id)
            } else {
                addFavorite(id,name,image,url,address)
                isFav = !isFav
                setStar(isFav)
            }
        }
    }

    fun setStar(isFav:Boolean){
        fab.setImageResource(if (isFav) R.drawable.ic_star else R.drawable.ic_star_border) // Picassoというライブラリを使ってImageVIewに画像をはめ込む
    }

    fun addFavorite(aId: String,aName:String,aImage:String,aUrl: String,aAddress: String) {
        FavoriteShop.insert(FavoriteShop().apply {
            id = aId
            name = aName
            imageUrl = aImage
            url = aUrl
            address =aAddress
        })
    }

    fun deleteFavorite(id: String) {
        AlertDialog.Builder(this)
            .setTitle(R.string.delete_favorite_dialog_title)
            .setMessage(R.string.delete_favorite_dialog_message)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                FavoriteShop.delete(id)
                isFav = !isFav
                setStar(isFav)
            }
            .setNegativeButton(android.R.string.cancel) { _, _ -> }
            .create()
            .show()
    }

    companion object {
        private const val KEY_URL = "key_url"
        private const val KEY_ID = "key_id"
        private const val KEY_IMAGE = "key_image"
        private const val KEY_NAME = "key_name"
        private const val KEY_ADDRESS = "key_address"
        fun start(activity: Activity, aId: String,aName:String,aImage:String,aUrl: String,aAddress:String) {

            val intent = Intent(activity, WebViewActivity::class.java)
            intent.putExtra(KEY_URL, aUrl)
            intent.putExtra(KEY_ID, aId)
            intent.putExtra(KEY_IMAGE, aImage)
            intent.putExtra(KEY_NAME, aName)
            intent.putExtra(KEY_ADDRESS, aAddress)
            activity.startActivity(intent)
        }
    }
}