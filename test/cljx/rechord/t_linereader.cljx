(ns rechord.t-linereader
  (#+clj :require #+cljs :require-macros
         [#+clj clojure.test #+cljs cemerick.cljs.test :refer (is deftest with-test run-tests testing)])
  (:require #+cljs [cemerick.cljs.test :as t]
            [rechord.linereader :refer [get-line-type get-tagged-lines]]))

;; get-line-type
(deftest empty-is-separator
  (testing "empty lines are separators"
    (is (= :separator
      (get-line-type "")))))

(deftest A-is-chords
  (testing "A single 'A' is a chord"
    (is (= :chord
      (get-line-type "A")))))

(deftest A-B-C-is-chords
  (testing "chord line"
    (is (= :chord
      (get-line-type " A B       C ")))))

(deftest A-birthday-song-is-lyrics
  (testing "note-offsets are based on A=0, A#=1, etc."
    (is (= :lyric
      (get-line-type "A birthday song")))))


;; get-tagged-lines
(deftest empty-is-one-separator
  (testing "empty is [:separator]"
    (is (= [[:separator ""] [:chord "A B C"]]
           (get-tagged-lines "\nA B C")
         ))))
(deftest birthday-song
  (testing "empty is [:separator]"
    (is (= [
[:separator ""]
[:chord "A            B        C"]
[:lyric "This is your birthday song"]]
           (get-tagged-lines
"
A            B        C
This is your birthday song")))))


