(ns rechord.htmlrenderer
  (:require [clojure.string :as string]))

(defn- create-formatter [replacement-type parts]
  (fn [line]
    (let [line-type (first line)
          text (second line)
          strings (map #(cond
                         (= :type %) (name line-type)
                         (= :text %) text
                         :else %) parts)]
      [replacement-type (string/join strings)])))

(def format-chord
  (create-formatter :chord ["<span class='" :type "'>" :text "</span><br/>"]))

(def format-lyric
  (create-formatter :chord ["<span class='" :type "'>" :text "</span><br/>"]))

(def format-title
  (create-formatter :title ["<h1>" :text "</h1>"]))

(def format-break
  (create-formatter :separator ["<br/>"]))

(def format-heading
  (create-formatter :heading ["<h3>" :text "</h3>"]))

(defn get-formatter [prev cur]
  (cond
    (= :chord                   cur      ) format-chord
    (= [:lyric     :chord]     [cur prev]) format-lyric
    (= [:lyric     :lyric]     [cur prev]) format-lyric
    (= [:lyric     nil   ]     [cur prev]) format-title
    (=  :lyric                  cur      ) format-heading
    (= [:separator :separator] [cur prev]) identity
    (=  :separator              cur      ) format-break
    :else                                  identity))

(defn- reducer [prev-line line]
  (let [formatter (get-formatter (first prev-line) (first line))]
    (formatter line)))

(defn render-tagged-lines [lines]
  (let [tagged-html-lines (rest (reductions reducer [nil nil] lines))
        text-lines (map second tagged-html-lines)]
    (string/join "\r\n" text-lines)))

