# ReChord

[![Build Status](https://travis-ci.org/jeremyrsellars/ReChord-Clojure.png)](https://travis-ci.org/jeremyrsellars/ReChord-Clojure)

A Clojure library designed to transpose the chords on a lead sheet

Ready to rock out some with insane shredding, but the key is no good?  Transpose it to a key you can play or wail with ReChord.

## Usage

```clojure

(def song 
"A birthday song
A     B    A   D  A
Happy birthday to you")

(rechord song -2 prefer-sharps)

"A birthday song
G     A    G   C  G
Happy birthday to you"

```
## License

Copyright © 2013 Jeremy R. Sellars

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
