(ns rechord.main-test
  (:require [clojure.test :refer :all]
            [rechord.core :refer :all]
            [rechord.main :refer :all]))

;; rechord-line
(deftest rechord-line-separator-is-identity
  (testing "rechord-line [:separator :x] is :x"
    (is (= :x
           (rechord-line [:separator :x] -1 prefer-flats)))))

(deftest rechord-line-lyric-is-identity
  (testing "rechord-line [:lyric :y] is :y"
    (is (= :y
           (rechord-line [:lyric :y] -1 prefer-flats)))))

(deftest rechord-line-other-is-identity
  (testing "rechord-line [:other :x] is :x"
    (is (= :x
           (rechord-line [:other :x] -1 prefer-flats)))))

(deftest rechord-line-chord-is-rechorded
  (testing "rechord-line [:chord 'A B C'] is :y"
    (is (= "Ab Bb B"
           (rechord-line [:chord "A B C"] -1 prefer-flats)))))


;; rechord
(deftest rechord-birthday-song
  (testing "rechord birthday song"
    (is (= ["A birthday song"
            ""
            "G     A    G   C  G"
            "Happy birthday to you"]
           (rechord [
             [:lyric     "A birthday song"]
             [:separator ""]
             [:chord     "A     B    A   D  A"]
             [:lyric     "Happy birthday to you"]] -2 prefer-sharps)))))

