package com.example.ppe3


import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.*
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_listview.*
import java.text.DateFormat
import java.time.ZoneId
import java.util.*


class listviewActivity : AppCompatActivity() {

    lateinit var list_motif: EditText
    lateinit var list_bilan: EditText
    lateinit var list_echan: EditText
    //lateinit var list_date: DatePicker
    lateinit var list_save: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listview)

        var list= ArrayList<String>()
        var db= FirebaseDatabase.getInstance().reference
        val builderSingle = AlertDialog.Builder(this)

        builderSingle.setTitle("Select One Name:-");

        db.child("praticien").addValueEventListener(object : ValueEventListener {

            override fun onDataChange(p0: DataSnapshot) {
                var praticien=p0.value as HashMap<String,Any>
                for(p in praticien.keys){
                    list.add(p)}
                var adp = ArrayAdapter(applicationContext,
                    android.R.layout.simple_list_item_1,list)
                spinner.adapter=adp

            }
            //lv.setOnItemClickListener { startActivity(Intent(this,listviewActivity::class.java))
            // }





            override fun onCancelled(p0: DatabaseError) {
            }
        })


        val calendar = Calendar.getInstance()
        val currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.time)




        list_motif = findViewById(R.id.list_motif)
        list_bilan = findViewById(R.id.list_bilan)
        list_echan = findViewById(R.id.list_echan)
        //list_date = findViewById(R.id.list_date)
        list_save = findViewById(R.id.list_save)

        list_save.setOnClickListener {
            saveRapport()
        }
    }


    @SuppressLint("NewApi")
    private fun saveRapport(){
        val motif = list_motif.text.toString().trim()
        val bilan = list_bilan.text.toString().trim()
        val echan = list_echan.text.toString().trim()
        val praticien = spinner.toString().trim()


        if(motif.isEmpty()){
            list_motif.error = "svp entre le motif merci !"
            return
        }

      //  val date = DateFormat.getDateInstance().timeZone

       // val date2 = TimeZone.getTimeZone(ZoneId.systemDefault())



        val ref= FirebaseDatabase.getInstance().getReference("rapports")

        val rapportId = ref.push().key

        val rapport = Rapport(rapportId!!,motif,bilan, echan,praticien)

        ref.child(rapportId).setValue(rapport).addOnCompleteListener {
            Toast.makeText(applicationContext,"sa marche !!!!!",Toast.LENGTH_LONG).show()
        }
    }



}
