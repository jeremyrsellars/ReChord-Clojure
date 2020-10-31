(ns rechord.linereader)

(defn get-line-type [line]
   (condp re-matches line
     #"^((\s*[A-G][b#]?[Mm]?(aj|sus)?\d*)/?)+\W*$" :chord
     #"^\s*$" :separator
     :lyric))

(defn tag-line [line]
  [(get-line-type line) line])

(defn get-tagged-lines [text]
  (-> text
    (clojure.string/split-lines)
    ((fn [x] (map tag-line x)))))


