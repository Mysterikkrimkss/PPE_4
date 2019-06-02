package com.example.ppe3
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.ArrayAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import kotlin.collections.ArrayList
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_mes_rapports.*
import android.content.Intent


class mesRapportsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mes_rapports)



        var list= ArrayList<String>()
        var db= FirebaseDatabase.getInstance().reference
        val builderSingle = AlertDialog.Builder(this)

        builderSingle.setTitle("Select One Name:-");

        db.child("praticien").addValueEventListener(object : ValueEventListener{

            override fun onDataChange(p0: DataSnapshot) {
                var praticien=p0.value as HashMap<String,Any>
                for(p in praticien.keys){
                    list.add(p)}
                var adp = ArrayAdapter(applicationContext,
                    android.R.layout.simple_list_item_1,list)
                lv2.adapter=adp

                }
            //lv.setOnItemClickListener { startActivity(Intent(this,listviewActivity::class.java))
           // }





            override fun onCancelled(p0: DatabaseError) {
            }
        })
    }
}
