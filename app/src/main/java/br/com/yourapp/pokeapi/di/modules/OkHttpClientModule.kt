package br.com.yourapp.pokeapi.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File

@Module(includes = [ContextModule::class])
class OkHttpClientModule {
    @Provides
    fun okHttpClient(cache: Cache?, httpLoggingInterceptor: HttpLoggingInterceptor?): OkHttpClient {
        return OkHttpClient()
                .newBuilder()
                .addInterceptor(httpLoggingInterceptor!!)
                .cache(cache)
                .build()
    }

    @Provides
    fun cache(cacheFile: File?): Cache {
        return Cache(cacheFile!!, 10 * 1000 * 1000)
    }

    @Provides
    fun file(context: Context): File {
        val file = File(context.cacheDir, "HttpCache")
        file.mkdirs()
        return file
    }

    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }
}