package com.amltd.amlbarcodescannerlib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.ResultReceiver;
import android.util.Log;

import static com.amltd.amlbarcodescannerlib.Values.*;
import static com.amltd.amlbarcodescannerlib.Values.ACTION_ERROR;
import static com.amltd.amlbarcodescannerlib.Values.ACTION_KEY_SCAN_DOWN;
import static com.amltd.amlbarcodescannerlib.Values.ACTION_KEY_SCAN_UP;
import static com.amltd.amlbarcodescannerlib.Values.ACTION_SCANNED;
import static com.amltd.amlbarcodescannerlib.Values.ACTION_TRIGGER_PULLED;
import static com.amltd.amlbarcodescannerlib.Values.ACTION_TRIGGER_RELEASED;
import static com.amltd.amlbarcodescannerlib.Values.CLASS_NAME;
import static com.amltd.amlbarcodescannerlib.Values.EXTRA_DATA;
import static com.amltd.amlbarcodescannerlib.Values.PACKAGE_NAME;

/**
 * Example class/helper that uses BarcodeScannerService Intents
 *
 * This class might not be maintained and is provided as sample code to demonstrate usage.
 */

public class BarcodeScanner {
    private Context mContext;
    //For receiving broadcast intents
    private BroadcastReceiver mReceiver;

    //For incoming scanned action
    public interface OnScannedListener { void onScanned(String barcode); }
    private OnScannedListener mOnScannedListener;
    public void setOnScannedListener(OnScannedListener listener) { mOnScannedListener = listener; }

    //For incoming error action
    public interface OnErrorListener { void onError(Exception e); }
    private OnErrorListener mOnErrorListener;
    public void setOnErrorListener(OnErrorListener listener) { mOnErrorListener = listener; }

    //For incoming trigger pulled action
    public interface OnTriggerPulledListener { void onTriggerPulled(); }
    private OnTriggerPulledListener mOnTriggerPulledListener;
    public void setOnTriggerPulledListener(OnTriggerPulledListener listener) { mOnTriggerPulledListener = listener; }

    //For incoming trigger pulled action
    public interface OnTriggerReleasedListener { void onTriggerReleased(); }
    private OnTriggerReleasedListener mOnTriggerReleasedListener;
    public void setOnTriggerReleasedListener(OnTriggerReleasedListener listener) { mOnTriggerReleasedListener = listener; }

    //Interface for asynchronous callbacks
    public interface OnReceiveSettings { void onReceive(Bundle bundle); }

    /**
     * Constructor
     * @param context
     * @throws Exception
     */
    public BarcodeScanner(Context context) throws Exception {
        if (context == null)
            throw new Exception("BarcodeScanner - Context cannot be null.");
        mContext = context;
    }
    /**
     * Register to listen for BarcodeScannerService intents
     */
    public void register() {
        if (mReceiver != null) return;

        //The receiver must be registered with an intent filter.
        // Using an intent filter with the scanned action will tell Android to
        // send intents with the scanned action to this receiver.
        IntentFilter filter = new IntentFilter();
        //Add this if we want an intent with the barcode returned to us after a successful scan.
        filter.addAction(ACTION_SCANNED);
        //Add this if we want an intent with error messages returned to us.
        filter.addAction(ACTION_ERROR);
        //filter.addAction("com.amltd.amlbarcodescanner.CHANGE_IMAGE_QUALITY");
        //Add this if we want an intent returned to us when the trigger is pulled.
        // This is only an indication of when the trigger is pulled.
        // It does not mean there was a barcode scanned.
        // The intent holds no data other than the action string.
        filter.addAction(ACTION_TRIGGER_PULLED);
        filter.addAction(ACTION_TRIGGER_RELEASED);

        //This broadcast receiver will handle incoming intents from the scanner service.
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case ACTION_SCANNED:
                        String barcode = intent.getStringExtra(EXTRA_DATA);
                        if (mOnScannedListener != null)
                            mOnScannedListener.onScanned(barcode);
                        break;

                    case ACTION_ERROR:
                        String errMsg = intent.getStringExtra(EXTRA_DATA);
                        if (mOnErrorListener != null)
                            mOnErrorListener.onError(new Exception(errMsg));
                        break;

                    case ACTION_TRIGGER_PULLED:
                        if (mOnTriggerPulledListener != null)
                            mOnTriggerPulledListener.onTriggerPulled();
                        break;
                    case ACTION_TRIGGER_RELEASED:
                        if (mOnTriggerReleasedListener != null)
                            mOnTriggerReleasedListener.onTriggerReleased();
                        break;
                }
            }
        };

        //Register!
        mContext.registerReceiver(mReceiver, filter);
        startService();
    }
    /**
     * Unregister to stop listening to intents
     */
    public void unregister() {
        if (mReceiver != null) {
            BroadcastReceiver br = mReceiver;
            mReceiver = null;
            //Make sure to unregister when we no longer need to listen to the scanner service.
            mContext.unregisterReceiver(br);
        }
    }
    /**
     * Send a correctly setup Bundle to BarcodeScannerService to be processed in order to
     * change the scanner behavior.
     * @param bundle
     */
    public void changeSettings(Bundle bundle) {
        changeSettings(bundle, null);
    }
    /**
     * Like changeSettings(Bundle bundle) but will asynchronously return the current scanner
     * settings, in a Bundle, after processing the incoming Bundle.
     * @param bundle
     * @param receiver
     */
    public void changeSettings(Bundle bundle, final OnReceiveSettings receiver) {
        Log.d("New Engine", "New Engine Barcode Scanner Change Settings");
        if (receiver != null) {
            if (bundle == null) bundle = new Bundle();
            //Get the current scanner settings after we change the settings
            ResultReceiver resultReceiver = new ResultReceiver(new Handler()) {
                @Override
                protected void onReceiveResult(int resultCode, Bundle resultData) {
                    receiver.onReceive(resultData);
                }
            };
            //If you change the package name of this file use this before adding to the bundle
            if (!"com.amltd.barcodelib.sdk.BarcodeScanner".equals(getClass().getCanonicalName())) {
                Parcel p = Parcel.obtain();
                resultReceiver.writeToParcel(p, 0);
                p.setDataPosition(0);
                resultReceiver = ResultReceiver.CREATOR.createFromParcel(p);
                p.recycle();
            }
            //Add the result receiver to the bundle, then the bundle to the intent.
            bundle.putParcelable(Intent.EXTRA_RESULT_RECEIVER, resultReceiver);
        }

        Intent intent = new Intent();
        intent.putExtras(bundle);
        //Calling startService and passing in our intent is how we send the intent to the
        // scanner service for processing.
        startService(intent);
    }

    /**
     * Request a Bundle with the current scanner settings.
     * @param receiver
     */
    public void requestSettings(OnReceiveSettings receiver) {
        changeSettings(null, receiver);
    }

    /**
     * Programmatically pull(on) the scanner trigger, or release(off).
     * @param on - true for on, otherwise false.
     */
    public void trigger(boolean on) {
        startService(new Intent(on ? ACTION_KEY_SCAN_DOWN : ACTION_KEY_SCAN_UP));
    }

    /* MISC */
    private void startService() {
        startService(null);
    }
    private void startService(Intent intent) {
        if (intent == null) intent = new Intent();
        intent.setClassName(PACKAGE_NAME, CLASS_NAME);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mContext.startForegroundService(intent);
        } else {
            mContext.startService(intent);
        }
    }
}

