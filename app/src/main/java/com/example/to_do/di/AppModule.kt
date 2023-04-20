package com.example.to_do.di

import android.app.Application
import androidx.room.Room
import com.example.to_do.data.TodoDao
import com.example.to_do.data.TodoDatabase
import com.example.to_do.data.TodoRepositoryImpl
import com.example.to_do.domain.repository.TodoRepository
import com.google.android.datatransport.runtime.dagger.Module
import com.google.android.datatransport.runtime.dagger.Provides
import javax.inject.Singleton

@Module
//@InstallIn(SingletonComponent::class)

object AppModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): TodoDatabase{
        return Room.databaseBuilder(
            application,
            TodoDatabase::class.java,
            "todo-app-database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(todoDao: TodoDao): TodoRepository{
        return TodoRepositoryImpl(todoDao)
    }
}