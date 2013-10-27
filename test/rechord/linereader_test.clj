(ns rechord.linereader-test
  (:require [clojure.test :refer :all]
            [rechord.linereader :refer :all]))

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


