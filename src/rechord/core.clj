(ns rechord.core)

(def note-offsets
  (sorted-map
    "A" 0
    "A#" 1
    "Bb" 1
    "B" 2
    "C" 3
    "C#" 4
    "Db" 4
    "D" 5
    "D#" 6
    "Eb" 6
    "E" 7
    "F" 8
    "F#" 9
    "Gb" 9
    "G" 10
    "G#" 11
    "Ab" 11
   ))

(def offset-notes
  [
    ["A"]
    ["A#" "Bb"]
    ["B"]
    ["C"]
    ["C#" "Db"]
    ["D"]
    ["D#" "Eb"]
    ["E"]
    ["F"]
    ["F#" "Gb"]
    ["G"]
    ["G#" "Ab"]
   ])

(defn get-note-offset [note]
  (note-offsets note))

(defn get-note [offset]
  (first (offset-notes offset)))

(defn get-notes [offset]
  (offset-notes offset))

(defn normalize [n min max]
  (let [dif (+ max (- min) 1)]
    (cond
     (> min n) (normalize (+ n dif) min max)
     (> n max) (normalize (- n dif) min max)
     :else n)))

(defn normalize-note [n]
  (normalize n 0 11))

(defn transpose [chord offset]
   (-> chord
    get-note-offset
    (+ offset)
    (get-notes)))

