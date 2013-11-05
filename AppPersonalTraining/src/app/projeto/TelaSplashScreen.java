package app.projeto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

/**
 *
 * @author Guilherme Gehling
 */
public class TelaSplashScreen extends Activity {

    private static String TAG = TelaSplashScreen.class.getName();
    private static long SLEEP_TIME = 5;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.telasplashscreen);
        // Removes title bar
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Removes notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        IntentLauncher launcher = new IntentLauncher();
        launcher.start();
    }

    private class IntentLauncher extends Thread {

        @Override
        /**
         * Sleep for some time and than start new activity.
         */
        public void run() {
            try {
                Thread.sleep(SLEEP_TIME * 1000);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
            Intent intent = new Intent(TelaSplashScreen.this, TelaLogin.class);
            TelaSplashScreen.this.startActivity(intent);
            TelaSplashScreen.this.finish();
        }
    }
}