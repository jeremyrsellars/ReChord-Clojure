(ns rechord.core)

(def note-offsets
  {
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
   })

(def offset-notes
  [
    "A"
    "A#"
    "B"
    "C"
    "C#"
    "D"
    "D#"
    "E"
    "F"
    "F#"
    "G"
    "G#"
   ])

(defn get-note-offset [note]
  (note-offsets note))

(defn get-note [offset]
  (offset-notes offset))

(defn normalize [n min max]
  (let [dif (+ max (- min) 1)]
    (cond
     (> min n) (normalize (+ n dif) min max)
     (> n max) (normalize (- n dif) min max)
     :else n)))

(defn normalize-note [n]
  (normalize n 0 11))


