package br.com.yourapp.pokeapi.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.squareup.picasso.Picasso;

import br.com.yourapp.pokeapi.R;

import static dagger.internal.Preconditions.checkNotNull;

public class ActivityUtils {

    public static void addFragmentToActivity (@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    public static void showMessageDialog(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alertdialog_message,null);

        TextView txtMessage = dialogView.findViewById(R.id.txtMessage);
        txtMessage.setText(message);

        builder.setView(dialogView);
        AlertDialog messageDialog = builder.create();
        messageDialog.setCancelable(false);
        messageDialog.setCanceledOnTouchOutside(false);

        messageDialog.show();
        messageDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        messageDialog.getWindow().setLayout(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        StaticButton btnOk = dialogView.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(button -> messageDialog.dismiss());
    }
}
