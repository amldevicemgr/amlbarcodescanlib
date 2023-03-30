package com.amltd.amlbarcodescannerlib;

/*
    These values can be used along with the Android SDK to interface with the AML scanner service.

    Essentially an Android "Bundle" will contain values for scanner settings, and the Bundle is
    passed to/from the scanner service using Android "Intents".

    For a suggestion on how to handle this behavior, see the classes "BarcodeScanner" and
    "ScannerInfo".  They are helper/wrapper classes that offer a way to simplify and handle Bundles,
    Intents, and sending/receiving data from a Service.

    NOTE: "FLAG" values can be used in combination with bitwise 'or' (char = '|') to set more than
    one value.
 */

public class Values {
    //Barcode service package/class strings for Intent
    public static final String PACKAGE_NAME = "com.amltd.amlbarcodescanner";
    public static final String CLASS_NAME = PACKAGE_NAME + ".BarcodeScannerService";

    //Incoming Intent action strings:
    //
    // Action string received if the incoming data is an error message.
    public static final String ACTION_ERROR = PACKAGE_NAME + ".ERROR";
    // Action string received if the incoming data is a barcode.
    public static final String ACTION_SCANNED = PACKAGE_NAME + ".SCANNED";
    // Action string received if the trigger is pulled (no data).
    public static final String ACTION_TRIGGER_PULLED = ".TRIGGER_PULLED";
    // Action string received if the trigger is released (no data).
    public static final String ACTION_TRIGGER_RELEASED = ".TRIGGER_RELEASED";
    // The key name to use when getting barcode or error message string from the Intent's extras.
    public static final String EXTRA_DATA = "d";

    //Outgoing Intent action strings:
    //
    // Set this action to programmatically release scan trigger
    public static final String ACTION_KEY_SCAN_UP = "key.scan.up";
    // Set this action to programmatically pull scan trigger
    public static final String ACTION_KEY_SCAN_DOWN = "key.scan.down";

    // Set this action to programmatically show the virtual keyboard
    public static final String ACTION_SHOW_IME = PACKAGE_NAME + ".SHOW_IME";
    // Set this action to programmatically hide the virtual keyboard
    public static final String ACTION_HIDE_IME = PACKAGE_NAME + ".HIDE_IME";

    //////////////////////////////////////////////////////////////////
    //                                                              //
    //  Key name strings for scanner configuration/settings below.  //
    //                                                              //
    //////////////////////////////////////////////////////////////////

    // Each string is a key name for a corresponding value, that should be set to a Bundle.
    // The Bundle should be passed to the service using an Intent.

    // NOTE: These settings are ONLY managed in the scanner service.
    // They are not programmed into the scan engine hardware.

    //Key name for enabling/disabling the scanner.
    // Requires a Boolean value type. True for on, false for off.
    public static final String PROPERTY_NAME_SCANNER_ENABLED = "Enabled";

    //Key name for enabling/disabling wifi, url, and bluetooth scanning.
    //Requires a Boolean value type. True for on, false for off.
    public static final String PROPERTY_BARCODE_ACTIONS_ENABLE = "Enable";


    //Key name for the two ways the scanner service can output a barcode.
    // Requires Long value type. This is a FLAG value type - one, all, or none of the flag values
    // can be set. Set zero '0' for none.
    public static final String PROPERTY_NAME_SCANNER_MODE_FLAGS = "Mode";
    //For PROPERTY_NAME_SCANNER_MODE_FLAGS:
    // Scanner service will auto type barcode into focused field for the activity displayed.
    public static final int FLAG_SCANNER_MODE_KEYBOARD_WEDGE  = 1;
    // Scanner service will broadcast intents (keep this mode set to receive events).
    // Set this mode to receive ACTION_SCANNED, ACTION_TRIGGER_PULLED, and ACTION_ERROR
    public static final int FLAG_SCANNER_MODE_INTENT          = 1 << 1;
    public static final int FLAG_SCANNER_MODE_BARCODE_ACTIONS = 1;

    //Key name for the two ways the Keyboard Wedge can operated (if wedge is enabled).
    // Requires Int value type.
    public static final String PROPERTY_NAME_KEYBOARD_WEDGE_MODE = "Wedge";
    //For PROPERTY_NAME_KEYBOARD_WEDGE_MODE:
    // Whole barcode string is set directly to focused field.
    public static final int KEYBOARD_WEDGE_MODE_FAST_INPUT = 0;
    // Each barcode character is typed as a virtual key press.
    public static final int KEYBOARD_WEDGE_MODE_TRUE_KEY_PRESS = 1;

    //Key names for Prefix and Suffix of barcode.
    // Requires String value type.
    // The string set for this value will be inserted at the beginning of the barcode.
    // To use non-printable ASCII characters in the prefix, use the following format:
    // \NN - where NN is the double digit hex number representation of the character.
    // Example: "\\02" (STX) double backslash is required to escape the backslash.
    public static final String PROPERTY_NAME_PREFIX = "Prefix";
    // Requires String value type.
    // The string set for this value will be appended to the end of the barcode.
    // Same prefix ASCII character rules apply to suffix.
    public static final String PROPERTY_NAME_SUFFIX = "Suffix";

    //Key name for Url path to ringtone that will play after a barcode is scanned.
    // Requires String value type.
    // The String must be a Url path for a ringtone on the device.
    public static final String PROPERTY_NAME_SOUND = "SoundPath";



    // NOTE: The settings bellow are NOT ONLY managed in the scanner service, they also effect the
    // scan engine hardware. If these settings are used, then any manually scanned configuration
    // barcodes used, to program the scan engine, are overwritten.
    // IMPORTANT: To used these settings, the "Managed" setting must be set to true, otherwise set
    // to false to have all these settings ignored and, as a result, enable the scan engine to be
    // configured manually with barcodes.

    //Key name for allowing scanner service to manage special settings that would otherwise be
    // handled by configuring the scan engine manually.
    // Requires Boolean. Set true to allow service to manage these settings, otherwise false.
    public static final String PROPERTY_NAME_MANAGED_ENABLED = "Managed";

    //Key name for setting barcode formats to apply on outgoing barcode data.
    // Requires Long value type. This is a FLAG value type.
    public static final String PROPERTY_NAME_BARCODE_FORMAT_FLAGS = "BarcodeFormat";
    //For PROPERTY_NAME_BARCODE_FORMAT_FLAGS:
    // The AIM standard 3 character length code will be inserted at the beginning of the barcode.
    // If there is a custom prefix already set, the AIM ID will be appended to the end of the
    // custom prefix but before the barcode string.
    public static final int FLAG_BARCODE_FORMAT_AIM_ID = 1;
    //Key name for enabling/disabling scan key event mode
    public static final String PROPERTY_NAME_SCAN_KEY_EVENT = "ScanKeyEvent";
    //Key name for enabling/disabling continuous scan mode for EX30 and some Zebra engines
    public static final String PROPERTY_NAME_CONT_SCAN_MODE = "ContScanMode";
    //Key name for enabling/disabling LED light flash on scan for the FIREBIRD model only
    public static final String PROPERTY_NAME_LED_FLASH_SCAN = "LEDFlashScan";

    //Key name for setting kiosk presentation mode for FIREBIRD model only
    public static final String PROPERTY_NAME_PRESENTATION_FLAGS = "PresentationMode";

    public static final long FLAG_SCANNER_OP_NORMAL_PRESENTATION = 2;
    public static final long FLAG_SCANNER_OP_MANUAL_TRIGGER = 0;
    public static long FLAG_SCANNER_OP_MOBILE_PRESENTATION = 3;

    //Used for hiding keyboard on scan
    public static final String PROPERTY_NAME_FLAGS = "Flags";
    public static final int FLAG_HIDE_IME_ON_SCAN = 1;

}
