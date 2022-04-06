package mumtaz.binar.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit_student.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class EditStudentActivity : AppCompatActivity() {
    private var dbnew : StudentDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        dbnew = StudentDatabase.getInstance(this)

        val getDataStudent = intent.getParcelableExtra<Student>("datastudent") as Student

        et_editnama.setText(getDataStudent.nama)
        et_editemail.setText(getDataStudent.email)

        btn_editsave.setOnClickListener {
            getDataStudent.nama = et_editnama.text.toString()
            getDataStudent.email = et_editemail.text.toString()

            GlobalScope.async {
                val perintah = dbnew?.studentDao()?.updateStudent(getDataStudent)

                runOnUiThread {
                    if (perintah != 0) {
                        Toast.makeText(this@EditStudentActivity,"Data ${getDataStudent.nama} Berhasil di Edit", Toast.LENGTH_LONG).show()
                    }else {
                        Toast.makeText(this@EditStudentActivity,"Data ${getDataStudent.nama} Gagal di Edit", Toast.LENGTH_LONG).show()
                    }
                    finish()
                }
            }
        }
    }
}