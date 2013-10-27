(ns rechord.linereader)

(defn get-line-type [line]
   (condp re-matches line
     #"^((\s*[A-G][b#]?[Mm]?(aj|sus)?\d*)/?)+\W*$" :chord
     #"^\s*$" :separator
     :lyric))

