package com.ygames.ysoccer.screens;

import com.ygames.ysoccer.framework.Assets;
import com.ygames.ysoccer.framework.Font;
import com.ygames.ysoccer.framework.GlGame;
import com.ygames.ysoccer.framework.GlScreen;
import com.ygames.ysoccer.gui.Button;
import com.ygames.ysoccer.gui.Widget;

public class PlayLeague extends GlScreen {

    public PlayLeague(GlGame game) {
        super(game);

        background = game.stateBackground;

        Widget w;
        w = new TitleBar();
        widgets.add(w);
    }

    class TitleBar extends Button {

        public TitleBar() {
            setGeometry((game.settings.GUI_WIDTH - 520) / 2, 30, 520, 40);
            setColors(0x415600, 0x5E7D00, 0x243000);
            setText(game.competition.getMenuTitle().toUpperCase(), Font.Align.CENTER, Assets.font14);
            setActive(false);
        }
    }
}
