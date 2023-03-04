(ns babashka.json-test
  (:require [babashka.json :as json]
            [clojure.test :refer [deftest is testing]]))

(deftest read-str-test
  (testing "read json"
    (is (= [1 2 3] (json/read-str "[1, 2, 3]")))
    (is (= {:a 1} (json/read-str "{\"a\": 1}")))
    (is (= {:a 1} (json/read-str "{\"a\": 1}" {:key-fn keyword})))
    (is (= {"a" 1} (json/read-str "{\"a\": 1}" {:key-fn str}))))
  (testing "write json"
    (is (= [1 2 3] (json/read-str (json/write-str [1 2 3]))))
    (is (= {:a 1} (json/read-str (json/write-str {:a 1}))))))
