package com.example.blablaplane.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ModifyProfile_dialogFragment extends DialogFragment {
    private static final String ARG_TITLE = "Titre";
    private static final String ARG_INSTRUCTION = "Instruction";
    private static final String ARG_INPUT_FIELD_TYPE = InputFieldType.FIRST_NAME.name();
    private InputDialogListener listenerInput;

    public enum InputFieldType {
        FIRST_NAME,
        LAST_NAME,
        EMAIL,
        PHONE_NUMBER,
        PASSWORD,
    }

    public interface InputDialogListener {
        View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

        void onTextEntered(String inputText, InputFieldType fieldType);
    }

    public static ModifyProfile_dialogFragment newInstance(String title, String instruction, InputFieldType inputFieldType) {
        ModifyProfile_dialogFragment fragment = new ModifyProfile_dialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_INSTRUCTION, instruction);
        args.putString(ARG_INPUT_FIELD_TYPE, inputFieldType.name());
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        String title = args.getString(ARG_TITLE);
        String instruction = args.getString(ARG_INSTRUCTION);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());

        builder.setTitle(title);
        builder.setMessage(instruction);
        final EditText inputEditText = new EditText(requireContext());
        final EditText inputRepeatPassword;

        builder.setView(inputEditText);

        if (ARG_INPUT_FIELD_TYPE.equals(InputFieldType.PASSWORD.name())) {
            inputRepeatPassword = new EditText(requireContext());
            builder.setView(inputRepeatPassword);
        } else {
            inputRepeatPassword = null;
        }

        builder.setPositiveButton("Confirmer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String userInput = inputEditText.getText().toString();

                if (ARG_INPUT_FIELD_TYPE.equals(InputFieldType.PASSWORD.name())) {
                    String repeatPassword = inputRepeatPassword.getText().toString();
                    if (!userInput.equals(repeatPassword)) {
                        Toast.makeText(requireContext(), "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }


                if (listenerInput != null) {
                    listenerInput.onTextEntered(userInput,InputFieldType.valueOf(ARG_INPUT_FIELD_TYPE));
                }

                dismiss(); // Fermer le fragment
            }
        });

        // Créez la fenêtre contextuelle AlertDialog à partir du AlertDialog.Builder
        return builder.create();
    }
}