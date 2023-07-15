(ns babashka.json-test
  (:require [babashka.json :as json]
            [clojure.test :refer [deftest is testing]]))

(deftest json-test
  (println "Testing provider" (json/get-provider))
  (testing "read json"
    (is (= [1 2 3] (json/read-str "[1, 2, 3]")))
    (is (= {:a 1} (json/read-str "{\"a\": 1}")))
    (is (= {:a 1} (json/read-str "{\"a\": 1}" {:key-fn keyword})))
    (is (= {"a" 1} (json/read-str "{\"a\": 1}" {:key-fn str}))))
  (testing "write json"
    (is (= [1 2 3] (json/read-str (json/write-str [1 2 3]))))
    (is (= {:a 1} (json/read-str (json/write-str {:a 1})))))
  (testing "read json"
    (let [rdr (json/->json-reader (java.io.StringReader. "{\"a\": 1} {\"b\": 2}"))]
      (is (= {:a 1} (json/read rdr)))
      #_(is (= {:b 2} (json/read rdr))))))

(deftest provider-test
  (let [prop (some-> (System/getProperty "babashka.json.provider") not-empty symbol)]
    (when prop
      (is (= prop (json/get-provider))))))
