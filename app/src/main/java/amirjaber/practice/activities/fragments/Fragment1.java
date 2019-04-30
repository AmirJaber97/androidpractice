package amirjaber.practice.activities.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import amirjaber.practice.activities.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {

    private static final String ARG_TEXT = "argText";
    Fragment1Listener fragment1Listener;
    TextView textView;
    EditText etText;
    Button bSendToFrag;
    String text;

    public static Fragment1 newInstance(String text) {
        Fragment1 fragment1 = new Fragment1();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, text);
        fragment1.setArguments(args);
        return fragment1;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        init(view);
        if (getArguments() != null) {
            text = getArguments().getString(ARG_TEXT);
        }
        textView.setText(text);
        bSendToFrag.setOnClickListener(v -> {
            CharSequence input = etText.getText();
            fragment1Listener.onInput1Sent(input);
        });
        return view;
    }

    public void updateEditText(CharSequence newText) {
        etText.setText(newText);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Fragment1Listener) {
            fragment1Listener = (Fragment1Listener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement Fragment1Listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragment1Listener = null;
    }

    private void init(View view) {
        textView = view.findViewById(R.id.tv_text);
        etText = view.findViewById(R.id.et_text);
        bSendToFrag = view.findViewById(R.id.b_send_to_frag);
    }

    public interface Fragment1Listener {
        void onInput1Sent(CharSequence input);
    }
}
