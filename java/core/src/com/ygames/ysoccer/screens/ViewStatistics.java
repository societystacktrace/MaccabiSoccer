package com.ygames.ysoccer.screens;

import com.ygames.ysoccer.framework.Assets;
import com.ygames.ysoccer.framework.Font;
import com.ygames.ysoccer.framework.GlGame;
import com.ygames.ysoccer.framework.GlScreen;
import com.ygames.ysoccer.gui.Button;
import com.ygames.ysoccer.gui.Widget;

public class ViewStatistics extends GlScreen {

    public ViewStatistics(GlGame game) {
        super(game);

        background = game.stateBackground;

        Widget w;
        w = new TitleBar();
        widgets.add(w);

        w = new HighestScorerButton();
        widgets.add(w);

        selectedWidget = w;

        w = new CompetitionInfoButton();
        widgets.add(w);
    }

    public class TitleBar extends Button {

        public TitleBar() {
            setGeometry((game.settings.GUI_WIDTH - 400) / 2, 30, 400, 40);
            setColors(0x415600, 0x5E7D00, 0x243000);
            setText(Assets.strings.get("STATISTICS"), Font.Align.CENTER, Assets.font14);
            setActive(false);
        }
    }

    public class HighestScorerButton extends Button {

        public HighestScorerButton() {
            setGeometry((game.settings.GUI_WIDTH - 340) / 2, 270, 340, 40);
            setColors(0x568200, 0x77B400, 0x243E00);
            setText(Assets.strings.get("HIGHEST SCORER LIST"), Font.Align.CENTER, Assets.font14);
        }

        @Override
        public void onFire1Down() {
            // TODO
        }
    }

    public class CompetitionInfoButton extends Button {

        public CompetitionInfoButton() {
            setGeometry((game.settings.GUI_WIDTH - 340) / 2, 350, 340, 40);
            setColors(0x568200, 0x77B400, 0x243E00);
            setText(Assets.strings.get("COMPETITION INFO"), Font.Align.CENTER, Assets.font14);
        }

        @Override
        public void onFire1Down() {
            // TODO
        }
    }
}
