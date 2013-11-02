(ns rechord.htmlrenderer-test
  (:require [clojure.test :refer :all]
            [rechord.core :refer :all]
            [rechord.htmlrenderer :refer :all]))

;; render-chord-line
(deftest render-chord-line-html
  (testing "render-chord-line [text]"
    (is (= ""
           (render-chord-line "")))
    (is (= "&nbsp;"
           (render-chord-line " ")))
    (is (= "<span>A</span>"
           (render-chord-line "A")))
    ))


;; render-lyric-line
(deftest render-lyric-line-html
  (testing "render-lyric-line [text]"
    (is (= ""
           (render-lyric-line "")))
    (is (= "&nbsp;"
           (render-lyric-line " ")))
    (is (= "&nbsp;A&nbsp;"
           (render-lyric-line " A ")))
    ))

