(ns clojure-docker.core-test
  (:require [clojure.test :refer :all]
            [clojure-docker.core :refer :all]))

(deftest a-test
  (testing "FIXED"
    (is (= 1 1))))

(deftest b-test
  (testing "Can fail"
    (is (= 0 1))))