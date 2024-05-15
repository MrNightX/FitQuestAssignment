package com.example.fitquest.ui.UserInfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import com.example.fitquest.R
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.fitquest.databinding.ActivityRegisterBinding

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener


class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding
    private var email:String = ""
    private var password:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth



    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.


        binding.buttonRegister.setOnClickListener{

            email = binding.editTextRegisterEmail.text.toString()
            password = binding.editTextRegisterPassword.text.toString()


            if(email.isEmpty()){
                Toast.makeText(this,"Enter Email",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(password.isEmpty()){
                Toast.makeText(this,"Enter Password",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
                    val user:User = User("Jason",0,25,"0129889166", 161.0f,45.0f,0,0,email,password)
                    val database = FirebaseDatabase.getInstance()
                    val myRef = database.getReference("Users")
                    val emailQuery:Query = myRef.orderByChild("email").equalTo(user.email)
                    emailQuery.addListenerForSingleValueEvent(object : ValueEventListener{
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if(snapshot.exists()){
                                Toast.makeText(this@RegisterActivity,"Email Existed",Toast.LENGTH_SHORT).show()
                            }else{
                                val userId = myRef.push().key ?: return
                                myRef.child(userId).setValue(user).addOnSuccessListener {
                                Toast.makeText(this@RegisterActivity,"User Added",Toast.LENGTH_SHORT).show()
                                createAccount(user.email,user.password)
                            }
                                .addOnFailureListener{ error ->
                                Toast.makeText(this@RegisterActivity,"User Fail to Add",Toast.LENGTH_SHORT).show()

                                }
                            }
                        }
                override fun onCancelled(databaseError: DatabaseError) {
                    Toast.makeText(this@RegisterActivity,"Database Error",Toast.LENGTH_SHORT).show()
                }
            })




        }

        binding.textViewToLogin.setOnClickListener{
            val intent = Intent(this@RegisterActivity,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun createAccount(email: String, password: String) {
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Task Successful",
                        Toast.LENGTH_SHORT,
                    ).show()
                    val intent = Intent(this@RegisterActivity,LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            task.exception.toString(),
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
            }
        // [END create_user_with_email]
    }
