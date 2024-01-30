(ns babashka.json
  (:refer-clojure :exclude [read]))

(defn- try-require [sym]
  (try (deref (requiring-resolve sym))
       (catch Exception _e
         nil)))

(let [provider (some-> (System/getProperty "babashka.json.provider")
                       not-empty symbol)
      [lib read-str-fn write-str-fn ->json-reader read-fn]
      (if provider
        (deref (requiring-resolve
                (case provider
                  org.clojure/data.json
                  'babashka.json.internal.data-json/fns
                  cheshire/cheshire
                  'babashka.json.internal.cheshire/fns
                  com.cnuernber/charred
                  'babashka.json.internal.charred/fns
                  metosin/jsonista
                  'babashka.json.internal.jsonista/fns)))
        (or
         ;; this should always work in babashka
         (try-require 'babashka.json.internal.cheshire/fns)
         ;; this needs to be added to the classpath explicitly
         (try-require 'babashka.json.internal.charred/fns)
         ;; this needs to be added to the classpath explicitly
         (try-require 'babashka.json.internal.jsonista/fns)
         ;; this one should always work as this project has a dependency on it.
         (try-require 'babashka.json.internal.data-json/fns)))]
  (def ^:private lib lib)
  (def ^:private read-str-fn read-str-fn)
  (def ^:private write-str-fn write-str-fn)
  (def ^:private internal->json-reader ->json-reader)
  (def ^:private read-fn read-fn))

(defn read
  ([reader] (read reader nil))
  ([reader opts]
   (read-fn reader opts)))

(defn read-str
  ([s] (read-str s nil))
  ([s opts]
   (read-str-fn s opts)))

(defn write-str
  ([s] (write-str s nil))
  ([s opts]
   (write-str-fn s opts)))

(defn ->json-reader
  ([x] (->json-reader x nil))
  ([x opts]
   (internal->json-reader x opts)))

(defn get-provider
  "Returns which library currently provides the JSON implementation."
  []
  lib)
