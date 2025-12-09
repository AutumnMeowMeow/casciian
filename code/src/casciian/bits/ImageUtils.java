/*
 * Casciian - Java Text User Interface
 *
 * Written 2013-2025 by Autumn Lamonte
 *
 * To the extent possible under law, the author(s) have dedicated all
 * copyright and related and neighboring rights to this software to the
 * public domain worldwide. This software is distributed without any
 * warranty.
 *
 * You should have received a copy of the CC0 Public Domain Dedication along
 * with this software. If not, see
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package casciian.bits;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * ImageUtils contains methods to:
 *
 *    - Check if an image is fully transparent.
 *
 *    - Compute the distance between two colors in RGB space.
 *
 *    - Compute the partial movement between two colors in RGB space.
 */
public class ImageUtils {

    // ------------------------------------------------------------------------
    // Constants --------------------------------------------------------------
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // Constructors -----------------------------------------------------------
    // ------------------------------------------------------------------------

    /**
     * Private constructor prevents accidental creation of this class.
     */
    private ImageUtils() {}

    // ------------------------------------------------------------------------
    // ImageUtils -------------------------------------------------------------
    // ------------------------------------------------------------------------

    /**
     * Check if any pixels in an image have not-0% alpha value.
     *
     * @param image the image to check
     * @return true if every pixel is fully transparent
     */
    public static boolean isFullyTransparent(final BufferedImage image) {
        assert (image != null);

        int [] rgbArray = image.getRGB(0, 0,
            image.getWidth(), image.getHeight(), null, 0, image.getWidth());

        if (rgbArray.length == 0) {
            // No image data, fully transparent.
            return true;
        }

        for (int i = 0; i < rgbArray.length; i++) {
            int alpha = (rgbArray[i] >>> 24) & 0xFF;
            if (alpha != 0x00) {
                // A not-fully transparent pixel is found.
                return false;
            }
        }
        // Every pixel was transparent.
        return true;
    }

    /**
     * Check if any pixels in an image have not-100% alpha value.
     *
     * @param image the image to check
     * @return true if every pixel is fully transparent
     */
    public static boolean isFullyOpaque(final BufferedImage image) {
        assert (image != null);

        int [] rgbArray = image.getRGB(0, 0,
            image.getWidth(), image.getHeight(), null, 0, image.getWidth());

        if (rgbArray.length == 0) {
            // No image data, fully transparent.
            return true;
        }

        for (int i = 0; i < rgbArray.length; i++) {
            int alpha = (rgbArray[i] >>> 24) & 0xFF;
            if (alpha != 0xFF) {
                // A partially transparent pixel is found.
                return false;
            }
        }
        // Every pixel was opaque.
        return true;
    }

    /**
     * Report the absolute distance in RGB space between two RGB colors.
     *
     * @param first the first color
     * @param second the second color
     * @return the distance
     */
    public static int rgbDistance(final int first, final int second) {
        int red   = (first >>> 16) & 0xFF;
        int green = (first >>>  8) & 0xFF;
        int blue  =  first         & 0xFF;
        int red2   = (second >>> 16) & 0xFF;
        int green2 = (second >>>  8) & 0xFF;
        int blue2  =  second         & 0xFF;
        double diff = Math.pow(red2 - red, 2);
        diff += Math.pow(green2 - green, 2);
        diff += Math.pow(blue2 - blue, 2);
        return (int) Math.sqrt(diff);
    }

    /**
     * Move from one point in RGB space to another, by a certain fraction.
     *
     * @param start the starting point color
     * @param end the ending point color
     * @param fraction the amount of movement between start and end, between
     * 0.0 (start) and 1.0 (end).
     * @return the final color
     */
    public static int rgbMove(final int start, final int end,
        final double fraction) {

        if (fraction <= 0) {
            return start;
        }
        if (fraction >= 1) {
            return end;
        }

        int red   = (start >>> 16) & 0xFF;
        int green = (start >>>  8) & 0xFF;
        int blue  =  start         & 0xFF;
        int red2   = (end >>> 16) & 0xFF;
        int green2 = (end >>>  8) & 0xFF;
        int blue2  =  end         & 0xFF;

        int rgbRed   =   red + (int) (fraction * (  red2 - red));
        int rgbGreen = green + (int) (fraction * (green2 - green));
        int rgbBlue  =  blue + (int) (fraction * ( blue2 - blue));

        rgbRed   = Math.min(Math.max(  rgbRed, 0), 255);
        rgbGreen = Math.min(Math.max(rgbGreen, 0), 255);
        rgbBlue  = Math.min(Math.max( rgbBlue, 0), 255);

        return (rgbRed << 16) | (rgbGreen << 8) | rgbBlue;
    }

    /**
     * Check if a Unicode block-drawing character is drawable by
     * drawUnicodeBlockDrawingChar().
     *
     * @param ch the character to draw
     * @return true if drawUnicodeBlockDrawingChar() can draw that character
     */
    public static boolean canDrawUnicodeBlockDrawingChar(final int ch) {
        switch (ch) {
        case ' ':
            // Space character
        case 0x2580:
            // Full upper half - 0x2580 - ▀
        case 0x258c:
            // Full left half - 0x258c - ▌
        case 0x2590:
            // Full right half - 0x2590 - ▐
        case 0x2584:
            // Full bottom half - 0x2584 - ▄
        case 0x2588:
            // Full foreground block - 0x2588 - █
            return true;
        }
        if ((ch >= 0x1FB00) && (ch <= 0x1FB3B)) {
            // Sextants
            return true;
        }

        return false;
    }

    /**
     * Draw a Unicode block-drawing character to an image.
     *
     * @param ch the character to draw
     * @param foreColorRGB the foreground color
     * @param backColorRGB the background color
     * @param image the image to draw onto
     */
    public static void drawUnicodeBlockDrawingChar(final int ch,
        final int foreColorRGB,  final int backColorRGB,
        final BufferedImage image) {

        java.awt.Color foreColor = new java.awt.Color(foreColorRGB);
        java.awt.Color backColor = new java.awt.Color(backColorRGB);
        Graphics gr = image.getGraphics();
        int width = image.getWidth();
        int height = image.getHeight();
        int halfWidth = width / 2;
        int halfHeight = height / 2;
        int thirdHeight = height / 3;

        switch (ch) {

        // Half blocks --------------------------------------------------------

        case ' ':
            // Space character
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            break;
        case 0x2580:
            // Full upper half - 0x2580 - ▀
            gr.setColor(foreColor);
            gr.fillRect(0, 0, width, halfHeight);
            gr.setColor(backColor);
            gr.fillRect(0, halfHeight, width, height - halfHeight);
            break;
        case 0x258c:
            // Full left half - 0x258c - ▌
            gr.setColor(foreColor);
            gr.fillRect(0, 0, halfWidth, height);
            gr.setColor(backColor);
            gr.fillRect(halfWidth, 0, width - halfWidth, height);
            break;
        case 0x2590:
            // Full right half - 0x2590 - ▐
            gr.setColor(backColor);
            gr.fillRect(0, 0, halfWidth, height);
            gr.setColor(foreColor);
            gr.fillRect(halfWidth, 0, width - halfWidth, height);
            break;
        case 0x2584:
            // Full bottom half - 0x2584 - ▄
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, halfHeight);
            gr.setColor(foreColor);
            gr.fillRect(0, halfHeight, width, height - halfHeight);
            break;
        case 0x2588:
            // Full foreground block - 0x2588 - █
            gr.setColor(foreColor);
            gr.fillRect(0, 0, width, height);
            break;

        // Sextants -----------------------------------------------------------

        case 0x1FB00:
            // 🬀      Block Sextant-1
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(0, 0, halfWidth, thirdHeight);
            break;
        case 0x1FB01:
            // 🬁      Block Sextant-2
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(halfWidth, 0, width - halfWidth, thirdHeight);
            break;
        case 0x1FB02:
            // 🬂      Block Sextant-12
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(0, 0, width, thirdHeight);
            break;
        case 0x1FB03:
            // 🬃      Block Sextant-3
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(0, thirdHeight, halfWidth, thirdHeight);
            break;
        case 0x1FB04:
            // 🬄      Block Sextant-13
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(0, 0, halfWidth, 2 * thirdHeight);
            break;
        case 0x1FB05:
            // 🬅      Block Sextant-23
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(halfWidth, 0, width - halfWidth, thirdHeight);
            gr.fillRect(0, thirdHeight, halfWidth, thirdHeight);
            break;
        case 0x1FB06:
            // 🬆      Block Sextant-123
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(0, 0, width, thirdHeight);
            gr.fillRect(0, thirdHeight, halfWidth, thirdHeight);
            break;
        case 0x1FB07:
            // 🬇      Block Sextant-4
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(halfWidth, thirdHeight, width - halfWidth, thirdHeight);
            break;
        case 0x1FB08:
            // 🬈      Block Sextant-14
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(0, 0, halfWidth, thirdHeight);
            gr.fillRect(halfWidth, thirdHeight, width - halfWidth, thirdHeight);
            break;
        case 0x1FB09:
            // 🬉      Block Sextant-24
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(halfWidth, 0, width - halfWidth, 2 * thirdHeight);
            break;
        case 0x1FB0A:
            // 🬊      Block Sextant-124
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(0, 0, width, thirdHeight);
            gr.fillRect(halfWidth, thirdHeight, width - halfWidth, thirdHeight);
            break;
        case 0x1FB0B:
            // 🬋      Block Sextant-34
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(0, thirdHeight, width, thirdHeight);
            break;
        case 0x1FB0C:
            // 🬌      Block Sextant-134
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(0, 0, halfWidth, thirdHeight);
            gr.fillRect(0, thirdHeight, width, thirdHeight);
            break;
        case 0x1FB0D:
            // 🬍      Block Sextant-234
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(halfWidth, 0, width - halfWidth, thirdHeight);
            gr.fillRect(0, thirdHeight, width, thirdHeight);
            break;
        case 0x1FB0E:
            // 🬎      Block Sextant-1234
            gr.setColor(foreColor);
            gr.fillRect(0, 0, width, 2 * thirdHeight);
            gr.setColor(backColor);
            gr.fillRect(0, 2 * thirdHeight, width, height - (2 * thirdHeight));
            break;
        case 0x1FB0F:
            // 🬏      Block Sextant-5
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(0, 2 * thirdHeight, halfWidth,
                height - (2 * thirdHeight));
            break;
        case 0x1FB10:
            // 🬐      Block Sextant-15
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(0, 0, halfWidth, thirdHeight);
            gr.fillRect(0, 2 * thirdHeight, halfWidth,
                height - (2 * thirdHeight));
            break;
        case 0x1FB11:
            // 🬑      Block Sextant-25
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(halfWidth, 0, width - halfWidth, thirdHeight);
            gr.fillRect(0, 2 * thirdHeight, halfWidth,
                height - (2 * thirdHeight));
            break;
        case 0x1FB12:
            // 🬒      Block Sextant-125
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(0, 0, width, thirdHeight);
            gr.fillRect(0, 2 * thirdHeight, halfWidth,
                height - (2 * thirdHeight));
            break;
        case 0x1FB13:
            // 🬓      Block Sextant-35
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(0, thirdHeight, halfWidth, height - thirdHeight);
            break;
        case 0x1FB14:
            // 🬔      Block Sextant-235
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(halfWidth, 0, width - halfWidth, thirdHeight);
            gr.fillRect(0, thirdHeight, halfWidth, height - thirdHeight);
            break;
        case 0x1FB15:
            // 🬕      Block Sextant-1235
            gr.setColor(foreColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(backColor);
            gr.fillRect(halfWidth, thirdHeight, width - halfWidth,
                height - thirdHeight);
            break;
        case 0x1FB16:
            // 🬖      Block Sextant-45
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(halfWidth, thirdHeight, width - halfWidth, thirdHeight);
            gr.fillRect(0, 2 * thirdHeight, halfWidth,
                height - (2 * thirdHeight));
            break;
        case 0x1FB17:
            // 🬗      Block Sextant-145
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(0, 0, halfWidth, thirdHeight);
            gr.fillRect(halfWidth, thirdHeight, width - halfWidth, thirdHeight);
            gr.fillRect(0, 2 * thirdHeight, halfWidth,
                height - (2 * thirdHeight));
            break;
        case 0x1FB18:
            // 🬘      Block Sextant-245
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(halfWidth, 0, width - halfWidth, 2 * thirdHeight);
            gr.fillRect(0, 2 * thirdHeight, halfWidth,
                height - (2 * thirdHeight));
            break;
        case 0x1FB19:
            // 🬙      Block Sextant-1245
            gr.setColor(foreColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(backColor);
            gr.fillRect(0, thirdHeight, halfWidth, thirdHeight);
            gr.fillRect(halfWidth, 2 * thirdHeight, width - halfWidth,
                height - 2 * thirdHeight);
            break;
        case 0x1FB1A:
            // 🬚      Block Sextant-345
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(0, thirdHeight, width, thirdHeight);
            gr.fillRect(0, 2 * thirdHeight, halfWidth,
                height - (2 * thirdHeight));
            break;
        case 0x1FB1B:
            // 🬛      Block Sextant-1345
            gr.setColor(foreColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(backColor);
            gr.fillRect(halfWidth, 0, width - halfWidth, thirdHeight);
            gr.fillRect(halfWidth, 2 * thirdHeight, width - halfWidth,
                height - (2 * thirdHeight));
            break;
        case 0x1FB1C:
            // 🬜      Block Sextant-2345
            gr.setColor(foreColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(backColor);
            gr.fillRect(0, 0, halfWidth, thirdHeight);
            gr.fillRect(halfWidth, 2 * thirdHeight, width - halfWidth,
                height - (2 * thirdHeight));
            break;
        case 0x1FB1D:
            // 🬝      Block Sextant-12345
            gr.setColor(foreColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(backColor);
            gr.fillRect(halfWidth, 2 * thirdHeight, width - halfWidth,
                height - (2 * thirdHeight));
            break;
        case 0x1FB1E:
            // 🬞      Block Sextant-6
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(halfWidth, 2 * thirdHeight, width - halfWidth,
                height - (2 * thirdHeight));
            break;
        case 0x1FB1F:
            // 🬟      Block Sextant-16
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(0, 0, halfWidth, thirdHeight);
            gr.fillRect(halfWidth, 2 * thirdHeight, width - halfWidth,
                height - (2 * thirdHeight));
            break;
        case 0x1FB20:
            // 🬠      Block Sextant-26
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(halfWidth, 0, width - halfWidth, thirdHeight);
            gr.fillRect(halfWidth, 2 * thirdHeight, width - halfWidth,
                height - (2 * thirdHeight));
            break;
        case 0x1FB21:
            // 🬡      Block Sextant-126
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(0, 0, width, thirdHeight);
            gr.fillRect(halfWidth, 2 * thirdHeight, width - halfWidth,
                height - (2 * thirdHeight));
            break;
        case 0x1FB22:
            // 🬢      Block Sextant-36
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(0, thirdHeight, halfWidth, thirdHeight);
            gr.fillRect(halfWidth, 2 * thirdHeight, width - halfWidth,
                height - (2 * thirdHeight));
            break;
        case 0x1FB23:
            // 🬣      Block Sextant-136
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(0, 0, halfWidth, 2 * thirdHeight);
            gr.fillRect(halfWidth, 2 * thirdHeight, width - halfWidth,
                height - (2 * thirdHeight));
            break;
        case 0x1FB24:
            // 🬤      Block Sextant-236
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(halfWidth, 0, width - halfWidth, thirdHeight);
            gr.fillRect(0, thirdHeight, halfWidth, thirdHeight);
            gr.fillRect(halfWidth, 2 * thirdHeight, width - halfWidth,
                height - (2 * thirdHeight));
            break;
        case 0x1FB25:
            // 🬥      Block Sextant-1236
            gr.setColor(foreColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(backColor);
            gr.fillRect(halfWidth, thirdHeight, width - halfWidth, thirdHeight);
            gr.fillRect(0, 2 * thirdHeight, halfWidth,
                height - (2 * thirdHeight));
            break;
        case 0x1FB26:
            // 🬦      Block Sextant-46
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(halfWidth, thirdHeight, width - halfWidth,
                height -thirdHeight);
            break;
        case 0x1FB27:
            // 🬧      Block Sextant-146
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(0, 0, halfWidth, thirdHeight);
            gr.fillRect(halfWidth, thirdHeight, width - halfWidth,
                height -thirdHeight);
            break;
        case 0x1FB28:
            // 🬨      Block Sextant-1246
            gr.setColor(foreColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(backColor);
            gr.fillRect(0, thirdHeight, halfWidth, thirdHeight);
            gr.fillRect(0, 2 * thirdHeight, halfWidth,
                height - (2 * thirdHeight));
            break;
        case 0x1FB29:
            // 🬩      Block Sextant-346
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(0, thirdHeight, width, thirdHeight);
            gr.fillRect(halfWidth, 2 * thirdHeight, width - halfWidth,
                height - (2 * thirdHeight));
            break;
        case 0x1FB2A:
            // 🬪      Block Sextant-1346
            gr.setColor(foreColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(backColor);
            gr.fillRect(halfWidth, 0, width - halfWidth, thirdHeight);
            gr.fillRect(0, 2 * thirdHeight, halfWidth,
                height - (2 * thirdHeight));
            break;
        case 0x1FB2B:
            // 🬫      Block Sextant-2346
            gr.setColor(foreColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(backColor);
            gr.fillRect(0, 0, halfWidth, thirdHeight);
            gr.fillRect(0, 2 * thirdHeight, halfWidth,
                height - (2 * thirdHeight));
            break;
        case 0x1FB2C:
            // 🬬      Block Sextant-12346
            gr.setColor(foreColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(backColor);
            gr.fillRect(0, 2 * thirdHeight, halfWidth,
                height - (2 * thirdHeight));
            break;
        case 0x1FB2D:
            // 🬭      Block Sextant-56
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(0, 2 * thirdHeight, width,
                height - (2 * thirdHeight));
            break;
        case 0x1FB2E:
            // 🬮      Block Sextant-156
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(0, 0, halfWidth, thirdHeight);
            gr.fillRect(0, 2 * thirdHeight, width,
                height - (2 * thirdHeight));
            break;
        case 0x1FB2F:
            // 🬯      Block Sextant-256
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(halfWidth, 0, width - halfWidth, thirdHeight);
            gr.fillRect(0, 2 * thirdHeight, width,
                height - (2 * thirdHeight));
            break;
        case 0x1FB30:
            // 🬰      Block Sextant-1256
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(0, 0, width, thirdHeight);
            gr.fillRect(0, 2 * thirdHeight, width,
                height - (2 * thirdHeight));
            break;
        case 0x1FB31:
            // 🬱      Block Sextant-356
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(foreColor);
            gr.fillRect(0, thirdHeight, halfWidth, thirdHeight);
            gr.fillRect(0, 2 * thirdHeight, width,
                height - (2 * thirdHeight));
            break;
        case 0x1FB32:
            // 🬲      Block Sextant-1356
            gr.setColor(foreColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(backColor);
            gr.fillRect(halfWidth, 0, width - halfWidth, 2 * thirdHeight);
            break;
        case 0x1FB33:
            // 🬳      Block Sextant-2356
            gr.setColor(foreColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(backColor);
            gr.fillRect(0, 0, halfWidth, thirdHeight);
            gr.fillRect(halfWidth, thirdHeight, width - halfWidth, thirdHeight);
            break;
        case 0x1FB34:
            // 🬴      Block Sextant-12356
            gr.setColor(foreColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(backColor);
            gr.fillRect(halfWidth, thirdHeight, width - halfWidth, thirdHeight);
            break;
        case 0x1FB35:
            // 🬵      Block Sextant-456
            gr.setColor(foreColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, thirdHeight);
            gr.fillRect(0, thirdHeight, halfWidth, thirdHeight);
            break;
        case 0x1FB36:
            // 🬶      Block Sextant-1456
            gr.setColor(foreColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(backColor);
            gr.fillRect(halfWidth, 0, width - halfWidth, thirdHeight);
            gr.fillRect(0, thirdHeight, halfWidth, thirdHeight);
            break;
        case 0x1FB37:
            // 🬷      Block Sextant-2456
            gr.setColor(foreColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(backColor);
            gr.fillRect(0, 0, halfWidth, 2 * thirdHeight);
            break;
        case 0x1FB38:
            // 🬸      Block Sextant-12456
            gr.setColor(foreColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(backColor);
            gr.fillRect(0, thirdHeight, halfWidth, thirdHeight);
            break;
        case 0x1FB39:
            // 🬹      Block Sextant-3456
            gr.setColor(foreColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(backColor);
            gr.fillRect(0, 0, width, thirdHeight);
            break;
        case 0x1FB3A:
            // 🬺      Block Sextant-13456
            gr.setColor(foreColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(backColor);
            gr.fillRect(halfWidth, 0, width - halfWidth, thirdHeight);
            break;
        case 0x1FB3B:
            // 🬻      Block Sextant-23456
            gr.setColor(foreColor);
            gr.fillRect(0, 0, width, height);
            gr.setColor(backColor);
            gr.fillRect(0, 0, halfWidth, thirdHeight);
            break;

        }

        gr.dispose();

    }


}
