(ns aoc2022.intrange-test
  (:require [clojure.test :as t]
            [aoc2022.intrange :as ir]))



(t/deftest is-intrange?-test
  (t/testing "simple ranges only"
    (t/is (ir/is-intrange? [2 3]))
    (t/is (ir/is-intrange? [2 2])))
  (t/testing "failure examples"
    (t/is (not (ir/is-intrange? [3 2])))
    (t/is (not (ir/is-intrange? [32938473847 2])))))
    
(t/deftest subrange?-test
  (t/testing "check truthiness true" 
    (t/is (ir/subrange? [2 3][1 4]))
    (t/is (ir/subrange? [2 3][2 3]))
    (t/is (ir/subrange? [2 3][1 3887878])))
  (t/testing "false examples"
    (t/is (not (ir/subrange? [1 4][2 3])))
    (t/is (not (ir/subrange? [2 2][3 3])))
    (t/is (not (ir/subrange? [1 3887878][2 4]))))
    )

(t/deftest intrange-subrange?-test
  (t/testing "true"
    (t/is (ir/intrange-subrange? [[2 3][1 4]]))
    (t/is (ir/intrange-subrange? [[1 4][2 3]]))
    (t/is (ir/intrange-subrange? [[2 3][2 3]]))
    (t/is (ir/intrange-subrange? [[1 3887878][2 4]])))
  (t/testing "false"
    (t/is (not (ir/intrange-subrange? [[8 8][1 4]])))
    (t/is (not (ir/intrange-subrange? [[1 4][3 10]])))
    (t/is (not (ir/intrange-subrange? [[10 20][1 4]])))
    ))

(t/deftest in-range?-test
  (t/testing "true"
    (t/is (ir/in-range? 1 [1 3]))
    (t/is (ir/in-range? 2 [1 3]))
    (t/is (ir/in-range? 3 [1 3])))
  (t/testing "false"
    (t/is (not (ir/in-range? 0 [1 3])))
    (t/is (not (ir/in-range? 4 [1 3])))
    (t/is (not (ir/in-range? 5 [1 3])))))

    

(t/deftest overlap?-test
  (t/testing "true"
    (t/is (ir/overlap? [[1 3][3 4]]))
    (t/is (ir/overlap? [[1 8][3 4]]))
    (t/is (ir/overlap? [[3 4][1 3]]))
    (t/is (ir/overlap? [[3 4][3 4]]))
    )
    (t/testing "false"
    (t/is (not (ir/overlap? [[1 3][4 4]])))
    (t/is (not (ir/overlap? [[1 8][19 34]])))
    (t/is (not (ir/overlap? [[3 4][1 2]])))
    ))
