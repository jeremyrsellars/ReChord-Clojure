(ns rechord.t-core
  (#+clj :require #+cljs :require-macros
         [#+clj clojure.test #+cljs cemerick.cljs.test :refer (is deftest with-test run-tests testing)])
  (:require #+cljs [cemerick.cljs.test :as t]
            [rechord.core :as core]))

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
   core/note-offsets))))


;; get-note-offset

(deftest get-note-offset-A
  (testing "get-note-offset A"
    (is (= 0 (core/get-note-offset "A")))))

(deftest get-note-offset-A#
  (testing "get-note-offset A#"
    (is (= 1 (core/get-note-offset "A#")))))

(deftest get-note-offset-Bb
  (testing "get-note-offset Bb"
    (is (= 1 (core/get-note-offset "Bb")))))

(deftest get-note-offset-B
  (testing "get-note-offset B"
    (is (= 2 (core/get-note-offset "B")))))

(deftest get-note-offset-G
  (testing "get-note-offset G"
    (is (= 10 (core/get-note-offset "G")))))

(deftest get-note-offset-Ab
  (testing "get-note-offset Ab"
    (is (= 11 (core/get-note-offset "Ab")))))

(deftest get-note-offset-H
  (testing "get-note-offset H is nil"
    (is (nil? (core/get-note-offset "H")))))


;; get-note

(deftest get-note-0
  (testing "get the first note"
    (is (= "A"
           (core/get-note 0)))))

(deftest get-note-all
  (testing "get the normal notes"
    (is (= ["A" "A#" "B" "C" "C#" "D" "D#" "E" "F" "F#" "G" "G#"]
           (map core/get-note (range 12))))))

;; normalize-note

(deftest normalize-note-0
  (testing "Normalize-note 0"
    (is (= 0 (core/normalize-note 0)))))

(deftest normalize-note-negative-1
  (testing "Normalize-note -1"
    (is (= 11 (core/normalize-note -1)))))


(deftest normalize-note-12
  (testing "Normalize-note 12"
    (is (= 0 (core/normalize-note 12)))))

(deftest normalize-notes-octives
  (testing "Normalize-note for 4 octives"
    (is (= (flatten (repeat 4 (range 12)))
           (map core/normalize-note (range -24 24))))))

;; get-notes

(deftest get-notes-0
  (testing "get the first note"
    (is (= '("A")
           (core/get-notes 0)))))

(deftest get-notes-1
  (testing "get the first flat/sharp"
    (is (= '("A#" "Bb")
           (core/get-notes 1)))))


;; transpose

(deftest transpose-A-0
  (testing "transpose A 0"
    (is (= "A"
           (core/transpose "A" 0 core/prefer-sharps)))))

(deftest transpose-A-1-sharp
  (testing "transpose A 1"
    (is (= "A#"
           (core/transpose "A" 1 core/prefer-sharps)))))

(deftest transpose-A-1-flat
  (testing "transpose A 1"
    (is (= "Bb"
           (core/transpose "A" 1 core/prefer-flats)))))

(deftest transpose-Am-1
  (testing "transpose A 1"
    (is (= "A#m"
           (core/transpose "Am" 1 core/prefer-sharps)))))

(deftest transpose-AbMaj7--1
  (testing "transpose AbMaj7 -1"
    (is (= "GMaj7"
           (core/transpose "AbMaj7" -1 core/prefer-sharps)))))

(deftest transpose-AMaj7--1-sharp
  (testing "transpose AMaj7 -1"
    (is (= "G#Maj7"
           (core/transpose "AMaj7" -1 core/prefer-sharps)))))

(deftest transpose-AMaj7--1-flat
  (testing "transpose AMaj7 -1"
    (is (= "AbMaj7"
           (core/transpose "AMaj7" -1 core/prefer-flats)))))

(deftest transpose-F#m7-3-flat
  (testing "transpose F#m7 3"
    (is (= "Am7"
           (core/transpose "F#m7" 3 core/prefer-flats)))))


;; transpose-width
(deftest transpose-width-tests
  (testing "transpose-width"
    (is (= "Am7"
           (core/transpose-width "Am7" 0 core/prefer-flats)))
    (is (= "Am7 "
           (core/transpose-width "Am7 " 0 core/prefer-flats)))
    (is (= "Am7 "
           (core/transpose-width "F#m7" 3 core/prefer-flats)))
    (is (= "F#m7"
           (core/transpose-width "Am7" -3 core/prefer-sharps)))
    (is (= "F#m7"
           (core/transpose-width "Am7 " -3 core/prefer-sharps)))
    (is (= "A"
           (core/transpose-width "A" 0 core/prefer-sharps)))
    (is (= "A#"
           (core/transpose-width "A" 1 core/prefer-sharps)))
    (is (= "Bb"
           (core/transpose-width "A" 1 core/prefer-flats)))
    (is (= "A#m"
           (core/transpose-width "Am" 1 core/prefer-sharps)))
    (is (= "GMaj7 "
           (core/transpose-width "AbMaj7" -1 core/prefer-sharps)))
    (is (= "G#Maj7"
           (core/transpose-width "AMaj7" -1 core/prefer-sharps)))
    (is (= "AbMaj7"
           (core/transpose-width "AMaj7" -1 core/prefer-flats)))
    (is (= "Absus4"
           (core/transpose-width "Asus4" -1 core/prefer-flats)))
    (is (= "Am7 "
           (core/transpose-width "F#m7" 3 core/prefer-flats)))
    ))


;; replace-chords
(deftest replace-chords-empty-0-sharps
  (testing "empty is empty"
    (is (= ""
           (core/replace-chords "" 0 core/prefer-sharps)))))

(deftest replace-chords-A-0-sharps
  (testing "Identity for A"
    (is (= "A"
           (core/replace-chords "A" 0 core/prefer-sharps)))))

(deftest replace-chords-A-2-sharps
  (testing "'A' + 2 is 'B'"
    (is (= "B"
           (core/replace-chords "A" 2 core/prefer-sharps)))))

(deftest replace-chords-_A_-2-sharps
  (testing "' A ' + 2 is ' B '"
    (is (= " B "
           (core/replace-chords " A " 2 core/prefer-sharps)))))

(deftest replace-chords-_A__GMaj7-2-sharps
  (testing "' A  Am' + 2 is ' B  BMaj'"
    (is (= " B  BMaj7"
           (core/replace-chords " A  AMaj7" 2 core/prefer-sharps)))))

(deftest replace-chords-_A__GMaj7__-2-sharps
  (testing "' A  GMaj7  ' + 2 is ' B  AMaj7  '"
    (is (= " B  AMaj7  "
           (core/replace-chords " A  GMaj7  " 2 core/prefer-sharps)))))

(deftest replace-chords-_A_-4-sharps
  (testing "' Am ' + 4 is ' C#m '"
    (is (= " C#m"
           (core/replace-chords " Am " 4 core/prefer-sharps)))))

(deftest replace-chords-_A_-4-flats
  (testing "' Am ' + 4 is ' Dbm '"
    (is (= " Dbm"
           (core/replace-chords " Am " 4 core/prefer-flats)))))






