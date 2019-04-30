package amirjaber.practice.activities.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import amirjaber.practice.activities.R;
import amirjaber.practice.activities.dialogs.SampleDialog;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.app.Activity.RESULT_OK;

public class FragmentHomeNav extends Fragment {

    private static final int TARGET_FRAGMENT_REQUEST_CODE = 4;
    private static final String EXTRA_USERNAME = "username";
    private static final String EXTRA_PASSWORD = "password";

    private TextView username, password;
    private Button bOpenDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_nav, container, false);
        init(view);
        return view;

    }

    private void init(View view) {
        username = view.findViewById(R.id.username_text);
        password = view.findViewById(R.id.password_text);
        bOpenDialog = view.findViewById(R.id.open_dialog);

        bOpenDialog.setOnClickListener(v -> {
            openDialog();
        });
    }

    private void openDialog() {
        SampleDialog dialog = new SampleDialog();
        dialog.setTargetFragment(FragmentHomeNav.this, TARGET_FRAGMENT_REQUEST_CODE);
        dialog.show(getActivity().getSupportFragmentManager(), "Sample Dialog");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == TARGET_FRAGMENT_REQUEST_CODE) {
            String username = data.getStringExtra(EXTRA_USERNAME);
            String password = data.getStringExtra(EXTRA_PASSWORD);
            this.username.setText(username);
            this.password.setText(password);
        }
    }
}
