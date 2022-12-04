;;
;; Day 1
(ns aoc2022.day1
   (:require [clojure.string :as s]
            ))
;; ----------------------------------------
;; programming examples
;; ----------------------------------------


(def sample (slurp "src/aoc2022/day1-example-input.txt"))
(def sample-result-a 24000)
(def sample-result-b 45000)

;; ----------------------------------------
;; puzzle input
;; ----------------------------------------

(def data-a (slurp "src/aoc2022/day1-input.txt"))
(def result-a 70296)
(def result-b 205381)

;; ----------------------------------------
;; helpers
;; ----------------------------------------

(defn str-to-long [str]
  (Long/parseLong str))

(defn seq-to-long [x]
  (mapv str-to-long x)
  )

;; ----------------------------------------
;; doing the work in steps
;; ----------------------------------------

(defn prepare-input [x]
  (->> x
       (s/split-lines)
       (partition-by empty?)
       (remove (comp s/blank? first))
       (map seq-to-long)
       (into [])
       (mapv (partial reduce + 0))
       (sort)
       (reverse)
       (into [])
  ))

(defn part-a [x]
  (->> x
       (prepare-input)
       (first)
       ))

(defn part-b [x]
  (->> x
       (prepare-input)
       (take 3)
       (reduce + 0)
       ))
