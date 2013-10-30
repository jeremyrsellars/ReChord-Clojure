(ns rechord.main-test
  (:require [clojure.test :refer :all]
            [rechord.core :refer :all]
            [rechord.main :refer :all]))

;; rechord-tagged-line
(deftest rechord-tagged-line-separator-is-identity
  (testing "rechord-tagged-line [:separator :x] is :x"
    (is (= :x
           (rechord-tagged-line [:separator :x] -1 prefer-flats)))))

(deftest rechord-tagged-line-lyric-is-identity
  (testing "rechord-tagged-line [:lyric :y] is :y"
    (is (= :y
           (rechord-tagged-line [:lyric :y] -1 prefer-flats)))))

(deftest rechord-tagged-line-other-is-identity
  (testing "rechord-tagged-line [:other :x] is :x"
    (is (= :x
           (rechord-tagged-line [:other :x] -1 prefer-flats)))))

(deftest rechord-tagged-line-chord-is-rechorded
  (testing "rechord-tagged-line [:chord 'A B C'] is :y"
    (is (= "Ab Bb B"
           (rechord-tagged-line [:chord "A B C"] -1 prefer-flats)))))


;; rechord
(deftest rechord-tagged-lines-birthday-song
  (testing "rechord-tagged-lines birthday song"
    (is (= ["A birthday song"
            ""
            "G     A    G   C  G"
            "Happy birthday to you"]
           (rechord-tagged-lines [
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
           (rechord
"A birthday song
A     B    A   D  A
Happy birthday to you" -2 prefer-sharps)))))





