(ns rechord.htmlrenderer
  (:require [clojure.string :as string]))

(defn render-chord-line [text]
  (string/replace text #" |\S+"
     #(if (= " " %) "&nbsp;"
        (string/join ["<span>" % "</span>"]))))

(defn render-lyric-line [text]
  (string/replace text #" " "&nbsp;"))

