package com.fourglabs.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;

public class PermissionUtils {
	public static boolean checkAndRequestPermission(final Context context,
			final Activity activity, final String permission,
			final int appConstant, final String lackPermsMsg,
			String additionalPermissionMessage) {
		boolean ret = true;
		if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
			ret = false;
			// Should we show an explanation?
			if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
					permission)) {
				AlertDialog.Builder builder = new AlertDialog.Builder(activity);
				builder.setTitle(AppConstants.MSG_REQ_PERMS);
				builder.setMessage(additionalPermissionMessage);
				// Add the buttons
				builder.setPositiveButton(AppConstants.OK,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								ActivityCompat.requestPermissions(activity,
										new String[] { permission },
										appConstant);
							}
						});
				builder.setNegativeButton(AppConstants.CANCEL,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								ToastUtils.showToast(context, lackPermsMsg);
							}
						});

				// Create the AlertDialog
				AlertDialog dialog = builder.create();
				dialog.show();
			} else {
				// No explanation needed, we can request the permission.
				ActivityCompat.requestPermissions(activity,
						new String[] { permission }, appConstant);
				// MY_PERMISSIONS_REQUEST_CALL_PHONE is an
				// app-defined int constant. The callback method gets the
				// result of the request.
			}
		}
		return ret;
	}

	private static boolean showRequestPermissionRationale(Activity activity,
			String[] permissions) {
		boolean ret = false;
		for (String permission : permissions) {
			if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
					permission)) {
				ret = true;
			}
		}
		return ret;
	}

	private static boolean checkSelfPermissions(Context context,
			String[] permissions) {
		boolean ret = false;
		for (String permission : permissions) {
			if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
				ret = true;
			}
		}
		return ret;
	}

	public static boolean checkAndRequestPermissions(final Context context,
			final Activity activity, final String[] permissions,
			final int appConstant, final String lackPermsMsg,
			String additionalPermissionMessage) {
		boolean ret = true;
		if (checkSelfPermissions(context, permissions) == true) {
			ret = false;
			// Should we show an explanation?
			if (showRequestPermissionRationale(activity, permissions) == true) {
				AlertDialog.Builder builder = new AlertDialog.Builder(activity);
				builder.setTitle(AppConstants.MSG_REQ_PERMS);
				builder.setMessage(additionalPermissionMessage);
				// Add the buttons
				builder.setPositiveButton(AppConstants.OK,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								ActivityCompat.requestPermissions(activity,
										permissions, appConstant);
							}
						});
				builder.setNegativeButton(AppConstants.CANCEL,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								ToastUtils.showToast(context, lackPermsMsg);
							}
						});

				// Create the AlertDialog
				AlertDialog dialog = builder.create();
				dialog.show();
			} else {
				// No explanation needed, we can request the permission.
				ActivityCompat.requestPermissions(activity, permissions,
						appConstant);
				// The callback method gets the result of the request.
			}
		}
		return ret;
	}
}
