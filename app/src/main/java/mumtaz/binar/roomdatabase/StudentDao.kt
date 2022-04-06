package mumtaz.binar.roomdatabase

import androidx.room.*

@Dao
interface StudentDao {


    @Insert
    fun insertStudent(student: Student) : Long

    @Query("SELECT * FROM Student")
    fun getAllStudent() : List<Student>

    @Delete
    fun deleteStudent(student: Student) : Int

    @Update
    fun updateStudent(student: Student) : Int
}