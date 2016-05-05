package com.channelsoft.android.ggsj.view.loading;


/**
 * Created by ybq.
 */
public class SpriteFactory
{

    public static Sprite create(Style style) {
        Sprite sprite = null;
        switch (style) {
            case CHASING_DOTS:
                sprite = new ChasingDots();
                break;
            case THREE_BOUNCE:
                sprite = new ThreeBounce();
                break;
        }
        return sprite;
    }
}
