(ns rechord.main
  (:require [rechord.core :as core]
            [rechord.linereader :as linereader]
            [clojure.string :as string]))

(defn rechord-tagged-line [tagged-line offset note-selector]
  (let [tag (first tagged-line)
        line (second tagged-line)]
    (condp = tag
      :separator line
      :lyric     line
      :chord     (core/replace-chords line offset note-selector)
      line)))

(defn rechord-tagged-lines [tagged-lines offset note-selector]
  (map #(rechord-tagged-line % offset note-selector) tagged-lines))


(defn rechord [text offset note-selector]
  (string/join "\n" (rechord-tagged-lines (linereader/get-tagged-lines text) offset note-selector)))
