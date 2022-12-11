;;
;; Day 2
;;
(ns aoc2022.day2
   (:require [clojure.string :as s]
            ))
;; ----------------------------------------
;; programming examples
;; ----------------------------------------


(def sample "src/aoc2022/day2-sample-input.txt")
(def sample-result-a 15)
(def sample-result-b 12)

;; ----------------------------------------
;; puzzle input
;; ----------------------------------------

(def data-a "src/aoc2022/day2-input.txt")
(def result-a 8890)
(def result-b 10238)



;; ----------------------------------------
;; Rock Paper Scissors reprentations
;; ----------------------------------------
(def rps
  { "A" :rock
   "B" :paper
   "C" :scissors
   "X" :rock
   "Y" :paper
   "Z" :scissors
   })

(def rps-b
  { "X" :loses
   "Y" :draw
   "Z" :wins
   })

;; ----------------------------------------
;; helpers
;; ----------------------------------------


(defn conv-pairs
  "returns ['A' 'B'] for string 'A B' with A looked up in set-a and B in set-B"
  [set-a set-b s]
  (let [[a b] (s/split s #" ")]
       [(set-a a)(set-b b)]))

(defn convert [set-a set-b x]
  (->> x
      (slurp)
      (s/split-lines)
      (mapv (partial conv-pairs set-a set-b))
      ))

;; ----------------------------------------
;; game logic
;; ----------------------------------------

;; defines the winning element from point of view of first key
(def rpswins
  {
   :rock {:paper :loses
          :rock :draw
          :scissors :wins}
   :paper {:paper :draw
           :rock :wins
           :scissors :loses}
   :scissors {:paper :wins
              :rock :loses
              :scissors :draw}
   })

(defn rps-get "accesses the matrix with a then b"[a b]
  (get (get rpswins a) b))

(def shape-value
  { :rock 1
    :paper 2
    :scissors 3})

(defn round-outcome-right "for right opponent, i.e. you"
  [v]
  (case v
    :draw 3
    :loses 0
    :wins 6))

(defn your-score [[opponent-shape your-shape]]
  (+ (shape-value your-shape)
     (round-outcome-right
      ;; trick: switch point of view here to you
      (rps-get your-shape opponent-shape))))

(defn invert-result [x]
  (case x
    :draw :draw
    :loses :wins
    :wins :loses))

(defn invert-game
  "find sub-map for shape, determine your shape for result"
  [[opponent-shape result]]
  [opponent-shape
   (first
    (first
     (filter
      #(= (invert-result result) (second %1)) (rpswins opponent-shape))))
   ])

;; ----------------------------------------
;; doing the work in steps
;; ----------------------------------------

(defn part-a [x]
  (reduce + 0
          (map your-score (convert rps rps x))))

(defn part-b [x]
  (reduce + 0
          (map (comp your-score invert-game) (convert rps rps-b x))))

