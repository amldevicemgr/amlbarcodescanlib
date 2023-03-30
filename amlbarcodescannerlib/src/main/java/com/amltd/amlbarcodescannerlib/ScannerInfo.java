package com.amltd.amlbarcodescannerlib;

import android.os.Bundle;

import static com.amltd.amlbarcodescannerlib.Values.*;
import static com.amltd.amlbarcodescannerlib.Values.PROPERTY_BARCODE_ACTIONS_ENABLE;
import static com.amltd.amlbarcodescannerlib.Values.PROPERTY_NAME_BARCODE_FORMAT_FLAGS;
import static com.amltd.amlbarcodescannerlib.Values.PROPERTY_NAME_CONT_SCAN_MODE;
import static com.amltd.amlbarcodescannerlib.Values.PROPERTY_NAME_FLAGS;
import static com.amltd.amlbarcodescannerlib.Values.PROPERTY_NAME_KEYBOARD_WEDGE_MODE;
import static com.amltd.amlbarcodescannerlib.Values.PROPERTY_NAME_LED_FLASH_SCAN;
import static com.amltd.amlbarcodescannerlib.Values.PROPERTY_NAME_MANAGED_ENABLED;
import static com.amltd.amlbarcodescannerlib.Values.PROPERTY_NAME_PREFIX;
import static com.amltd.amlbarcodescannerlib.Values.PROPERTY_NAME_PRESENTATION_FLAGS;
import static com.amltd.amlbarcodescannerlib.Values.PROPERTY_NAME_SCANNER_ENABLED;
import static com.amltd.amlbarcodescannerlib.Values.PROPERTY_NAME_SCANNER_MODE_FLAGS;
import static com.amltd.amlbarcodescannerlib.Values.PROPERTY_NAME_SCAN_KEY_EVENT;
import static com.amltd.amlbarcodescannerlib.Values.PROPERTY_NAME_SOUND;
import static com.amltd.amlbarcodescannerlib.Values.PROPERTY_NAME_SUFFIX;

/**
 * Example class/helper for setting up a Bundle to send to the scanner service.
 *
 * This class may not be maintained and is provided as sample code to demonstrate usage.
 */
public class ScannerInfo {

    protected Bundle mBundle;
    public Bundle getBundle() { return mBundle; }

    public ScannerInfo() {
        mBundle = new Bundle();
    }
    public ScannerInfo(Bundle bundle) {
        mBundle = bundle;
    }

    /* Get methods */

    // Use nullable Integer, Long, Boolean types so we get "null" back when there is no mapping.
    // (Instead of value types "0" or "false" for no mapping when using "getInt" etc.)
    // Null is treated like the "not set" value.

    public String getPrefix() {
        return mBundle.getString(PROPERTY_NAME_PREFIX);
    }
    public String getSuffix() {
        return mBundle.getString(PROPERTY_NAME_SUFFIX);
    }
    public String getSound() { return  mBundle.getString(PROPERTY_NAME_SOUND); }
    public Integer getKeyboardWedgeMode() {
        return (Integer)mBundle.get(PROPERTY_NAME_KEYBOARD_WEDGE_MODE);
    }
    public Boolean getScannerEnabled() {
        return (Boolean)mBundle.get(PROPERTY_NAME_SCANNER_ENABLED);
    }
    public Boolean getBarcodeActionsEnable(){
        return (Boolean)mBundle.get(PROPERTY_BARCODE_ACTIONS_ENABLE);
    }
    public Boolean getContScanMode() {
        return (Boolean)mBundle.get(PROPERTY_NAME_CONT_SCAN_MODE);
    }
    public Boolean getLEDFlashScanMode() { return (Boolean)mBundle.get(PROPERTY_NAME_LED_FLASH_SCAN); }
    public Boolean getScanKeyEventMode() {
        try {
            return (Boolean) mBundle.get(PROPERTY_NAME_SCAN_KEY_EVENT);
        } catch (Exception e){
            return false;
        }
    }
    public Boolean getManagedEnabled() {
        return (Boolean)mBundle.get(PROPERTY_NAME_MANAGED_ENABLED);
    }
    public Long getHideKeyboard() {
        return (Long)mBundle.get(PROPERTY_NAME_FLAGS);
    }
    public Long getPresentationMode() {
        return (Long)mBundle.get(PROPERTY_NAME_PRESENTATION_FLAGS);
    }
    public Long getScannerModeFlags() {
        return (Long)mBundle.get(PROPERTY_NAME_SCANNER_MODE_FLAGS);
    }
    public Long getBarcodeFormatFlags() {
        return (Long)mBundle.get(PROPERTY_NAME_BARCODE_FORMAT_FLAGS);
    }


    /* Set methods */

    public com.amltd.amlbarcodescannerlib.ScannerInfo setPrefix(String prefix) {
        if (prefix != null) mBundle.putString(PROPERTY_NAME_PREFIX, prefix);
        return this;
    }
    public com.amltd.amlbarcodescannerlib.ScannerInfo setSound(String soundUrl) {
        if (soundUrl != null) mBundle.putString(PROPERTY_NAME_SOUND, soundUrl);
        return this;
    }
    public com.amltd.amlbarcodescannerlib.ScannerInfo setSuffix(String suffix) {
        if (suffix != null) mBundle.putString(PROPERTY_NAME_SUFFIX, suffix);
        return this;
    }
    public com.amltd.amlbarcodescannerlib.ScannerInfo setKeyboardWedgeMode(int wedge) {
        mBundle.putInt(PROPERTY_NAME_KEYBOARD_WEDGE_MODE, wedge);
        return this;
    }
    public com.amltd.amlbarcodescannerlib.ScannerInfo setScannerEnabled(boolean enabled) {
        mBundle.putBoolean(PROPERTY_NAME_SCANNER_ENABLED, enabled);
        return this;
    }
    public com.amltd.amlbarcodescannerlib.ScannerInfo setBarcodeActionsEnable(boolean enabled){
        mBundle.putBoolean(PROPERTY_BARCODE_ACTIONS_ENABLE, enabled);
        return this;
    }
    public com.amltd.amlbarcodescannerlib.ScannerInfo setContScanMode(boolean enabled){
        mBundle.putBoolean(PROPERTY_NAME_CONT_SCAN_MODE, enabled);
        return this;
    }
    public com.amltd.amlbarcodescannerlib.ScannerInfo setLEDFlashScanMode(boolean enabled){
        mBundle.putBoolean(PROPERTY_NAME_LED_FLASH_SCAN, enabled);
        return this;
    }
    public com.amltd.amlbarcodescannerlib.ScannerInfo setScanKeyEventMode(boolean enabled){
        mBundle.putBoolean(PROPERTY_NAME_SCAN_KEY_EVENT, enabled);
        return this;
    }
    public com.amltd.amlbarcodescannerlib.ScannerInfo setManagedEnabled(boolean enabled) {
        mBundle.putBoolean(PROPERTY_NAME_MANAGED_ENABLED, enabled);
        return this;
    }
    public com.amltd.amlbarcodescannerlib.ScannerInfo setHideKeyboard(long flags) {
        mBundle.putLong(PROPERTY_NAME_FLAGS, flags);
        return this;
    }
    public com.amltd.amlbarcodescannerlib.ScannerInfo setPresentationMode(long flags) {
        mBundle.putLong(PROPERTY_NAME_PRESENTATION_FLAGS, flags);
        return this;
    }
    public com.amltd.amlbarcodescannerlib.ScannerInfo setScannerModeFlags(long flags) {
        mBundle.putLong(PROPERTY_NAME_SCANNER_MODE_FLAGS, flags);
        return this;
    }
    public com.amltd.amlbarcodescannerlib.ScannerInfo setBarcodeFormatFlags(long flags) {
        mBundle.putLong(PROPERTY_NAME_BARCODE_FORMAT_FLAGS, flags);
        return this;
    }
}

