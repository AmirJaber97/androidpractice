package amirjaber.practice.activities.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import amirjaber.practice.activities.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import static android.app.Activity.RESULT_OK;

public class SampleDialog extends DialogFragment {

    private static final String EXTRA_USERNAME = "username";
    private static final String EXTRA_PASSWORD = "password";

    private EditText editUsername, editPassword;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_layout, null);
        builder.setView(view)
                .setTitle("Login")
                .setNegativeButton("cancel", (dialog, which) -> {

                })
                .setPositiveButton("ok", (dialog, which) -> {
                    String username = editUsername.getText().toString();
                    String password = editPassword.getText().toString();
                    passData(username, password);
                });
        editUsername = view.findViewById(R.id.edit_username);
        editPassword = view.findViewById(R.id.edit_password);

        return builder.create();

    }

    private void passData(String username, String password) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_USERNAME, username);
        intent.putExtra(EXTRA_PASSWORD, password);
        getTargetFragment().onActivityResult(getTargetRequestCode(), RESULT_OK, intent);
        dismiss();

    }

}
