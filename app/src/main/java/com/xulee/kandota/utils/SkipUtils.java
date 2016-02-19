package com.xulee.kandota.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.xulee.kandota.act.youku.PlayerActivity;
import com.xulee.kandota.entity.Movie;

public class SkipUtils {

    public static void skipToMovieDetail(Context context, Movie book) {
        Bundle extra = new Bundle();
        extra.putParcelable(PlayerActivity.EXTRA_MOVIE, book);
        Intent intent = new Intent(context.getApplicationContext(), PlayerActivity.class);
        intent.putExtras(extra);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
