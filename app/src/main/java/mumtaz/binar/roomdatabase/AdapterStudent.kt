package mumtaz.binar.roomdatabase

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_adapter_student.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AdapterStudent (val listStudent : List<Student>) : RecyclerView.Adapter<AdapterStudent.ViewHolder>(){

    private var mdb : StudentDatabase? = null
    class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterStudent.ViewHolder {
        val viewItem = LayoutInflater.from(parent.context).inflate(R.layout.item_adapter_student, parent, false)
        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: AdapterStudent.ViewHolder, position: Int) {
        holder.itemView.tv_id_student.text = listStudent[position].id.toString()
        holder.itemView.tv_nama_student.text = listStudent[position].nama
        holder.itemView.tv_email_student.text = listStudent[position].email

        holder.itemView.btn_delete.setOnClickListener {
            mdb = StudentDatabase.getInstance(it.context)

            AlertDialog.Builder(it.context)
                .setTitle("Hapus Data")
                .setMessage("Yakin Hapus Data ?")
                .setPositiveButton("Ya"){dialogInterface: DialogInterface, i: Int ->
                    GlobalScope.async {
                        val result = mdb?.studentDao()?.deleteStudent(listStudent[position])

                        (holder.itemView.context as MainActivity).runOnUiThread{
                            if (result != 0){
                                Toast.makeText(it.context, "Data ${listStudent[position].nama} Berhasil di Hapus", Toast.LENGTH_LONG).show()
                                (holder.itemView.context as MainActivity).getDataStudent()
                            }else {
                                Toast.makeText(it.context, "Data ${listStudent[position].nama} Gagal di Hapus", Toast.LENGTH_LONG).show()
                            }
                        }

                    }
                }
                .setNegativeButton("Tidak"){dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.dismiss()
                }
                .show()
        }

        holder.itemView.btn_edit.setOnClickListener {
            val pindah = Intent(it.context, EditStudentActivity::class.java)
            pindah.putExtra("datastudent", listStudent[position])
            it.context.startActivity(pindah)
        }
    }

    override fun getItemCount(): Int {
        return listStudent.size
    }
}