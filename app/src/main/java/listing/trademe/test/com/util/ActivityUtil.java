package listing.trademe.test.com.util;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by Letícia on 21/02/2018.
 */

public class ActivityUtil {

    public static void replaceFragmentToActivityAddBackStack(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int frameId) {
        fragmentManager.beginTransaction().replace(frameId, fragment).addToBackStack(null).commit();
    }

    public static void replaceFragmentToActivity(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int frameId) {
        fragmentManager.beginTransaction().replace(frameId, fragment).commit();
    }
}
