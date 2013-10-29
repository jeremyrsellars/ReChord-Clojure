(ns rechord.main
  (:require [rechord.core :refer :all]))

(defn rechord-line [tagged-line offset note-selector]
  (let [tag (first tagged-line)
        line (second tagged-line)]
    (condp = tag
      :separator line
      :lyric     line
      :chord     (replace-chords line offset note-selector)
      line)))

(defn rechord [tagged-lines offset note-selector]
  (map #(rechord-line % offset note-selector) tagged-lines))


