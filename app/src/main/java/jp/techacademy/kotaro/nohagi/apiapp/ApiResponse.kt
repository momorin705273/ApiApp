package jp.techacademy.kotaro.nohagi.apiapp
import com.google.gson.annotations.SerializedName


data class ApiResponse(
    @SerializedName("results")
    val results: Results
)

data class Results(
    @SerializedName("shop")
    val shop: List<Shop>
)

data class Shop(
    @SerializedName("coupon_urls")
    val couponUrls: CouponUrls,
    @SerializedName("id")
    val id: String,
    @SerializedName("logo_image")
    val logoImage: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("address")
    var address: String,
)

data class Budget(
    @SerializedName("average")
    val average: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("name")
    val name: String
)

data class CouponUrls(
    @SerializedName("pc")
    val pc: String,
    @SerializedName("sp")
    val sp: String
)