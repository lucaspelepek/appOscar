package com.example.apposcarverso2.dialogs;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class AlertDialog extends AppCompatDialogFragment {

    private String resultado;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        if (getArguments() != null) {
            resultado = getArguments().getString("resultado");
        }

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
        builder.setTitle("Votos")
                .setMessage(resultado)
                .setPositiveButton("Ok", (dialog, which) -> {
                    if (!resultado.matches("Token invalido")) {
                        getActivity().finish();
                    }
                });

        return builder.create();
    }
}
