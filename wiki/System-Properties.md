System Properties
=================

This document outlines the system properties that can be set to
customize Casciian.  The table below summarizes the properties and
default values, below which is a more detailed outline.

| Property                  | Default | Description                            |
| ------------------------- | ------- | -------------------------------------- |
| casciian.animations          | true    | Animations enabled                  |
| casciian.blinkMillis         | 500     | Blinking interval                   |
| casciian.blinkDimPercent     | 80      | Percent to dim when blinked         |
| casciian.textMouse           | true    | Show text mouse pointer             |
| casciian.hideMouseWhenTyping | false   | Hide mouse on keystroke everywhere  |
| casciian.hideMenuBar         | false   | Hide the pull-down menu             |
| casciian.hideStatusBar       | false   | Hide the status bar                 |
| casciian.textBlink           | on      | Allow blinking text                 |
| casciian.translucence        | true    | Translucent windows                 |
| casciian.TEditor.hideMouseWhenTyping | true | Hide mouse on keystroke in text editor windows |
| casciian.TEditor.margin      | 0       | Right column margin to highlight    |
| casciian.TEditor.autoWrap    | false   | Automatically wrap text to margin   |
| casciian.TTerminal.closeOnExit | false | Close terminal window when shell exits    |
| casciian.TTerminal.hideMouseWhenTyping | true | Hide mouse on keystroke in terminal windows |
| casciian.TTerminal.ptypipe   | auto    | Use 'ptypipe' for terminal shell    |
| casciian.TTerminal.setsid    | true    | Run 'setsid script' for terminal shell    |
| casciian.TTerminal.shell     |    | Command to use for the terminal shell    |
| casciian.TTerminal.cmdHack   | true    | For Windows, append Ctrl-J after Enter    |
| casciian.TTerminal.scrollbackMax | 2000 | Number of lines in scrollback buffer     |
| casciian.TTerminal.TERM     | xterm-direct | TERM to use for the terminal shell    |
| casciian.ECMA48.modifyOtherKeys | false  | ECMA48: detect other modifiers    |
| casciian.ECMA48.rgbColor     | false   | ECMA48: emit 24-bit RGB for system colors    |
| casciian.ECMA48.textBlink    | auto    | ECMA48: Allow/simulate blinking text|
| casciian.ECMA48.color0       | #000000 | ECMA48: color for black             |
| casciian.ECMA48.color1       | #a80000 | ECMA48: color for red               |
| casciian.ECMA48.color2       | #00a800 | ECMA48: color for green             |
| casciian.ECMA48.color3       | #a85400 | ECMA48: color for yellow            |
| casciian.ECMA48.color4       | #0000a8 | ECMA48: color for blue              |
| casciian.ECMA48.color5       | #a800a8 | ECMA48: color for magenta           |
| casciian.ECMA48.color6       | #00a8a8 | ECMA48: color for cyan              |
| casciian.ECMA48.color7       | #a8a8a8 | ECMA48: color for white             |
| casciian.ECMA48.color8       | #545454 | ECMA48: color for black + bold      |
| casciian.ECMA48.color9       | #fc5454 | ECMA48: color for red + bold        |
| casciian.ECMA48.color10      | #54fc54 | ECMA48: color for green + bold      |
| casciian.ECMA48.color11      | #fcfc54 | ECMA48: color for yellow + bold     |
| casciian.ECMA48.color12      | #5454fc | ECMA48: color for blue + bold       |
| casciian.ECMA48.color13      | #fc54fc | ECMA48: color for magenta + bold    |
| casciian.ECMA48.color14      | #54fcfc | ECMA48: color for cyan + bold       |
| casciian.ECMA48.color15      | #fcfcfc | ECMA48: color for white + bold      |
| casciian.cjkFont.filename    | NotoSansMonoCJKtc-Regular.otf | Font for CJK characters |
| casciian.emojiFont.filename  | OpenSansEmoji.ttf | Font for emojis           |
| casciian.fallbackFont.filename |       | Font to use when no other available font has a codepoint |
| casciian.TButton.style       | square  | Button style                        |
| casciian.TWindow.borderStyleForeground | double | Window border style        |
| casciian.TWindow.borderStyleModal      | double | Window border style        |
| casciian.TWindow.borderStyleMoving     | single | Window border style        |
| casciian.TWindow.borderStyleInactive   | single | Window border style        |
| casciian.TMenu.borderStyle             | single | Menu border style          |
| casciian.TEditColorTheme.borderStyle   | double | Color theme window border style     |
| casciian.TEditColorTheme.options.borderStyle   | single | Interior boxes border style |
| casciian.TPanel.borderStyle  | none    | TPanel border style                 |
| casciian.TRadioGroup.borderStyle       | singleVdoubleH | Radio group border style    |
| casciian.TWindow.opacity     | 75      | Default window opacity (10 - 100)   |
| casciian.TTerminal.opacity   | 85      | Terminal window opacity (10 - 100)  |
| casciian.TMenu.opacity       | 85      | Menu window opacity (10 - 100)      |
| casciian.effect.windowOpen   | none    | Animation for opening new windows   |
| casciian.effect.windowClose  | none    | Animation for closing windows       |

casciian.animations
-------------------

Used by casciian.TApplication.  If true, allow high-speed animations in
Casciian widgets that support it (currently TButton, TCheckBox, TField,
and TRadioButton), and window open/close effects.  Note that most
animations require 24-bit RGB ("truecolor") support from the terminal.
Default: true.

casciian.blinkMillis
--------------------

Used by casciian.backend.ECMA48Terminal.  The number of millis to wait
before switching the blink from visible to invisible.  If 0 or
negative, blinking is disabled.  Default: 500.

casciian.blinkDimPercent
------------------------

Used by casciian.backend.ECMA48Terminal.  The percent of "dimming" to
do when blinking text is invisible.  0 means no blinking; 100 means
the foreground color matches the background color (like CGA/EGA/VGA
hardware).  Values less than 100 requires 24-bit RGB ("truecolor")
support from the terminal to look correct.  Default: 80.

casciian.textMouse
------------------

Used by casciian.TApplication.  If true, display a text-based mouse
pointer, otherwise do not.  Default: true.

casciian.hideMouseWhenTyping
----------------------------

Used by casciian.TApplication.  If true, suppress the text-based mouse
pointer after a user presses a key.  Mouse motion will restore the
pointer.  Default: false.

casciian.hideMenuBar
--------------------

Used by casciian.TApplication.  If true, do not display the pull-down
menu on the top row.  Menu keyboard accelerators will still work.
Default: false.

casciian.hideStatusBar
----------------------

Used by casciian.TApplication.  If true, do not display the status bar on
the bottom row.  Status bar keyboard accelerators will still work.
Default: false.

casciian.textBlink
------------------

Used by casciian.backend.ECMA48Terminal.  If on, blinking text may
blink.  If off, blinking text will always be visible without actually
blinking.  Default: on.

casciian.translucence
---------------------

Used by casciian.TApplication.  If true, alpha-blend windows during
drawing to achieve a glass-like translucence effect.  Note that this
requires 24-bit RGB ("truecolor") support from the terminal.  Default:
true.

casciian.TEditor.hideMouseWhenTyping
------------------------------------

Used by casciian.TEditorWindow.  If true, suppress the text-based mouse
pointer after a user presses a key within a text editor window.  Mouse
motion will restore the pointer.  Default: true.

casciian.TEditor.margin
-----------------------

Used by casciian.TEditorWindow.  If a positive integer, highlight the
column in the text editor window.  Default: 0.

casciian.TEditor.autoWrap
-------------------------

Used by casciian.TEditorWindow.  If true, automatically wrap the text to
fit inside the margin.  Default: false.

casciian.TTerminal.closeOnExit
------------------------------

Used by casciian.TTerminalWindow.  If true, close the window when the
spawned shell exits.  Default: false.

casciian.TTerminal.hideMouseWhenTyping
--------------------------------------

Used by casciian.TTerminalWindow.  If true, suppress the text-based mouse
pointer after a user presses a key within a terminal window.  Mouse
motion will restore the pointer.  Default: true.

casciian.TTerminal.ptypipe
--------------------------

Used by casciian.TTerminalWindow.  If 'true', or if 'auto' and 'ptypipe'
is on the PATH, then spawn shell using the 'ptypipe' utility rather
than 'script'.  This permits terminals to resize with the window.
ptypipe is a separate C language utility, available at
https://gitlab.com/AutumnMeowMeow/ptypipe.  Default: auto.

When casciian.TTerminal.ptypipe is true, and casciian.TTerminal.shell is not
set, then the command used for the terminal shell is
`ptypipe /bin/bash --login`

casciian.TTerminal.setsid
-------------------------

Used by casciian.TTerminalWindow.  If true, and os.name does not start
with "Windows" or "Mac", spawn shell using the 'setsid' utility to run
'script'.  This runs 'script' in a new process group, permitting
closing the terminal window without crashing the JVM.  Default: true.

When casciian.TTerminal.setsid is true, and casciian.TTerminal.shell is not
set, and os.name does not start with "Windows" or "Mac", then the
command used for the terminal shell is `setsid script -fqe /dev/null`

casciian.TTerminal.shell
------------------------

Used by casciian.TTerminalWindow.  If set, use this value to spawn the
shell.  If not set, the following commands are used based on the value
of the os.name system property:

| os.name starts with | Command used for terminal shell |
| ------------------- | ------------------------------- |
| Windows             | cmd.exe                         |
| Mac                 | script -q -F /dev/null          |
| Anything else       | script -fqe /dev/null           |

casciian.TTerminal.cmdHack
--------------------------

Used by casciian.TTerminalWindow.  If true, append a line feed (Ctrl-J,
hex 0x0a) after every enter/return keystroke (carriage return, Ctrl-M,
hex 0x0d).  This is needed for cmd.exe, but might not be for other
shells.  Default: true.

casciian.TTerminal.scrollbackMax
--------------------------------

Used by casciian.TTerminalWindow.  The number of lines in the scrollback
(offscreen) buffer.  If 0, scrollback is unlimited.  Default: 2000.

casciian.TTerminal.TERM
-----------------------

Used by casciian.TTerminalWindow.  The value to use for the TERM
environment variable.  Default: xterm-direct.

casciian.ECMA48.modifyOtherKeys
-------------------------------

Used by casciian.backend.ECMA48Terminal.  If true, enable the
"modifyOtherKeys" feature of Xterm to detect things like Ctrl-Enter,
Ctrl-Tab, Ctrl-Shift-A, and so on.  Default: false.

casciian.ECMA48.rgbColor
------------------------

Used by casciian.backend.ECMA48Terminal.  If true, emit T.416-style RGB
colors for normal system colors.  This is expensive in bandwidth, and
potentially terrible looking for non-xterms.  Default: false.

casciian.ECMA48.textBlink
-------------------------

Used by casciian.backend.ECMA48Terminal.  Controls whether text specified
with the blinking attribute will never actually blink, be simulated
without sending the blink attribute to the host terminal, or allow the
blink attribute to be sent to the host terminal.  (Overridden by
casciian.textBlink.)

Value can be one of the following:

| Value | Description                                                        |
| ----- | ------------------------------------------------------------------ |
| off   | Do not blink text at all                                           |
| soft  | Simulate blinking without sending blink attribute to host terminal |
| hard  | Send blink attribute to host terminal                              |
| auto  | Choose soft or hard based on terminal identification               |

Default: auto.

casciian.cjkFont.filename
-------------------------

Used by casciian.backend.GlyphMaker.  Filename containing the font to use
for CJK.  Filename must be on the classpath.  Default:
NotoSansMonoCJKtc-Regular.otf

Note that if the font is not found, NO ERROR IS REPORTED.

casciian.emojiFont.filename
---------------------------

Used by casciian.backend.GlyphMaker.  Filename containing the font to use
for emojis.  Filename must be on the classpath.  Default:
OpenSansEmoji.ttf

Note that if the font is not found, NO ERROR IS REPORTED.

casciian.fallbackFont.filename
------------------------------

Used by casciian.backend.GlyphMaker.  Filename containing the font to use
as a last resort when no other font has the codepoint.  Filename must
be on the classpath.  Default: ""

Note that if the font is not found, NO ERROR IS REPORTED.

casciian.TButton.style
----------------------

Used by casciian.TButton.  The style to draw the button ends and shadow.
Default: square.

Value can be one of the following:

| Value      | Description                       |
| ---------- | --------------------------------- |
| square     | Square edges                      |


casciian.TWindow.borderStyleForeground
--------------------------------------

Used by casciian.TWindow.  The glyphs to be used for the window border
and corners when it is the foreground window, not modal or being
moved/resized.  Default: double.

Value can be one of the following:

| Value   | Description                            |
| ------- | -------------------------------------- |
| default | Default for widget                     |
| none    | No border                              |
| single  | Single-line border with sharp corners  |
| double  | Double-line border with sharp corners  |
| singleVdoubleH | Single-line border on the vertical sections, double-line on the horizontal sections |
| singleHdoubleV | Double-line border on the vertical sections, single-line on the horizontal sections |
| round   | Single-line border with rounded corners |

casciian.TWindow.borderStyleModal
---------------------------------

Used by casciian.TWindow.  The glyphs to be used for the window border
and corners when it is a foreground model window.  See
casciian.TWindow.borderStyleForeground for the available values.
Default: double.

casciian.TWindow.borderStyleMoving
----------------------------------

Used by casciian.TWindow.  The glyphs to be used for the window border
and corners when it is being moved/resized.  See
casciian.TWindow.borderStyleForeground for the available values.
Default: single.

casciian.TWindow.borderStyleInactive
------------------------------------

Used by casciian.TWindow.  The glyphs to be used for the window border
and corners when it is not the foreground window.  See
casciian.TWindow.borderStyleForeground for the available values.
Default: single.

casciian.TMenu.borderStyle
--------------------------

Used by casciian.TMenu.  The glyphs to be used for a menu window border
and corners.  See casciian.TWindow.borderStyleForeground for the
available values.  Default: single.

casciian.TEditColorTheme.borderStyle
------------------------------------

Used by casciian.TEditColorThemeWindow.  The glyphs to be used for the
color theme editor window border and corners.  See
casciian.TWindow.borderStyleForeground for the available values.
Default: double.

casciian.TEditColorTheme.options.borderStyle
--------------------------------------------

Used by casciian.TEditColorThemeWindow.  The glyphs to be used for the
color theme editor interior boxes border and corners.  See
casciian.TWindow.borderStyleForeground for the available values.
Default: single.

casciian.TPanel.borderStyle
---------------------------

Used by casciian.TPanel.  The glyphs to be used for a drawn border and
corners.  See casciian.TWindow.borderStyleForeground for the available
values.  Default: none.

casciian.TRadioGroup.borderStyle
--------------------------------

Used by casciian.TRadioGroup.  The glyphs to be used for the box border
and corners around the radio button options.  See
casciian.TWindow.borderStyleForeground for the available values.
Default: singleVdoubleH.

casciian.translucence
---------------------

Used by casciian.TApplication.  If true, alpha-blend windows during
drawing to achieve a glass-like translucence effect.  Note that this
requires 24-bit RGB ("truecolor") support from the terminal.  Default:
true.

casciian.TWindow.opacity
------------------------

Used by casciian.TWindow.  The opacity to set for new windows, in
general.  A value of 0 will make the window entirely invisible; a
value of 100 will be fully opaque (no alpha-blending the area
underneath it).  Default: 75.

casciian.TTerminal.opacity
--------------------------

Used by casciian.TTerminalWindow.  The opacity to set for new terminal
windows.  A value of 0 will make the window entirely invisible; a
value of 100 will be fully opaque (no alpha-blending the area
underneath it).  Default: 85.

casciian.TMenu.opacity
----------------------

Used by casciian.TMenu.  The opacity to set for menus.  A value of 0 will
make the menu entirely invisible; a value of 100 will be fully opaque
(no alpha-blending the area underneath it).  Default: 85.


casciian.effect.windowOpen
--------------------------

Used by TApplication.  An optional animation shown when opening new
windows.  Default: none.

Value can be one of the following:

| Value     | Description                                          |
| --------- | ---------------------------------------------------- |
| none      | No animation                                         |
| fade      | Fade the window by changing its opacity              |
| burn      | Cover the window area with a plasma fire-type effect |
| wipeUp    | Sweep the window contents from bottom-to-top         |
| wipeDown  | Sweep the window contents from top-to-bottom         |
| wipeLeft  | Sweep the window contents from right-to-left         |
| wipeRight | Sweep the window contents from left-to-right         |

casciian.effect.windowClose
---------------------------

Used by TApplication.  An optional animation shown when a window is
closed.  See casciian.effect.windowOpen for the available values.
Default: none.
