package com.example.ayush.kotlinstarter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by ayush on 7/4/17.
 */

fun ViewGroup.inflate(layoutId: Int, attachToRoot : Boolean = false) = LayoutInflater.from(context).inflate(layoutId, this, false)

fun ImageView.loadImage(imageUrl : String) =
    if (TextUtils.isEmpty(imageUrl)){
        Picasso.with(context).load(R.mipmap.ic_launcher).into(this)
    }
    else{
        Picasso.with(context).load(imageUrl).into(this)
    }