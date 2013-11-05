(ns rechord.htmlrenderer-test
  (:require [clojure.test :refer :all]
            [rechord.core :refer :all]
            [rechord.htmlrenderer :refer :all]))

;; render-tagged-lines
(deftest render-tagged-lines-test
  (testing "render-tagged-lines produces html"
    (is (= "<h1>title</h1>\r\n<h3>chorus</h3>\r\n<span class='chord'>A B C</span><br/>\r\n<span class='lyric'>Lyrics</span><br/>\r\n<br/>\r\n"
           (render-tagged-lines
            [
             [:lyric "title"]
             [:lyric "chorus"]
             [:chord "A B C"]
             [:lyric "Lyrics"]
             [:separator ""]
             [:separator ""]
            ])))
    ))

