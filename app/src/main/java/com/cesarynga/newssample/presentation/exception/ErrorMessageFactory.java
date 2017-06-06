package com.cesarynga.newssample.presentation.exception;

import android.content.Context;

import com.cesarynga.newssample.R;
import com.cesarynga.newssample.data.exception.NetworkConnectionException;

public class ErrorMessageFactory {

    private ErrorMessageFactory() {
    }

    public static String create(Context context, Exception exception) {
        String message = context.getString(R.string.exception_message_generic);

        if (exception instanceof NetworkConnectionException) {
            message = context.getString(R.string.exception_message_no_connection);
        }

        return message;
    }
}
