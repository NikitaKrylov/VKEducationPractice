package com.example.vknext.core.data.locale.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.vknext.core.data.locale.dao.AnswerDao
import com.example.vknext.core.data.locale.entity.AnswerEntity
import com.example.vknext.core.data.locale.entity.AnswerPointEntity
import com.example.vknext.core.data.locale.utils.Converters

@Database(
    entities = [AnswerEntity::class, AnswerPointEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun answerDao(): AnswerDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "feedback_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}