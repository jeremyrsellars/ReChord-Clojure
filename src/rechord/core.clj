(ns rechord.core)

(def prefer-sharps first)
(def prefer-flats last)

(def notes
  [
    ["A"]        ;; 0
    ["A#" "Bb"]  ;; 1
    ["B"]        ;; 2
    ["C"]        ;; 3
    ["C#" "Db"]  ;; 4
    ["D"]        ;; 5
    ["D#" "Eb"]  ;; 6
    ["E"]        ;; 7
    ["F"]        ;; 8
    ["F#" "Gb"]  ;; 9
    ["G"]        ;; 10
    ["G#" "Ab"]  ;; 11
   ])

(defn note-offset-pairs [i notes]
  (map #(cons % [i]) notes))

(def note-offsets
  (apply array-map (flatten (map-indexed note-offset-pairs notes))))

(defn get-note-offset [note]
  (note-offsets note))

(defn normalize [n min max]
  (let [dif (+ max (- min) 1)]
    (cond
     (> min n) (normalize (+ n dif) min max)
     (> n max) (normalize (- n dif) min max)
     :else n)))

(defn normalize-note [n]
  (normalize n 0 11))

(defn get-note [offset]
  (first (notes (normalize-note offset))))

(defn get-notes [offset]
  (notes (normalize-note offset)))

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

(defn replace-chords [line offset note-selector]
  (clojure.string/replace
     line
     #"[A-G][b#]?(Maj|MAJ|maj|min|m|M|[0-9])*"
     #(transpose (first %) offset note-selector)))

