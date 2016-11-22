package com.ygames.ysoccer.match;

import com.ygames.ysoccer.framework.GlColor3;

public class Hair {

    public enum Color {BLACK, BLOND, BROWN, RED, PLATINUM, GRAY, WHITE, PUNK_FUCHSIA, PUNK_BLOND}

    public final Color color;
    public final String style;

    public Hair(Color color, String style) {
        this.color = color;
        this.style = style;
    }

    static int[][][] map =
            // row, column, (frmx,frmy,posx,posy)
            {{
                    {0, 0, 15, 3}, {1, 0, 16, 3}, {2, 0, 14, 4}, {3, 0, 13, 3}, {4, 0, 11, 3}, {5, 0, 12, 1}, {6, 0, 12, 2}, {7, 0, 15, 1}, // 0
                    {0, 0, 15, 3}, {1, 0, 16, 3}, {2, 0, 14, 4}, {3, 0, 13, 3}, {4, 0, 11, 3}, {5, 0, 12, 1}, {6, 0, 12, 2}, {7, 0, 15, 1},
            }, {
                    {0, 1, 15, 3}, {1, 1, 16, 3}, {2, 1, 14, 4}, {3, 1, 13, 3}, {4, 1, 11, 3}, {5, 1, 12, 1}, {6, 1, 12, 2}, {7, 1, 15, 1}, // 1
                    {0, 1, 15, 3}, {1, 1, 16, 3}, {2, 1, 14, 4}, {3, 1, 13, 3}, {4, 1, 11, 3}, {5, 1, 12, 1}, {6, 1, 12, 2}, {7, 1, 15, 1},
            }, {
                    {0, 2, 15, 3}, {1, 2, 16, 3}, {2, 2, 14, 4}, {3, 2, 13, 3}, {4, 2, 11, 3}, {5, 2, 12, 1}, {6, 2, 12, 2}, {7, 2, 15, 1}, // 2
                    {0, 2, 15, 3}, {1, 2, 16, 3}, {2, 2, 14, 4}, {3, 2, 13, 3}, {4, 2, 11, 3}, {5, 2, 12, 1}, {6, 2, 12, 2}, {7, 2, 15, 1},
            }, {
                    {0, 1, 14, 2}, {1, 1, 16, 2}, {2, 1, 14, 3}, {3, 1, 13, 2}, {4, 1, 11, 2}, {5, 1, 12, 1}, {6, 1, 12, 0}, {7, 1, 14, 0}, // 3
                    {0, 1, 14, 2}, {1, 1, 16, 2}, {2, 1, 14, 3}, {3, 1, 13, 2}, {4, 1, 11, 2}, {5, 1, 12, 1}, {6, 1, 12, 0}, {7, 1, 14, 0},
            }, {
                    {0, 3, 8, 11}, {1, 3, 10, 9}, {2, 3, 14, 7}, {3, 3, 19, 10}, {4, 3, 20, 13}, {5, 3, 16, 14}, {6, 3, 13, 19}, {7, 3, 10, 16}, // 4
                    {0, 3, 8, 11}, {1, 3, 10, 9}, {2, 3, 14, 7}, {3, 3, 19, 10}, {4, 3, 20, 13}, {5, 3, 16, 14}, {6, 3, 13, 19}, {7, 3, 10, 16},
            }, {
                    {0, 1, 15, 4}, {1, 1, 16, 4}, {2, 1, 14, 4}, {3, 1, 13, 4}, {4, 1, 11, 4}, {5, 1, 12, 3}, {6, 1, 12, 4}, {7, 1, 17, 4}, // 5
                    {0, 1, 15, 0}, {1, 1, 16, 0}, {2, 1, 14, 0}, {3, 1, 13, 0}, {4, 1, 11, 0}, {5, 1, 12, -1}, {6, 1, 12, 0}, {7, 1, 17, 0},
            }, {
                    {0, 1, 20, 3}, {1, 1, 16, 2}, {2, 1, 12, 3}, {3, 1, 11, 3}, {4, 1, 8, 4}, {5, 1, 9, 1}, {6, 1, 11, 2}, {7, 1, 21, 1}, // 6
                    {0, 1, 20, 3}, {1, 1, 16, 2}, {2, 1, 12, 3}, {3, 1, 11, 3}, {4, 1, 8, 4}, {5, 1, 9, 1}, {6, 1, 11, 2}, {7, 1, 21, 1},
            }, {
                    {0, 4, 20, 11}, {1, 4, 17, 19}, {2, 4, 15, 20}, {3, 4, 11, 19}, {4, 4, 7, 12}, {5, 4, 7, 6}, {6, 4, 13, 4}, {7, 4, 20, 6}, // 7
                    {0, 4, 20, 14}, {1, 4, 17, 22}, {2, 4, 15, 23}, {3, 4, 11, 22}, {4, 4, 7, 15}, {5, 4, 7, 9}, {6, 4, 13, 7}, {7, 4, 20, 11},
            }, {
                    {0, 8, 16, 2}, {1, 8, 17, 2}, {2, 8, 15, 3}, {3, 8, 14, 2}, {4, 8, 12, 2}, {5, 8, 13, 1}, {6, 8, 13, 2}, {7, 8, 17, 1}, // 8
                    {0, 5, 18, 4}, {1, 5, 12, 4}, {2, 5, 18, 4}, {3, 5, 13, 4}, {0, 5, 18, 4}, {1, 5, 12, 4}, {2, 5, 21, 3}, {3, 5, 10, 3},
            }, {
                    {0, 9, 15, 2}, {1, 9, 18, 2}, {2, 9, 15, 4}, {3, 9, 13, 3}, {4, 9, 11, 2}, {5, 9, 12, 0}, {6, 9, 13, 2}, {7, 9, 17, 0}, // 9
                    {0, 5, 20, 3}, {1, 5, 11, 3}, {2, 5, 20, 2}, {3, 5, 11, 2}, {0, 5, 20, 7}, {1, 5, 11, 7}, {2, 5, 22, 6}, {3, 5, 9, 6},
            }, {
                    {0, 1, 14, 6}, {1, 1, 13, 6}, {2, 1, 15, 7}, {3, 1, 12, 5}, {4, 1, 12, 5}, {5, 1, 12, 4}, {6, 1, 13, 5}, {7, 1, 18, 4}, // 10
                    {0, 5, 18, -3}, {1, 5, 13, -3}, {2, 5, 20, -4}, {3, 5, 11, -4}, {0, 1, 0, 0}, {1, 1, 0, 0}, {2, 6, 23, 4}, {3, 6, 8, 4},
            }, {
                    {0, 1, 13, 4}, {1, 1, 13, 4}, {2, 1, 0, 0}, {3, 1, 12, 4}, {4, 1, 11, 4}, {5, 1, 12, 3}, {6, 1, 14, 3}, {7, 1, 17, 3}, // 11
                    {0, 6, 19, 8}, {1, 6, 12, 8}, {2, 5, 22, 7}, {3, 5, 9, 7}, {4, 1, 0, 0}, {5, 1, 0, 0}, {2, 6, 23, 8}, {3, 6, 8, 8},
            }, {
                    {0, 1, 13, 4}, {1, 1, 13, 4}, {2, 1, 0, 0}, {3, 1, 12, 4}, {4, 1, 11, 4}, {5, 1, 12, 3}, {6, 1, 14, 3}, {7, 1, 17, 3}, // 12
                    {0, 7, 23, 17}, {1, 7, 8, 17}, {2, 7, 24, 17}, {3, 7, 7, 17}, {0, 7, 0, 0}, {1, 7, 0, 0}, {2, 7, 22, 20}, {3, 7, 9, 20},
            }, {
                    {0, 1, 13, 4}, {1, 1, 13, 4}, {2, 1, 0, 0}, {3, 1, 12, 4}, {4, 1, 11, 4}, {5, 1, 12, 3}, {6, 1, 14, 3}, {7, 1, 17, 3}, // 13
                    {4, 5, 21, 7}, {5, 5, 7, 7}, {2, 6, 23, 5}, {3, 6, 6, 5}, {0, 7, 23, 19}, {1, 7, 8, 19}, {2, 7, 25, 20}, {3, 7, 6, 20},
            }, {
                    {2, 1, 14, 4}, {2, 1, 14, 7}, {2, 1, 14, 7}, {2, 1, 13, 10}, {4, 1, 0, 0}, {5, 1, 0, 0}, {6, 1, 0, 0}, {7, 1, 0, 0}, // 14
                    {0, 6, 0, 0}, {1, 6, 0, 0}, {2, 6, 23, 2}, {3, 6, 6, 2}, {0, 7, 0, 0}, {1, 7, 0, 0}, {2, 7, 21, 20}, {3, 7, 10, 19},
            }, {
                    {2, 1, 14, 6}, {2, 1, 14, 3}, {2, 1, 14, 5}, {6, 1, 13, 7}, {4, 1, 0, 0}, {5, 1, 0, 0}, {6, 1, 0, 0}, {7, 1, 0, 0}, // 15
                    {0, 6, 0, 0}, {1, 6, 0, 0}, {2, 6, 23, 5}, {3, 6, 6, 5}, {4, 1, 0, 0}, {5, 1, 0, 0}, {6, 1, 0, 0}, {7, 1, 0, 0},
            }, {
                    {0, 1, 0, 0}, {1, 1, 0, 0}, {2, 1, 0, 0}, {3, 1, 0, 0}, {4, 1, 0, 0}, {5, 1, 0, 0}, {6, 1, 0, 0}, {7, 1, 0, 0}, // 16
                    {2, 0, 15, 1}, {6, 0, 12, -1}, {2, 1, 12, 4}, {3, 1, 0, 0}, {4, 1, 0, 0}, {5, 1, 0, 0}, {6, 1, 0, 0}, {7, 1, 0, 0},
            }, {
                    {0, 1, 0, 0}, {1, 1, 0, 0}, {2, 1, 0, 0}, {3, 1, 0, 0}, {4, 1, 0, 0}, {5, 1, 0, 0}, {6, 1, 0, 0}, {7, 1, 0, 0}, // 17
                    {6, 6, 16, -1}, {7, 6, 13, -2}, {2, 1, 0, 0}, {3, 1, 0, 0}, {4, 1, 0, 0}, {5, 1, 0, 0}, {6, 1, 0, 0}, {7, 1, 0, 0},
            }, {
                    {0, 1, 0, 0}, {1, 1, 0, 0}, {2, 1, 0, 0}, {3, 1, 0, 0}, {4, 1, 0, 0}, {5, 1, 0, 0}, {6, 1, 0, 0}, {7, 1, 0, 0}, // 18
                    {6, 7, 16, -1}, {7, 7, 12, -2}, {2, 1, 0, 0}, {3, 1, 0, 0}, {4, 1, 0, 0}, {5, 1, 0, 0}, {6, 1, 0, 0}, {7, 1, 0, 0},
            }, {
                    {0, 1, 0, 0}, {1, 1, 0, 0}, {2, 1, 0, 0}, {3, 1, 0, 0}, {4, 1, 0, 0}, {5, 1, 0, 0}, {6, 1, 0, 0}, {7, 1, 0, 0}, // 19
                    {0, 1, 0, 0}, {1, 1, 0, 0}, {2, 1, 0, 0}, {3, 1, 0, 0}, {4, 1, 0, 0}, {5, 1, 0, 0}, {6, 1, 0, 0}, {7, 1, 0, 0},
            }};

    public static GlColor3[] colors = {
            new GlColor3(Color.BLACK.toString(), 0x2A2A2A, 0x1A1A1A, 0x090909),
            new GlColor3(Color.BLOND.toString(), 0xA2A022, 0x81801A, 0x605F11),
            new GlColor3(Color.BROWN.toString(), 0xA26422, 0x7B4C1A, 0x543411),
            new GlColor3(Color.RED.toString(), 0xE48B00, 0xB26D01, 0x7F4E01),
            new GlColor3(Color.PLATINUM.toString(), 0xFFFD7E, 0xE5E33F, 0xCAC800),
            new GlColor3(Color.GRAY.toString(), 0x7A7A7A, 0x4E4E4E, 0x222222),
            new GlColor3(Color.WHITE.toString(), 0xD5D5D5, 0xADADAD, 0x848484),
            new GlColor3(Color.PUNK_FUCHSIA.toString(), 0xFF00A8, 0x722F2F, 0x421A1A),
            new GlColor3(Color.PUNK_BLOND.toString(), 0xFDFB35, 0x808202, 0x595A05),
    };
}