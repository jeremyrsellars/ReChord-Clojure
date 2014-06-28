(ns rechord.t-main
  (#+clj :require #+cljs :require-macros
         [#+clj clojure.test #+cljs cemerick.cljs.test :refer (is deftest with-test run-tests testing)])
  (:require #+cljs [cemerick.cljs.test :as t]
            [rechord.core :refer [prefer-flats prefer-sharps]]
            [rechord.main :as main]))

;; rechord-tagged-line
(deftest rechord-tagged-line-test
  (testing "rechord-tagged-line"
    (is (= :x
           (main/rechord-tagged-line [:separator :x] -1 prefer-flats)))
    (is (= :y
           (main/rechord-tagged-line [:lyric :y] -1 prefer-flats)))
    (is (= :x
           (main/rechord-tagged-line [:other :x] -1 prefer-flats)))
    (is (= "AbBbB"
           (main/rechord-tagged-line [:chord "A B C"] -1 prefer-flats)))))


;; rechord
(deftest rechord-tagged-lines-birthday-song
  (testing "rechord-tagged-lines birthday song"
    (is (= ["A birthday song"
            ""
            "G     A    G   C  G"
            "Happy birthday to you"]
           (main/rechord-tagged-lines [
             [:lyric     "A birthday song"]
             [:separator ""]
             [:chord     "A     B    A   D  A"]
             [:lyric     "Happy birthday to you"]] -2 prefer-sharps)))))

;; rechord-all
(deftest rechord-birthday-song
  (testing "rechord-all birthday song"
    (is (=
"A birthday song
G     A    G   C  G
Happy birthday to you"
           (main/rechord
"A birthday song
A     B    A   D  A
Happy birthday to you" -2 prefer-sharps)))))

;; rechord-html
(deftest rechord-html-birthday-song
  (testing "rechord-html birthday song"
    (is (=
"<h1>A birthday song</h1>\r
<span class='chord'>G     A    G   C  G</span><br/>\r
<span class='lyric'>Happy birthday to you</span><br/>"
           (main/rechord-html
"A birthday song
A     B    A   D  A
Happy birthday to you" -2 prefer-sharps)))))






