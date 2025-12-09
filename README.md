Casciian - Java Text User Interface library
===========================================

This library implements a text-based windowing system loosely
reminiscent of Borland's [Turbo
Vision](http://en.wikipedia.org/wiki/Turbo_Vision) system for
Xterm-like terminals.

Casciian can be run inside its own terminal window, with support for
all of its features including mouse, and of course more terminals.

Casciian has seen inspiration from several other projects:

* Translucent windows were inspired by
  [notcurses](https://github.com/dankamongmen/notcurses).  Translucent
  windows and layered images generally look as one would expect in a
  modern graphical environment...but it's mostly text.

* Pulsing button text, window effects, and desktop effects were
  inspired by [vtm's](https://github.com/netxs-group/vtm) incredibly
  slick game-like aesthetic.



Copyright Status
----------------

To the extent possible under law, the author(s) of Casciian have
dedicated all copyright and related and neighboring rights to Casciian
to the public domain worldwide. This software is distributed without
any warranty.  The COPYING file describes this intent, and provides a
public license fallback for those jurisdictions that do not recognize
the public domain.



Running The Demo
----------------

src/demo contains official demos showing all of the stock UI controls.
The demos can be run as follows:

  * 'java -cp casciian.jar:demo.jar demo.Demo1' .  This will use
    System.in/out with Xterm-like sequences.

  * 'java -cp casciian.jar:demo.jar demo.Demo2 PORT' (where PORT is a
    number to run the TCP daemon on).  This will use the Xterm backend
    on a telnet server that will update with screen size changes.

  * 'java -cp casciian.jar:demo.jar demo.Demo3' .  This will use
    System.in/out with Xterm-like sequences.  One can see in the code
    how to pass a different InputReader and OutputReader to
    TApplication, permitting a different encoding than UTF-8; in this
    case, code page 437.

  * 'java -cp casciian.jar:demo.jar demo.Demo4' .  This demonstrates
    hidden windows and a custom TDesktop.

  * 'java -cp casciian.jar:demo.jar demo.Demo7' .  This demonstrates the
    BoxLayoutManager, achieving a similar result as the
    javax.swing.BoxLayout apidocs example.

  * 'java -cp casciian.jar:demo.jar demo.Demo8 PORT' (where PORT is a
    number to run the TCP daemon on).  This will use the Xterm backend
    on a telnet server to share one screen to many terminals.
