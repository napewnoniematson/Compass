package com.napewnoniematson.compass.view.widget;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;

public class GeoCoordinateButton extends AppCompatButton {

    public static final String TAG = GeoCoordinateButton.class.getSimpleName();

    public GeoCoordinateDialog geoDialog;
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
        geoCoordinateName = this.getText().toString();
        geoDialog = new GeoCoordinateDialog(getContext(), geoCoordinateName);
    }

    public class GeoCoordinateDialog extends AlertDialog {

        private static final String POSITIVE_BUTTON_TEXT = "OK";
        private static final String NEGATIVE_BUTTON_TEXT = "CANCEL";

        private static final String POSITIVE_BUTTON_LOG = "Pressed OK";
        private static final String NEGATIVE_BUTTON_LOG = "Pressed CANCEL";

        public final String TAG = GeoCoordinateDialog.class.getSimpleName();

        public AlertDialog dialog;

        public GeoCoordinateDialog(Context context, String geoCoordinateName) {
            super(context);
            Builder builder = new Builder(context);
            builder.setTitle(geoCoordinateName);
            EditText editText = getCoordinateEditText(context);
            builder.setView(editText);
            builder.setPositiveButton(
                    POSITIVE_BUTTON_TEXT, (dialog, id) -> onPositiveButtonClicked(editText)
            );
            builder.setNegativeButton(
                    NEGATIVE_BUTTON_TEXT, (dialog, id) -> onNegativeButtonClicked()
            );
            dialog = builder.create();
        }

        private EditText getCoordinateEditText(Context context) {
            EditText editText = new EditText(context);
            editText.setInputType(
                    InputType.TYPE_CLASS_NUMBER
                            | InputType.TYPE_NUMBER_FLAG_SIGNED
                            | InputType.TYPE_NUMBER_FLAG_DECIMAL
            );
            return editText;
        }

        private void onPositiveButtonClicked(EditText input) {
            String value = input.getText().toString();
            if (!value.isEmpty())
                coordinate = Float.valueOf(input.getText().toString());
            Log.d(TAG, POSITIVE_BUTTON_LOG);
        }

        private void onNegativeButtonClicked() {
            Log.d(TAG, NEGATIVE_BUTTON_LOG);
        }
    }
}
