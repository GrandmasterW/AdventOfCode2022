(ns aoc2022.day4-test
  (:require [clojure.test :as t]
            [aoc2022.day4 :as d]))

(t/deftest convert-range-test
  (t/testing "simple ranges only"
    (t/is (= [2 10] (d/convert-range "2-10")))
    (t/is (= [0 1] (d/convert-range "0-1")))
    (t/is (= [123456789 123456790] (d/convert-range "123456789-123456790")))))
    

(t/deftest convert-line-test
  (t/testing "simple line range conversions"
    (t/is (= [[2 10][5 12]] (d/convert-line "2-10,5-12")))
    (t/is (= [[0 1][5 5]] (d/convert-line "0-1,5-5")))
    (t/is (= [[123456789 123456790][5 5]] (d/convert-line "123456789-123456790,5-5")))
))
    
(t/deftest part-a-test
  (t/testing "sample part a"
    (t/is (= d/sample-result-a
             (d/part-a d/sample))))
  (t/testing "real data part a"
    (t/is (= d/result-a
             (d/part-a d/data-a)))))



(t/deftest part-b-test
  (t/testing "sample part b"
    (t/is (= d/sample-result-b
             (d/part-b d/sample))))
  (t/testing "real data part b"
    (t/is (= d/result-b
             (d/part-b d/data-a)))))
