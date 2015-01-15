package com.andrewpham.android.khanacademy_learnanything.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;

import com.andrewpham.android.khanacademy_learnanything.controllers.TopicFragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by andrewpham on 1/14/15.
 */
public class DownloadService extends IntentService {
    public static final String TAG = "DownloadService";

    Handler mHandler;

    public DownloadService() {
        super(TAG);
        mHandler = new Handler();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressWarnings("deprecation")
        boolean isNetworkAvailable = cm.getBackgroundDataSetting() &&
                cm.getActiveNetworkInfo() != null;
        if (!isNetworkAvailable) return;

        String s = intent.getStringExtra(TopicFragment.EXTRA_URL);
        String filename = intent.getStringExtra(TopicFragment.EXTRA_TITLE);

        download(s, filename);
    }

    private void download(String s, String filename) {
        try {
            mHandler.post(new DisplayToast(this, "Downloading file..."));

            URL url = new URL(s);
            URLConnection connection = url.openConnection();
            connection.connect();

            int size = connection.getContentLength();
            Log.v(TAG, "File size: " + size);

            InputStream in = url.openStream();
            File f = getOutputMediaFile(filename);
            FileOutputStream fos = new FileOutputStream(f);

            byte buffer[] = new byte[1024];
            int bytesRead;
            long totalBytesRead = 0;
            int progress = 0;

            while ((bytesRead = in.read(buffer)) > 0) {
                totalBytesRead += bytesRead;
                int tmp = (int) totalBytesRead * 100 / size;
                if (tmp % 10 == 0 && progress != tmp) {
                    progress = tmp;
                    Log.v(TAG, "Downloading " + progress + "%");
                }
                fos.write(buffer, 0, bytesRead);
            }

            in.close();
            fos.close();

            mHandler.post(new DisplayToast(this, "Download complete."));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private File getOutputMediaFile(String filename) {
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + this.getPackageName()
                + "/files");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(TAG, "Failed to create directory: ");
            }
        }

        // Create a media file name
        return new File(mediaStorageDir.getPath() + File.separator + filename + ".mp4");
    }
}