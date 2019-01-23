package developer.android.com.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("mS75PlxcjYU4EoNKX8sTzIRozlHLb5FOJqeycKrg")
                // if defined
                .clientKey("dl7DDCeEJIUePu6LagCdnEePi95zIRfLwZQmcoNl")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
