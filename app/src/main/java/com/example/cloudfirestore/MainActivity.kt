package com.example.cloudfirestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore



class MainActivity : AppCompatActivity() {

    private lateinit var ename: EditText
    private lateinit var econtact: EditText
    private lateinit var sbutton: Button
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ename = findViewById(R.id.edtname)
        econtact = findViewById(R.id.edtContact)
        sbutton = findViewById(R.id.buttonSubmit)
        db = FirebaseFirestore.getInstance()

        sbutton.setOnClickListener {
            val name = ename.text.toString()
            val contact = econtact.text.toString()
            cloud(name, contact)
        }
    }

    private fun cloud(name: String, contact: String) {
        val user = hashMapOf(
            "name" to name,
            "contact" to contact
        )

        db.collection("users").add(user).addOnSuccessListener { documentReference ->
            Toast.makeText(this, "Details captured at: ${documentReference.id}", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener { e ->
            Toast.makeText(this, "Error entering details: $e", Toast.LENGTH_SHORT).show()
        }
    }
}
