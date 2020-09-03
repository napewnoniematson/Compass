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

import com.napewnoniematson.compass.model.GeoPoint;

public class GeoCoordinateButton extends AppCompatButton {

    public static final String TAG = GeoCoordinateButton.class.getSimpleName();

    public GeoCoordinateDialog geoDialog;
//    private GeoPoint geoPoint = new GeoPoint();
    public Float coordinate = 0f;
    public String geoCoordinateName;

    public GeoCoordinateButton(Context context) {
        super(context);
        init();
    }

    public GeoCoordinateButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
//        setOnClickListener(this);
        this.geoCoordinateName = this.getText().toString();
        geoDialog = new GeoCoordinateDialog(getContext(), this.geoCoordinateName);
    }

//    @Override
//    public void onClick(View v) {
//        geoDialog.dialog.show();
//        Log.d(TAG, this.getText().toString() + ": " + geoDialog.coordinate);
//    }


    public class GeoCoordinateDialog extends AlertDialog {

        public final String TAG = GeoCoordinateDialog.class.getSimpleName();

        public AlertDialog dialog;

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
                String value = editText.getText().toString();
                if (!value.equals("")) {
                    coordinate = Float.valueOf(editText.getText().toString());
//                    geoPoint.addCoordinate(geoCoordinateName, Float.parseFloat(value));
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
