package com.m.samplelibb;

import android.content.Context;
import android.content.Intent;

public class SampleInitCase {

    public static void initStarFish(Context context){
        Intent i = new Intent(context,SearchActivity.class);
        context.startActivity(i);
    }

}
