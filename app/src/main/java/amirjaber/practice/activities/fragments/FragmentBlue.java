package amirjaber.practice.activities.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import amirjaber.practice.activities.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentBlue extends Fragment {

    FragmentBlueListener fragmentBlueListener;
    EditText etText;
    Button bSendToFrag;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blue, container, false);
        init(view);
        bSendToFrag.setOnClickListener(v -> {
            CharSequence input = etText.getText();
            fragmentBlueListener.onInputBlueSent(input);
        });
        return view;
    }

    private void init(View view) {
        etText = view.findViewById(R.id.et_text);
        bSendToFrag = view.findViewById(R.id.b_send_to_frag);
    }

    public void updateEditText(CharSequence newText) {
        etText.setText(newText);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentBlue.FragmentBlueListener) {
            fragmentBlueListener = (FragmentBlue.FragmentBlueListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement FragmentBlueListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentBlueListener = null;
    }

    public interface FragmentBlueListener {
        void onInputBlueSent(CharSequence input);
    }
}
