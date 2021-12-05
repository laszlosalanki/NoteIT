package hu.unideb.noteit.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.util.*

private const val BASE_URL = "https://xkcd.com/614/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ComicApiService {
    @GET("info.0.json")
    suspend fun getProperties(): List<Comic>
}
object ComicApi {
    val retrofitService: ComicApiService by lazy {
        retrofit.create(ComicApiService::class.java)
    }
}