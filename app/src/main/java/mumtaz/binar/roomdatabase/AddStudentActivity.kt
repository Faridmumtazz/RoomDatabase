package mumtaz.binar.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_student.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AddStudentActivity : AppCompatActivity() {

    var dbstudent : StudentDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        dbstudent = StudentDatabase.getInstance(this)

        btn_save.setOnClickListener {
            GlobalScope.async {
                val nama = et_nama.text.toString()
                val email = et_email.text.toString()

                val hasil = dbstudent?.studentDao()?.insertStudent(Student(null, nama, email))

                runOnUiThread {
                    if (hasil != 0.toLong()){
                        Toast.makeText(this@AddStudentActivity, "Success", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this@AddStudentActivity, "Failed", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}