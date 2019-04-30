package amirjaber.practice.activities.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

import amirjaber.practice.activities.R;
import androidx.fragment.app.Fragment;


public class Fragment2 extends Fragment {
    private static final String TEXT = "text";

    private String mText;
    private EditText etPassedText;
    private Button bPass, bBack;

    private OnFragmentInteractionListener mListener;

    public Fragment2() {
        // Required empty public constructor
    }

    public static Fragment2 newInstance(String text) {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();
        args.putString(TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mText = getArguments().getString(TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);
        etPassedText = view.findViewById(R.id.et_passed_text);
        bPass = view.findViewById(R.id.b_pass);
        bBack = view.findViewById(R.id.b_back);
        etPassedText.setText(mText);
        etPassedText.requestFocus();
        initListeners();
        return view;
    }

    private void initListeners() {
        bPass.setOnClickListener(v -> {
            String sendBackText = etPassedText.getText().toString();
            sendBack(sendBackText);
        });
        bBack.setOnClickListener(v -> {
            Objects.requireNonNull(getActivity()).onBackPressed();
        });
    }


    public void sendBack(String text) {
        if (mListener != null) {
            mListener.onFragmentInteraction(text);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String sendBackText);
    }
}
