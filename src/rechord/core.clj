(ns rechord.core)

(def prefer-sharps first)
(def prefer-flats last)

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

(defn note-offset-pairs [i notes]
  (map #(cons % [i]) notes))

(def note-offsets
  (apply array-map (flatten (map-indexed note-offset-pairs offset-notes))))

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

(defn transpose-note [note offset]
   (-> note
    get-note-offset
    (+ offset)
    normalize-note
    get-notes))

(defn transpose [chord offset note-selector]
  (let [parts (rest (re-matches #"^([A-G][b#]?)(.*)" chord))
        note (first parts)
        mods (second parts)
        tnotes (transpose-note note offset)
        tnote (note-selector tnotes)]
    (clojure.string/join [tnote mods])))



