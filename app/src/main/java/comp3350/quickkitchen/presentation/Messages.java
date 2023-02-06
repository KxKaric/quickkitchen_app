package comp3350.quickkitchen.presentation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class Messages {
    public static void fatalError(final Activity owner, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(owner).create();

        alertDialog.setTitle("fatal error");
        alertDialog.setMessage(message);
        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
            	owner.finish();
            }
        });

        alertDialog.show();
    }

    public static void warning(Activity owner, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(owner).create();

        alertDialog.setTitle("Warning");
        alertDialog.setMessage(message);

        alertDialog.show();
    }
}
