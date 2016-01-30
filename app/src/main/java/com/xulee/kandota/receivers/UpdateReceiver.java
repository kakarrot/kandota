package com.xulee.kandota.receivers;

import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import com.liuguangqiang.framework.utils.IntentUtils;
import com.xulee.kandota.constant.Constants;

import java.io.File;

/**
 * 版本更新，接收下载结束后的通知，并安装。
 */
public class UpdateReceiver extends BroadcastReceiver {

	private DownloadManager downloadManager;

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (action.equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
			long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
			if (id != Constants.UPDATE_DOWNLOAD_ID) {
				return;
			}
			Query query = new Query();
			query.setFilterById(id);
			downloadManager = (DownloadManager) context
					.getSystemService(Context.DOWNLOAD_SERVICE);

			Cursor cursor = downloadManager.query(query);
			int columnCount = cursor.getColumnCount();
			while (cursor.moveToNext()) {
				for (int j = 0; j < columnCount; j++) {
					String columnName = cursor.getColumnName(j);
					String string = cursor.getString(j);
					if (columnName.equals("local_uri")) {
					} else if (columnName.equals("local_filename")) {
						if (string != null) {
							IntentUtils.installApk(context, new File(string));
						}
					}
				}
			}
			cursor.close();
		}
	}

}
