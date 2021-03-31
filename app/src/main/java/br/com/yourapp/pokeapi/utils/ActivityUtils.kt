package br.com.yourapp.pokeapi.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import br.com.yourapp.pokeapi.R
import dagger.internal.Preconditions

object ActivityUtils {
    @JvmStatic
    fun addFragmentToActivity(fragmentManager: FragmentManager, fragment: Fragment, frameId: Int) {
        Preconditions.checkNotNull(fragmentManager)
        Preconditions.checkNotNull(fragment)
        val transaction = fragmentManager.beginTransaction()
        transaction.add(frameId, fragment)
        transaction.commit()
    }

    @JvmStatic
    fun showMessageDialog(context: Context, message: String?) {
        val builder = AlertDialog.Builder(context)
        val inflater = (context as Activity).layoutInflater
        val dialogView = inflater.inflate(R.layout.alertdialog_message, null)
        val txtMessage = dialogView.findViewById<TextView>(R.id.txtMessage)
        txtMessage.text = message
        builder.setView(dialogView)
        val messageDialog = builder.create()
        messageDialog.setCancelable(false)
        messageDialog.setCanceledOnTouchOutside(false)
        messageDialog.show()
        messageDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        messageDialog.window!!.setLayout(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        val btnOk: StaticButton = dialogView.findViewById(R.id.btnOk)
        btnOk.setOnClickListener { button: View? -> messageDialog.dismiss() }
    }
}