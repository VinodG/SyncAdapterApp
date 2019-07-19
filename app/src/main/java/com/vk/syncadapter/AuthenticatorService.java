package com.vk.syncadapter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * This is used only by Android to run our {@link AccountAuthenticator}.
 */
public class AuthenticatorService extends Service {
    private AccountAuthenticator authenticator;


    @Override
    public void onCreate() {
        // Instantiate our authenticator when the service is created
        this.authenticator = new AccountAuthenticator(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Return the authenticator's IBinder
        return authenticator.getIBinder();
    }
}
