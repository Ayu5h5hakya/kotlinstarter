package com.example.ayush.kotlinstarter

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_contact.*

class ContactActivity : AppCompatActivity() {

    lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        realm = Realm.getDefaultInstance()

        contactname.setText(intent.getStringExtra("name"))
        contactaddress.setText(intent.getStringExtra("address"))
        if (intent.getLongExtra("phone", -1).toInt() != -1) contactnum.setText(intent.getLongExtra("phone", -1).toString())

        contactSave.setOnClickListener {
            if (contactname.isvalid() && contactaddress.isvalid() && contactnum.isvalid()) {
                realm.executeTransaction {
                    val contact = realm.createObject(Contact::class.java)
                    contact.name = contactname.text.toString()
                    contact.address = contactaddress.text.toString()
                    contact.phone = contactnum.text.toString().toLong()
                    realm.copyToRealm(contact)
                    setResult(Activity.RESULT_OK)
                    finish()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}
