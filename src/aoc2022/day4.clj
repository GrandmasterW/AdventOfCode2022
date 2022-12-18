;;
(ns aoc2022.day4
   (:require [clojure.string :as s]
             [aoc2022.intrange :as ir]))

(def sample (slurp "src/aoc2022/day4-example-input.txt"))
(def sample-result-a 2)
(def sample-result-b 4)

;; ----------------------------------------
;; puzzle input
;; ----------------------------------------

(def data-a (slurp "src/aoc2022/day4-input.txt"))
(def result-a 595)
(def result-b 952)

;; ----------------------------------------
;; helpers
;; ----------------------------------------

(defn split-reg
  [reg s]
  (s/split s reg))

(defn to-long [x]
  (Long/parseLong x))

(defn convert-range "converts a string of form 'x-y' into a vector [x y],
  given that x and y are numbers, i.e. long"
  [r]
  (->> r
       (split-reg #"-")  ; returns a sequence of two elements
       (mapv to-long) ; yielding a vector of two elements
  ))
  
(defn convert-line
  "convers a line like '2-10,4-12' into a vector of [[2 10][4 12]]"
  [l]
  (->> l ; threading is cool
       (split-reg #",") ; returning '2-10' and '4-12'
       (mapv convert-range)))

(defn part-a
  "count subranges"
  [x]
  (->> x
       (s/split-lines)
       (mapv convert-line)
       (mapv ir/intrange-subrange?)
       (filter identity)
       (count)
       ))


;; ----------------------------------------


(defn part-b
  "count overlaps" 
  [x]
  (->> x
       (s/split-lines)
       (mapv convert-line)
       (mapv ir/overlap?)
       (filter identity)
       (count)
       ))

