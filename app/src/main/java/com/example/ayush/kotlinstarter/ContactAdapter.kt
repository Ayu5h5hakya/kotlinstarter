package com.example.ayush.kotlinstarter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_contact.view.*
import kotlinx.android.synthetic.main.news_item.view.*

/**
 * Created by ayush on 7/25/17.
 */
class ContactAdapter(context :Context) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    private var contacts: MutableList<Contact>? = null
    private val contactClickListener : ContactListClickListener

    init {
        contacts = mutableListOf()
        contactClickListener = context as ContactListClickListener
    }

    fun addContact(contactlist: MutableList<Contact>) {
        contacts?.clear()
        for (contact in contactlist.iterator()) {
            contacts?.add(contact)
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ContactViewHolder?, position: Int) {
        holder as ContactViewHolder
        if (contacts != null)
            holder.bind((contacts as List<Contact>)[position])

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ContactViewHolder(parent)

    override fun getItemCount() = contacts?.size ?: 0

    inner class ContactViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.news_item)), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(contact: Contact) = itemView.apply {
            contactName.text = contact.name
            contactAddress.text = contact.address
            contactNumber.text = contact.phone.toString()
            deleteContact.setOnClickListener(this@ContactViewHolder)
        }


        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.deleteContact -> {
                    if (contacts!=null){
                        val contact = contacts!!.get(adapterPosition)
                        contacts!!.removeAt(adapterPosition)
                        contactClickListener.onContactDelete(contact)
                    }

                }
                itemView.id -> {
                    val contact = contacts!!.get(adapterPosition)
                    contactClickListener.onContactEdit(contact)
                }
                else -> {

                }
            }
        }

    }

    interface ContactListClickListener {
        fun onContactEdit( contact : Contact)
        fun onContactDelete( contact : Contact)
    }
}