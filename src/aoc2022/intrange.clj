(ns aoc2022.intrange)

;; ----------------------------------------
;; util for using int ranges in form [x y]
;; ----------------------------------------

(defn is-intrange? [r]
  (and (= (count r) 2)
       (<= (first r)(second r))))

(defn subrange?
  "true, if r1 is a subrange of r2, i.e.
  l1>=l2, r1<=r2
  Example
  f    r1    r2
  t    2 3   1 4
  t    2 3   2 3
  f    2 4   1 3
  "
  [[l1 r1][l2 r2]]
  (and (<= l2 l1)(<= r1 r2)))

(defn intrange-subrange?
  "if r1 and r2 can be swapped, takes a vector of two ranges"
  [[r1 r2]]
  (or (subrange? r1 r2)
      (subrange? r2 r1)))

(defn in-range?
  [i [l1 r1]]
  (and (>= i l1)(<= i r1)))

(defn overlap-l-r?
  "if r1 and r2 overlap in at least one point"
  [[[l1 r1][l2 r2]]]
  (or (in-range? l1 [l2 r2])
      (in-range? r1 [l2 r2])))

(defn overlap?
  "if r1 and r2 overlap in at least one point"
  [[r1 r2]]
  (or (overlap-l-r? [r1 r2])
      (overlap-l-r? [r2 r1])))
