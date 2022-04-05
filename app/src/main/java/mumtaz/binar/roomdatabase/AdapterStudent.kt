package mumtaz.binar.roomdatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_adapter_student.view.*

class AdapterStudent (val listStudent : List<Student>) : RecyclerView.Adapter<AdapterStudent.ViewHolder>(){

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
    }

    override fun getItemCount(): Int {
        return listStudent.size
    }
}