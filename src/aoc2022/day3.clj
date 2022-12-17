;;
;; Day 3
(ns aoc2022.day3
   (:require [clojure.string :as s]
             [clojure.set :as cs]))
;; ----------------------------------------
;; programming examples
;; ----------------------------------------


(def sample (slurp "src/aoc2022/day3-example-input.txt"))
(def sample-result-a 157)
(def sample-result-b 70)

;; ----------------------------------------
;; puzzle input
;; ----------------------------------------

(def data-a (slurp "src/aoc2022/day3-input.txt"))
(def result-a 8233)
(def result-b 2821)

;; ----------------------------------------
;; helpers
;; ----------------------------------------


(defn convert-char
  "converts character into 1..26 if it is between a and z or 27..52 if it is between A and Z. In clojure, a is 97, while A is 65"
  [c]
  (if (< (int c) (int \a))
    (+ 27 (- (int c) (int \A)))
    (inc (- (int c) (int \a)))
    ))


(defn convert-compartment
  "converts one compartment (sequence) into ints"
  [compartment]
  (mapv convert-char compartment))

(defn convert-rucksack
  "converts both compartments (sequences) of a rucksack (line) into ints"
  [rucksack]
  (mapv convert-compartment rucksack))

(defn input-to-compartments
  "takes a big string with separate lines,
  splits it up,
  converts each line into a two sets of ints,
  one for each compartment per rucksack"
  [x]
  (->> x
       (s/split-lines)
       (mapv #(split-at (/ (count %1) 2) %1))
       (mapv convert-rucksack)))


(defn find-shares
  "takes a rucksack with 2 compartments, turns them into sets, intersects the sets and return a list of shared items"
  [rucksack]
  (let [c1 (set (first rucksack))
        c2 (set (second rucksack))]
    (cs/intersection c1 c2)))


(defn part-a
  "add up the shared values from the set"[x]
  (->> x
       (input-to-compartments)
       (mapv find-shares)
       (mapv first)
       (reduce + 0)
       ))


;; ----------------------------------------


(defn part-b
  "partition into groups of three lines and find single matches"
  [x]
  (->> x
       (s/split-lines)
       (mapv convert-compartment)
       (map set)
       (partition 3)
       (mapv #(apply cs/intersection %1))
       (mapv first)
       (reduce + 0)
       ))
