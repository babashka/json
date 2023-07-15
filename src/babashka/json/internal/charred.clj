(ns babashka.json.internal.charred
  (:require [charred.api :as json])
  (:refer-clojure :exclude [read])
  (:import [charred CloseableSupplier]))

(set! *warn-on-reflection* true)

(defn ->json-reader
  ([x] (->json-reader x nil))
  ([x {:keys [key-fn] :or {key-fn keyword}}] (json/read-json-supplier x :key-fn key-fn)))

(defn read
  ([reader] (read reader nil))
  ([reader _]
   (.get ^CloseableSupplier reader)))

(defn read-str
  ([s] (read-str s nil))
  ([s {:keys [key-fn]}] (json/read-json s :key-fn (or key-fn keyword))))

(defn write-str
  ([s] (write-str s nil))
  ([s _opts] (json/write-json-str s)))

(def fns ['com.cnuernber/charred read-str write-str (fn [x & _xs] x) read])
