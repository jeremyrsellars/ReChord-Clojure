(ns rechord.core-test
  (:require [clojure.test :refer :all]
            [rechord.core :refer :all]))


;; get-note-offset

(deftest get-note-offset-A
  (testing "get-note-offset A"
    (is (= 0 (get-note-offset "A")))))

(deftest get-note-offset-A#
  (testing "get-note-offset A#"
    (is (= 1 (get-note-offset "A#")))))

(deftest get-note-offset-Bb
  (testing "get-note-offset Bb"
    (is (= 1 (get-note-offset "Bb")))))

(deftest get-note-offset-B
  (testing "get-note-offset B"
    (is (= 2 (get-note-offset "B")))))

(deftest get-note-offset-G
  (testing "get-note-offset G"
    (is (= 10 (get-note-offset "G")))))

(deftest get-note-offset-Ab
  (testing "get-note-offset Ab"
    (is (= 11 (get-note-offset "Ab")))))

(deftest get-note-offset-H
  (testing "get-note-offset H is nil"
    (is (nil? (get-note-offset "H")))))

;; normalize-note

(deftest normalize-note-0
  (testing "Normalize-note 0"
    (is (= 0 (normalize-note 0)))))

(deftest normalize-note-negative-1
  (testing "Normalize-note -1"
    (is (= 11 (normalize-note -1)))))


(deftest normalize-note-12
  (testing "Normalize-note 12"
    (is (= 0 (normalize-note 12)))))

(deftest normalize-notes-octives
  (testing "Normalize-note for 4 octives"
    (is (= (flatten (repeat 4 (range 12)))
           (map normalize-note (range -24 24))))))


