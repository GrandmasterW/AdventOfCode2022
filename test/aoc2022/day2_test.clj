(ns aoc2022.day2-test
  (:require [clojure.test :refer :all]
            [aoc2022.day2 :as d2]))

(def rcp-truth
  [
   [:rock :rock :draw]
   [:rock :paper :loses]
   [:rock :scissors :wins]

   [:paper :paper :draw]
   [:paper :scissors :loses]
   [:paper :rock :wins]

   [:scissors :scissors :draw]
   [:scissors :paper :wins]
   [:scissors :rock :loses]
   ]
)

(defn f-truth [[a b r]]
  (= (d2/rps-get a b) r))

(deftest rps-truth-test
  (testing "is every win as it should?"
    (is (every? f-truth rcp-truth))))

(def rcp-sample-games
  [;; opponent-shape your-shape points
   [:rock :paper 8]
   [:paper :rock 1]
   [:scissors :scissors 6]])

(defn points?
  [[s1 s2 p]]
  (= (d2/your-score [s1 s2]) p))

(deftest point-test
  (testing "sample input games"
    (is (every?
         points?
         rcp-sample-games))))


(def b-games-test
  [[:rock :draw :rock]
   [:paper :loses :rock]
   [:scissors :wins :rock]])

(defn b-truth? [[op res you]]
  (= you (second (d2/invert-game [op res]))))

(deftest b-test
  (testing "if inverting games correct"
    (is (every?
         b-truth?
         b-games-test))))

(deftest part-a-sample-test
  (testing "sample to result"
    (is (= (d2/part-a d2/sample)
           d2/sample-result-a))))

(deftest part-a-real-test
  (testing "real data to result"
    (is (= (d2/part-a d2/data-a)
           d2/result-a))))

(deftest part-b-sample-test
  (testing "sample to result"
    (is (= (d2/part-b d2/sample)
           d2/sample-result-b))))

(deftest part-b-real-test
  (testing "real data to result"
    (is (= (d2/part-b d2/data-a)
           d2/result-b))))
