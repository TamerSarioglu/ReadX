package com.tamersarioglu.readx.di

import com.tamersarioglu.readx.data.api.AuthorApi
import com.tamersarioglu.readx.data.repository.BookDetailsRepositoryImpl
import com.tamersarioglu.readx.data.repository.BooksRepositoryImpl
import com.tamersarioglu.readx.domain.repository.BookDetailsRepository
import com.tamersarioglu.readx.domain.repository.BooksRepository
import com.tamersarioglu.readx.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideTolkienBooksApi(retrofit: Retrofit): AuthorApi {
        return retrofit.create(AuthorApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBooksRepository(repository: BooksRepositoryImpl): BooksRepository {
        return repository
    }

    @Provides
    @Singleton
    fun provideBooksDetailRepository(repository: BookDetailsRepositoryImpl): BookDetailsRepository {
        return repository
    }
}