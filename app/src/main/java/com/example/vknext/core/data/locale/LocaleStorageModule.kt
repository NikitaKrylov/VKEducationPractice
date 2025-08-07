package com.example.vknext.core.data.locale

import android.content.Context
import androidx.room.Room
import com.example.vknext.core.data.locale.dao.AnswerDao
import com.example.vknext.core.data.locale.database.AppDatabase
import com.example.vknext.core.data.locale.utils.Converters
import com.example.vknext.core.data.repositories.answers.AnswersRepository
import com.example.vknext.core.data.repositories.answers.AnswersRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocaleStorageModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "feedback_db"
        ).addTypeConverter(Converters())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideDao(db: AppDatabase) = db.answerDao()

    @Provides
    fun provideRepository(dao: AnswerDao): AnswersRepository {
        return AnswersRepositoryImpl(dao)
    }
}