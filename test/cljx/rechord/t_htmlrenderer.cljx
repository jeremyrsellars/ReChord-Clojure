(ns rechord.t-htmlrenderer
  (#+clj :require #+cljs :require-macros
         [#+clj clojure.test #+cljs cemerick.cljs.test :refer (is deftest with-test run-tests testing)])
  (:require #+cljs [cemerick.cljs.test :as t]
            [rechord.htmlrenderer :refer [render-tagged-lines]]))

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


