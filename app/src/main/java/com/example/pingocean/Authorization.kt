package com.example.pingocean

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import com.example.pingocean.data.User
import com.example.pingocean.retrofit.Api
import com.example.pingocean.retrofit.RetrofitHelper
import kotlinx.coroutines.*


class Authorization : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_authorization, container, false)

        val email: EditText = v.findViewById(R.id.EmailAddress)
        val pass: EditText = v.findViewById(R.id.Password)

        //Btn
        val btn: Button = v.findViewById(R.id.button)

        btn.setOnClickListener{
            // Get email and password from user
            val semail = email.text.toString()
            val spass = pass.text.toString()

            // Check
            if (semail.isEmpty()){

                Toast.makeText(context, "Вы не ввели email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (spass.isEmpty()){
                Toast.makeText(context, "Вы не ввели пароль", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(!semail.contains('@')){
                Toast.makeText(context, "Вы ввели неправильный email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = User(semail.trim(),spass)

            val api = RetrofitHelper.getInstance().create(Api::class.java)

            viewLifecycleOwner.lifecycleScope.launch {
                val result = api.getToken(user)
                if(result.body()?.token.isNullOrEmpty()){
                    withContext(Dispatchers.Main) {
                        Toast.makeText(context, "Ваши данные неправильные", Toast.LENGTH_SHORT).show()
                    }
                }
                else {
                    val secondFragment = ShowData.newInstance(result.body()?.token.toString())

                    val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
                    transaction.replace(R.id.main,secondFragment).commit()
                }
            }

        }
        return v
    }


}