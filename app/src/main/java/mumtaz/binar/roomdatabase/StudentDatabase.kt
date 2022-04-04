package mumtaz.binar.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1)
abstract class StudentDatabase : RoomDatabase() {

    abstract fun studentDao() :StudentDao

    companion object{
        private var INSTANCE : StudentDatabase? = null
        fun getInstance(context : Context):StudentDatabase? {
            if (INSTANCE == null){
                synchronized(StudentDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        StudentDatabase::class.java,"Student.db").build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}