package com.example.pingocean

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.pingocean.retrofit.Api
import com.example.pingocean.retrofit.RetrofitHelper
import kotlinx.coroutines.launch


class ShowData : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val token = arguments?.getString("token").toString()

        val v = inflater.inflate(R.layout.fragment_show_data, container, false)
        val btn: Button =v.findViewById(R.id.button)

        val api = RetrofitHelper.getInstance().create(Api::class.java)

        viewLifecycleOwner.lifecycleScope.launch {
            val result = api.getProfiles("Bearer $token")

            //Get info
            val id: (TextView) = v.findViewById(R.id.idText)
            val name: (TextView) = v.findViewById(R.id.name)
            val email: (TextView) = v.findViewById(R.id.email)
            val phone: (TextView) = v.findViewById(R.id.phone)
            val avatar: (TextView) = v.findViewById(R.id.avatar)
            val position: (TextView) = v.findViewById(R.id.position)
            val companyName: (TextView) = v.findViewById(R.id.companyName)
            val nameEng: (TextView) = v.findViewById(R.id.nameEng)
            val timezone: (TextView) = v.findViewById(R.id.timezone)
            val sections: (TextView) = v.findViewById(R.id.sections)
            val alertEmail: (TextView) = v.findViewById(R.id.alertEmail)
            val sendSystemNotifications: (TextView) = v.findViewById(R.id.sendSystemNotifications)

            // Showing data
            setTextView(result.body()?.id.toString(), id)
            setTextView(result.body()?.name.toString(), name)
            setTextView(result.body()?.email.toString(), email)
            setTextView(result.body()?.phone.toString(), phone)
            setTextView(result.body()?.avatar.toString(), avatar)
            setTextView(result.body()?.position.toString(), position)
            setTextView(result.body()?.companyName.toString(), companyName)
            setTextView(result.body()?.nameEng.toString(), nameEng)
            setTextView(result.body()?.timezone.toString(), timezone)
            setTextView(result.body()?.sections.toString(), sections)
            setTextView(result.body()?.alertEmail.toString(), alertEmail)
            setTextView(result.body()?.sendSystemNotifications.toString(), sendSystemNotifications)

        }

        btn.setOnClickListener {
            requireActivity().run {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }

        return v
    }

    fun setTextView(data: String, textView: TextView){
        if(data.isNullOrEmpty()){
            textView.text = "${textView.text} - null"
        }
        else {
            textView.text = "${textView.text} - $data";
        }
    }


    companion object {
        fun newInstance(token: String?): ShowData {
            val f = ShowData()
            // Pass index input as an argument.
            val args = Bundle()
            args.putString("token", token)
            f.arguments = args
            return f
        }
    }

}