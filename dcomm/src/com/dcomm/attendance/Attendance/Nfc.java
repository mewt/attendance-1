package com.dcomm.attendance.Attendance;
import android.nfc.Tag;
import android.nfc.tech.MifareUltralight;
import android.util.Log;
import java.io.IOException;
import java.nio.charset.Charset;
import android.nfc.*;

import static android.content.Intent.getIntent;

/**
 * Created with IntelliJ IDEA.
 * User: Brandon
 * Date: 4/9/13
 * Time: 2:39 PM
 * To change this template use File | Settings | File Templates.
 */


public class Nfc {
      private User user;
      private String class_id;
      private String server_ip;
      private String server_name;
      public NfcAdapter adapter;
      public Tag detectedTag;

      private static final String TAG = Nfc.class.getSimpleName();

    public Nfc(NfcAdapter adapt)
    {
        adapter = adapt;

    }

    public void setClass_id(String id)
    {
        this.class_id = id;
    }

    public void setUser(User u)
    {
        this.user = u;
    }

    public String getClass_id()
    {
        return this.class_id;
    }

    public String getUserName()
    {
        return this.user.getName();
    }

    public String getUserId()
    {
        return this.user.get_eagle_id();
    }

    public String readTag(Tag tag) {
        MifareUltralight mifare = MifareUltralight.get(tag);
        try {
            mifare.connect();
            byte[] payload = mifare.readPages(4);
            return new String(payload, Charset.forName("US-ASCII"));
        } catch (IOException e) {
            Log.e(TAG, "IOException while writing MifareUltralight message...", e);
        } finally {
            if (mifare != null) {
                try {
                    mifare.close();
                }
                catch (IOException e) {
                    Log.e(TAG, "Error closing tag...", e);
                }
            }
        }
        return null;
    }
    //Method that will send the message to the server, encapsulate the networking shit in this class
    // So we can call this in the main activity and keep it clean over there but grab the nfc data there,
    // then parse it and store it in an instance of the class then poof. Send it out using this method.
    // We can probably specify a server and what not also here but not there yet.
    public void send_message()
    {

    }
}
