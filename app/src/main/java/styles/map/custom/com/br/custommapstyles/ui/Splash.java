package styles.map.custom.com.br.custommapstyles.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import styles.map.custom.com.br.custommapstyles.R;

public class Splash extends Activity {
    
    private Class[] paramTypes = { Integer.TYPE, Integer.TYPE };
    private Method overrideAnimation = null;
    public static final Integer SPLASH_DURATION = 2100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        try {
            Class<?> activityClass = Class.forName("android.app.Activity");
            overrideAnimation = activityClass.getDeclaredMethod(
                    "overridePendingTransition", paramTypes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this,
                        MapsActivity.class);
                startActivity(i);
                finish();
                if (overrideAnimation != null) {
                    try {
                        overrideAnimation.invoke(Splash.this, android.R.anim.fade_in,
                                android.R.anim.slide_out_right);
                    } catch (IllegalArgumentException e) {
                        Log.v("IllegalArgumentExp: ", e.getMessage());
                    } catch (IllegalAccessException e) {
                        Log.v("IllegalAccessExp: ", e.getMessage());
                    } catch (InvocationTargetException e) {
                        Log.v("InvocationTargetExp: ", e.getMessage());
                    }
                }
            }
        }, SPLASH_DURATION);
    }
}
