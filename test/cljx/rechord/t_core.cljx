(ns rechord.t_core
  (:require [clojure.test :refer :all]
            [rechord.core :refer :all]))

;; note-offsets
(deftest note-offsets-named-map
  (testing "note-offsets are based on A=0, A#=1, etc."
    (is (=
  {
    "A" 0
    "A#" 1
    "Bb" 1
    "B" 2
    "C" 3
    "C#" 4
    "Db" 4
    "D" 5
    "D#" 6
    "Eb" 6
    "E" 7
    "F" 8
    "F#" 9
    "Gb" 9
    "G" 10
    "G#" 11
    "Ab" 11
   }
   note-offsets))))


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


;; get-note

(deftest get-note-0
  (testing "get the first note"
    (is (= "A"
           (get-note 0)))))

(deftest get-note-all
  (testing "get the normal notes"
    (is (= ["A" "A#" "B" "C" "C#" "D" "D#" "E" "F" "F#" "G" "G#"]
           (map get-note (range 12))))))

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

;; get-notes

(deftest get-notes-0
  (testing "get the first note"
    (is (= '("A")
           (get-notes 0)))))

(deftest get-notes-1
  (testing "get the first flat/sharp"
    (is (= '("A#" "Bb")
           (get-notes 1)))))


;; transpose

(deftest transpose-A-0
  (testing "transpose A 0"
    (is (= "A"
           (transpose "A" 0 prefer-sharps)))))

(deftest transpose-A-1-sharp
  (testing "transpose A 1"
    (is (= "A#"
           (transpose "A" 1 prefer-sharps)))))

(deftest transpose-A-1-flat
  (testing "transpose A 1"
    (is (= "Bb"
           (transpose "A" 1 prefer-flats)))))

(deftest transpose-Am-1
  (testing "transpose A 1"
    (is (= "A#m"
           (transpose "Am" 1 prefer-sharps)))))

(deftest transpose-AbMaj7--1
  (testing "transpose AbMaj7 -1"
    (is (= "GMaj7"
           (transpose "AbMaj7" -1 prefer-sharps)))))

(deftest transpose-AMaj7--1-sharp
  (testing "transpose AMaj7 -1"
    (is (= "G#Maj7"
           (transpose "AMaj7" -1 prefer-sharps)))))

(deftest transpose-AMaj7--1-flat
  (testing "transpose AMaj7 -1"
    (is (= "AbMaj7"
           (transpose "AMaj7" -1 prefer-flats)))))

(deftest transpose-F#m7-3-flat
  (testing "transpose F#m7 3"
    (is (= "Am7"
           (transpose "F#m7" 3 prefer-flats)))))


;; transpose-width
(deftest transpose-width-tests
  (testing "transpose-width"
    (is (= "Am7"
           (transpose-width "Am7" 0 prefer-flats)))
    (is (= "Am7 "
           (transpose-width "Am7 " 0 prefer-flats)))
    (is (= "Am7 "
           (transpose-width "F#m7" 3 prefer-flats)))
    (is (= "F#m7"
           (transpose-width "Am7" -3 prefer-sharps)))
    (is (= "F#m7"
           (transpose-width "Am7 " -3 prefer-sharps)))
    (is (= "A"
           (transpose-width "A" 0 prefer-sharps)))
    (is (= "A#"
           (transpose-width "A" 1 prefer-sharps)))
    (is (= "Bb"
           (transpose-width "A" 1 prefer-flats)))
    (is (= "A#m"
           (transpose-width "Am" 1 prefer-sharps)))
    (is (= "GMaj7 "
           (transpose-width "AbMaj7" -1 prefer-sharps)))
    (is (= "G#Maj7"
           (transpose-width "AMaj7" -1 prefer-sharps)))
    (is (= "AbMaj7"
           (transpose-width "AMaj7" -1 prefer-flats)))
    (is (= "Absus4"
           (transpose-width "Asus4" -1 prefer-flats)))
    (is (= "Am7 "
           (transpose-width "F#m7" 3 prefer-flats)))
    ))


;; replace-chords
(deftest replace-chords-empty-0-sharps
  (testing "empty is empty"
    (is (= ""
           (replace-chords "" 0 prefer-sharps)))))

(deftest replace-chords-A-0-sharps
  (testing "Identity for A"
    (is (= "A"
           (replace-chords "A" 0 prefer-sharps)))))

(deftest replace-chords-A-2-sharps
  (testing "'A' + 2 is 'B'"
    (is (= "B"
           (replace-chords "A" 2 prefer-sharps)))))

(deftest replace-chords-_A_-2-sharps
  (testing "' A ' + 2 is ' B '"
    (is (= " B "
           (replace-chords " A " 2 prefer-sharps)))))

(deftest replace-chords-_A__GMaj7-2-sharps
  (testing "' A  Am' + 2 is ' B  BMaj'"
    (is (= " B  BMaj7"
           (replace-chords " A  AMaj7" 2 prefer-sharps)))))

(deftest replace-chords-_A__GMaj7__-2-sharps
  (testing "' A  GMaj7  ' + 2 is ' B  AMaj7  '"
    (is (= " B  AMaj7  "
           (replace-chords " A  GMaj7  " 2 prefer-sharps)))))

(deftest replace-chords-_A_-4-sharps
  (testing "' Am ' + 4 is ' C#m '"
    (is (= " C#m"
           (replace-chords " Am " 4 prefer-sharps)))))

(deftest replace-chords-_A_-4-flats
  (testing "' Am ' + 4 is ' Dbm '"
    (is (= " Dbm"
           (replace-chords " Am " 4 prefer-flats)))))






