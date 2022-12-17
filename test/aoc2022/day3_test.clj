(ns aoc2022.day3-test
  (:require [clojure.test :as t]
            [aoc2022.day3 :as d3]))

(t/deftest convert-char-test
  (t/testing "a to z in 1..26"
    (t/is (= 1 (d3/convert-char \a)))
    (t/is (= 2 (d3/convert-char \b)))
    (t/is (= 26 (d3/convert-char \z))))

  (t/testing "A to Z in 27..52"
    (t/is (= 27 (d3/convert-char \A)))
    (t/is (= 28 (d3/convert-char \B)))
    (t/is (= 52 (d3/convert-char \Z))))
  )

(def rucksack-fs1 [[1 2 3 4] [5 6 7 4]])
(def rucksack-fs2 [[27 2 3 4] [31 6 7 4]])


(t/deftest find-share-test
  (t/testing "find 4 = d as only shared value"
    (t/is (= #{4}
           (d3/find-shares rucksack-fs1))))
  (t/testing "find 4 = d as only shared value in mixed rucksack"
    (t/is (= #{4}
           (d3/find-shares rucksack-fs2)))))

(t/deftest part-a-test
  (t/testing "sample part a"
    (t/is (= d3/sample-result-a
             (d3/part-a d3/sample))))
  (t/testing "real data part a"
    (t/is (= d3/result-a
             (d3/part-a d3/data-a)))))



(t/deftest part-b-test
  (t/testing "sample part b"
    (t/is (= d3/sample-result-b
             (d3/part-b d3/sample))))
  (t/testing "real data part b"
    (t/is (= d3/result-b
             (d3/part-b d3/data-a)))))
