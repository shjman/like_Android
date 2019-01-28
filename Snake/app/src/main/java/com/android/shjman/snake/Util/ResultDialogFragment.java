package com.android.shjman.snake.Util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.android.shjman.snake.App;
import com.android.shjman.snake.R;
import com.android.shjman.snake.activities.SnakeFiledActivity;

public class ResultDialogFragment extends DialogFragment {
    public static ResultDialogFragment newInstance() {
        return new ResultDialogFragment();
    }

    public ResultDialogFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext())
                .setTitle(R.string.title_message)
                .setMessage(R.string.message_message)
                .setPositiveButton(R.string.positive_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        App.getInstance().startActivity(new Intent(getActivity(), SnakeFiledActivity.class));
                    }
                })
                .setNegativeButton(R.string.negative_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        getActivity().finish();
                    }
                })
                .create();
    }
}