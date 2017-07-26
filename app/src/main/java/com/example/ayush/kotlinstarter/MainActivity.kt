package com.example.ayush.kotlinstarter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ContactAdapter.ContactListClickListener {

    private val contacts: RecyclerView by lazy {
        idRecycler.layoutManager = LinearLayoutManager(this)
        idRecycler.setHasFixedSize(true)
        idRecycler
    }

    lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        realm = Realm.getDefaultInstance()

    }

    override fun onResume() {
        super.onResume()
        if (contacts.childCount == 0) {
            realm.executeTransaction {
                var contactAdapter = ContactAdapter(this)
                contactAdapter.addContact(realm.where(Contact::class.java).findAll().toMutableList())
                contacts.adapter = contactAdapter
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.getItemId()) {
            R.id.add -> {
                startActivityForResult(newintent(this), 777)

            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 777 && resultCode == Activity.RESULT_OK) {
            realm.executeTransaction {
                (contacts.adapter as ContactAdapter).addContact(realm.where(Contact::class.java).findAll().toMutableList())
            }
        }
    }

    override fun onContactEdit(contact: Contact) {
        startActivityForResult(newintent(this, contact.name, contact.address, contact.phone), 777)
    }

    override fun onContactDelete(contact: Contact) {
        realm.executeTransaction {
            realm.where(Contact::class.java)
                    .equalTo(KEY_NAME, contact.name)
                    .equalTo(KEY_ADDRESS, contact.address)
                    .equalTo(KEY_PHONE, contact.phone)
                    .findFirst()
                    .deleteFromRealm()
        }
        contacts.adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    companion object {

        val KEY_NAME = "name"
        val KEY_ADDRESS = "address"
        val KEY_PHONE = "phone"

        fun newintent(context: Context, name: String? = null, address: String? = null, phone: Long? = null): Intent {

            val intent = Intent(context, ContactActivity::class.java)
            intent.putExtra(KEY_NAME, name)
            intent.putExtra(KEY_ADDRESS, address)
            intent.putExtra(KEY_PHONE, phone)

            return intent
        }
    }

}
