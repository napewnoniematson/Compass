package com.napewnoniematson.compass.view;

import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;

public class GeoCoordinateButton extends AppCompatButton implements View.OnClickListener {

    public static final String TAG = GeoCoordinateButton.class.getSimpleName();

    private GeoCoordinateDialog geoDialog;

    public GeoCoordinateButton(Context context) {
        super(context);
        init();
    }

    public GeoCoordinateButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOnClickListener(this);
        geoDialog = new GeoCoordinateDialog(getContext(), this.getText().toString());
    }

    @Override
    public void onClick(View v) {
        geoDialog.dialog.show();
        Log.d(TAG, this.getText().toString() + ": " + geoDialog.coordinate);
    }


    private class GeoCoordinateDialog extends AlertDialog {

        public final String TAG = GeoCoordinateDialog.class.getSimpleName();

        private AlertDialog dialog;
        private Float coordinate;

        public GeoCoordinateDialog(Context context, String geoCoordinateName) {
            super(context);
            Builder builder = new Builder(context);
            builder.setTitle("Enter " + geoCoordinateName);
            EditText editText = new EditText(context);
            editText.setInputType(
                    InputType.TYPE_CLASS_NUMBER
                            | InputType.TYPE_NUMBER_FLAG_SIGNED
                            | InputType.TYPE_NUMBER_FLAG_DECIMAL
            );
            builder.setView(editText);
            builder.setPositiveButton("OK", (dialog, id) -> {
                if (!editText.getText().toString().equals("")) {
                    coordinate = Float.valueOf(editText.getText().toString());
                }
                Log.d(TAG, "Pressed OK");
            });
            builder.setNegativeButton("CANCEL", (dialog, id) -> {
                Log.d(TAG, "Pressed Cancel");
            });
            dialog = builder.create();
        }
    }


}
