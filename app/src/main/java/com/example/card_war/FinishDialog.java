package com.example.card_war;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

import org.w3c.dom.Text;

public class FinishDialog extends AppCompatDialogFragment {
    private EditText editTextUsername;
    private TextView points;
    private int numberOfPoints;
    private FinishDialogListener listener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view)
                .setTitle("Congrats")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.myFinish();
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String username = editTextUsername.getText().toString();
                        listener.applyTexts(username , numberOfPoints);
                    }
                });
        editTextUsername = (EditText) view.findViewById(R.id.edit_username);
        points = (TextView) view.findViewById(R.id.points);
        points.setText("Number of points total = "+numberOfPoints);
        return builder.create();
    }
    public interface FinishDialogListener{
        void applyTexts(String username , int points);
        void myFinish();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener =(FinishDialogListener) context;
        } catch (ClassCastException e) {
            throw  new ClassCastException(context.toString() + "must implement FinishDialogListener");
        }
    }
    public void setPoints(int points){
        this.numberOfPoints = points;
    }
}
